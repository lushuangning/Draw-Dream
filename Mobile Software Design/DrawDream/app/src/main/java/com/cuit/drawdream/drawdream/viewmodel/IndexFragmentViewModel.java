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


import com.bumptech.glide.Glide;
import com.cuit.drawdream.bean.NewsDetail;
import com.cuit.drawdream.bean.NewsDetailDao;
import com.cuit.drawdream.drawdream.BR;
import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.ordinary.DetialArticleEntity;
import com.cuit.drawdream.drawdream.bean.ordinary.ItemIndexEntity;
import com.cuit.drawdream.drawdream.utils.tool.GlideImageLoader;
import com.cuit.drawdream.drawdream.view.DetailActivity;
import com.kelin.mvvmlight.command.ReplyCommand;


import java.util.ArrayList;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

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

    private Context mContext;
    private ArrayList<ItemIndexEntity> mList;

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
        initUI();
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

    private void initUI() {
        isProgressBarShowing.set(false);
        isRefreshing.set(false);
        mList = new ArrayList<>();

        ArrayList<String > images = new ArrayList<>();

        images.add("http://img1.178.com/acg1/201705/288466908147/288471126293.jpg");
        images.add("http://img0.178.com/acg1/201705/288926238343/288926825037.jpg");
        images.add("http://img5.178.com/acg1/201705/288287453008/288288127007.jpg");
        images.add("http://img1.178.com/acg1/201705/288287453008/288288091902.jpg");

        ItemIndexViewModel viewModelForRecycler = new ItemIndexViewModel(mContext,
                new ItemIndexEntity(images),
                ITEM_HEADER_RECYCLER);
        viewModels.add(viewModelForRecycler);

        ItemIndexViewModel viewModelForAuthor = new ItemIndexViewModel(mContext,
                null,
                ITEM_HEADER_AUTHOR);
        viewModels.add(viewModelForAuthor);


        for(NewsDetail entity :loadGeneralData()){
            ItemIndexEntity itemEntity = new ItemIndexEntity();
            itemEntity.setAuthor(entity.getNede_author());
            itemEntity.setImg(entity.getNede_img());
            itemEntity.setTime(entity.getNede_time());
            itemEntity.setTitle(entity.getNede_title());
            itemEntity.setId(entity.getNede_id());
            itemEntity.setContent(entity.getNede_content());
            itemEntity.setClassify("动漫");
            itemEntity.setTable_id(entity.getId());
            mList.add(itemEntity);
        }
//        for(int i = 0; i < 12;i++){
//           mList.add(new ItemIndexEntity("http://img1.178.com/acg1/201705/288466908147/288471126293.jpg",
//                   "今日早报",
//                   "动漫",
//                   "Fragd",
//                   "09-12"));
//        }

        for(ItemIndexEntity entity :mList){
            ItemIndexViewModel viewModel = new ItemIndexViewModel(mContext,entity,ITEM_GENERAL);
            viewModels.add(viewModel);
        }

    }

    private ArrayList<NewsDetail> loadGeneralData() {
        ArrayList<NewsDetail> list = new ArrayList<>();
        NewsDetailDao dao = MyApplication.daoSession.getNewsDetailDao();
        list = (ArrayList<NewsDetail>) dao.loadAll();

        return list;
    }

    /**
     * 下拉刷新
     */
    public final ReplyCommand onRefreshCommand = new ReplyCommand(()->{
        isRefreshing.set(true);
        isRefreshing.set(false);
    });

    /**
     * 加载更多
     */
    public final ReplyCommand<Integer> onLoadMoreCommand = new ReplyCommand<>((itemCount)->{
        Toast.makeText(mContext,"没有更多了" + itemCount,Toast.LENGTH_SHORT)
                .show();
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
        public final ReplyCommand toDetial = new ReplyCommand(()->{
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

        @Override
        public void destroy() {

        }
    }

}
