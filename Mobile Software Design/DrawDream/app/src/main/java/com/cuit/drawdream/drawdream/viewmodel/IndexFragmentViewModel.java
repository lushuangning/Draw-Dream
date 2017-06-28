package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.cuit.drawdream.drawdream.BR;
import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.ordinary.DetailEntity;
import com.cuit.drawdream.drawdream.bean.ordinary.ItemIndexEntity;
import com.cuit.drawdream.drawdream.bean.response.ResponseClassifyResult;
import com.cuit.drawdream.drawdream.utils.tool.Config;
import com.cuit.drawdream.drawdream.utils.tool.GlideImageLoader;
import com.cuit.drawdream.drawdream.view.DetailActivity;
import com.google.gson.Gson;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;
import java.util.HashMap;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * class :    IndexFragmentViewModel
 * Created by yangq
 * At         2017/5/17.
 * Desc :
 */

public class IndexFragmentViewModel extends BaseViewModel {

    private static final String TAG = "IndexFgVM";

    private static final int ITEM_HEADER_AUTHOR = 1;    //作者
    private static final int ITEM_HEADER_RECYCLER = 0;  //轮播图
    private static final int ITEM_GENERAL = 2;          //普通的
    private static final int REFRESH = 0;               //下拉刷新
    private static final int LOAD_MORE = 1;             //加载更多

    private static int LOADMORE_TIMES = 1;

    private Context mContext;
    private ArrayList<DetailEntity> mListNews = new ArrayList<>();   //普通新闻数据
    private Subscription mSubscription;
    private static ArrayList<ItemIndexEntity> mList;

    public final ObservableBoolean isRefreshing = new ObservableBoolean(true);
    public final ObservableBoolean isProgressBarShowing = new ObservableBoolean(true);
    public final ObservableField<GridLayoutManager> mGridLayoutManager = new ObservableField<>();
    public final ObservableArrayList<ItemIndexViewModel> viewModels = new ObservableArrayList<>();
    public final ItemViewSelector<ItemIndexViewModel> itemView = new ItemViewSelector<ItemIndexViewModel>() {
        @Override
        public void select(ItemView itemView, int position, ItemIndexViewModel item) {
            if(ITEM_HEADER_RECYCLER == item.mType){
                itemView.set(BR.itemHeaderRecyclerViewModel,R.layout.item_header_recycler);
            }else if(ITEM_HEADER_AUTHOR == item.mType){
                itemView.set(BR.itemHeaderAuthorViewModel,R.layout.item_header_author);
            }else {
                itemView.set(BR.itemIndexViewModel,R.layout.item_index);
            }
        }

        @Override
        public int viewTypeCount() {
            return 3;
        }
    };

    public IndexFragmentViewModel(Context context) {
        super(context);
        mContext = context;
//        initUI();
        loadDataFromNet(REFRESH);
        setManager();
    }

    /**
     * 设置item占有列数
     */
    private void setManager() {
        GridLayoutManager glm = new GridLayoutManager(mContext,2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //轮播图和作者推荐区占2列，普通的占一列
                if(ITEM_HEADER_RECYCLER == position || ITEM_HEADER_AUTHOR == position){
                    return 2;
                }else {
                    return 1;
                }
            }
        });
        mGridLayoutManager.set(glm);
    }

    private void initUI(Boolean isReFreshing) {
        isProgressBarShowing.set(false);
        isRefreshing.set(false);
        if(isReFreshing){
            //每次刷新清空viewmodels
            viewModels.clear();
        }

        mList = new ArrayList<>();

        ArrayList<String > images = new ArrayList<>();
        for(DetailEntity entity : mListNews){
            ItemIndexEntity itemEntity = new ItemIndexEntity();
          //TODO 改对应的数据设置
            itemEntity.setAuthor(entity.getNede_author());
            itemEntity.setImg(entity.getNede_cover_img());
            itemEntity.setTime(entity.getNede_web_time());
            itemEntity.setTitle(entity.getNede_title());
//          这里应该是PK
            itemEntity.setId(entity.getPk());
            itemEntity.setContent(entity.getNede_content());
            itemEntity.setClassify(entity.getNede_classify());
//            itemEntity.setTable_id();
            mList.add(itemEntity);
        }

        //轮播图
        images.add(mList.get(0).getImg());
        images.add(mList.get(1).getImg());
        images.add(mList.get(2).getImg());
        images.add(mList.get(3).getImg());

        ItemIndexViewModel viewModelForRecycler = new ItemIndexViewModel(mContext,
                new ItemIndexEntity(images),
                ITEM_HEADER_RECYCLER);
        viewModels.add(viewModelForRecycler);

        ItemIndexViewModel viewModelForAuthor = new ItemIndexViewModel(mContext,
                null,
                ITEM_HEADER_AUTHOR);
        viewModels.add(viewModelForAuthor);

        for(ItemIndexEntity entity :mList){
            ItemIndexViewModel viewModel = new ItemIndexViewModel(mContext,entity,ITEM_GENERAL);
            viewModels.add(viewModel);
        }

    }

    private void loadDataFromNet(int key){
        Gson gson = new Gson();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("operation", key);
        map.put("page",LOADMORE_TIMES);
        String jsonStr = gson.toJson(map);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),jsonStr);

        mSubscription = getApplication()
                .getNetworkService()
                .index(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(getApplication().defaultSubscribeScheduler())
                .subscribe(new Observer<Response<ResponseClassifyResult>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,e.getMessage());
                        Log.d(TAG,"*************************");
                        Toast.makeText(mContext,"服务器错误",Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onNext(Response<ResponseClassifyResult> responseClassifyResultResponse) {
                        if(responseClassifyResultResponse.body().getSuccess().equals("true")){
                            mListNews = (ArrayList<DetailEntity>) responseClassifyResultResponse.body().getData();
                            initUI(LOADMORE_TIMES == 1?true:false);
                        }else {
                            if(responseClassifyResultResponse.body().getMsg().equals(Config.CODE_NONE)){
                                Toast.makeText(mContext,"没有更多了",Toast.LENGTH_SHORT)
                                        .show();
                            }else if(responseClassifyResultResponse.body().getMsg().equals(Config.CODE_ERROR)){
                                Toast.makeText(mContext,"服务器错误",Toast.LENGTH_SHORT)
                                        .show();
                            }

                        }
                    }
                });
    }

    /**
     * 下拉刷新
     */
    public final ReplyCommand onRefreshCommand = new ReplyCommand(()->{
        isRefreshing.set(true);
        LOADMORE_TIMES = 0;
        //重新载入数据
        loadDataFromNet(REFRESH);
    });

    /**
     * 加载更多
     */
    public final ReplyCommand<Integer> onLoadMoreCommand = new ReplyCommand<>((itemCount)->{
        Log.d(TAG,"item " + itemCount);
        LOADMORE_TIMES += 1;
        //加载更多
        loadDataFromNet(LOADMORE_TIMES);
    });




    @Override
    public void destroy() {

    }

    public class ItemIndexViewModel extends BaseViewModel{

        private Context mContext;
        private ItemIndexEntity mEntity;
        private int mType;

        public GlideImageLoader mGil = new GlideImageLoader();

        public final ObservableArrayList<String > mImages = new ObservableArrayList<>();
        public final ObservableField<String > mImage = new ObservableField<>();
        public final ObservableField<String > mTitle = new ObservableField<>();
        public final ObservableField<String > mClassify = new ObservableField<>();
        public final ObservableField<String > mAuthor = new ObservableField<>();
        public final ObservableField<String > mTime = new ObservableField<>();

        public ItemIndexViewModel(Context context, ItemIndexEntity entity,int type) {
            super(context);
            mContext = context;
            mType = type;
            if(null != entity){
                mEntity = entity;
                setData();
            }else {
                setData();
            }
        }

        private void setData() {
            switch(mType){
                case ITEM_HEADER_RECYCLER:
                    for(String imgUrl:mEntity.getImages()){
                        mImages.add(imgUrl);
                    }
                    Log.d("mImages' size is ","" + mImages.size());
                    break;
                case ITEM_GENERAL:
                    mImage.set(mEntity.getImg());
                    mTitle.set(mEntity.getTitle());
                    mClassify.set(mEntity.getClassify());
                    mAuthor.set(mEntity.getAuthor());
                    mTime.set(mEntity.getTime());
                    break;
            }
        }

        /**
         * 跳转详情页面
         */
        public final ReplyCommand toDetail = new ReplyCommand(()->{
            /**
             * TODO 这里需要获取到被点击item的信息，以便在详情页面进行网络请求
             * mTitle.get();获取信息
             */
            Bundle bundle = new Bundle();
            ArrayList<ItemIndexEntity > list = new ArrayList<>();
            list.add(mEntity);
            bundle.putSerializable("Detail",list);
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        });

        /**
         * 轮播图的监听事件
         */
        public final ReplyCommand banner = new ReplyCommand((position)->{
            Bundle bundle = new Bundle();
            ArrayList<ItemIndexEntity > list = new ArrayList<>();
            list.add(mList.get(Integer.valueOf(position.toString())));
            bundle.putSerializable("Detail",list);
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        });

        @Override
        public void destroy() {

        }
    }

}
