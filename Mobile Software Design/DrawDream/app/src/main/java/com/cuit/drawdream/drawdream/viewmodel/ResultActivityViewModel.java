package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.cuit.drawdream.drawdream.BR;
import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.ordinary.DetailEntity;
import com.cuit.drawdream.drawdream.bean.ordinary.ItemIndexEntity;
import com.cuit.drawdream.drawdream.bean.response.ResponseClassifyResult;
import com.cuit.drawdream.drawdream.view.DetailActivity;
import com.google.gson.Gson;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;
import java.util.HashMap;

import me.tatarka.bindingcollectionadapter.ItemView;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by sunnylu on 2017/6/19.
 */

public class ResultActivityViewModel extends BaseViewModel {

    private Context mContext;
    private String classify;
//    private static ArrayList<SearchResultEntity> rList;
    private Subscription subscription;
    private ArrayList<DetailEntity> res_list;

    //数据源
    public final ObservableArrayList<ItemResultViewModel> viewModels = new ObservableArrayList<>();
    //子布局模板
    public final ItemView itemView = ItemView.of(BR.itemResultViewModel, R.layout.item_result);
    //布局管理器
    public final ObservableField<GridLayoutManager> mGridLayoutManager = new ObservableField<>();
    public ResultActivityViewModel(Context context, String classify){
        super(context);
        mContext = context;
        this.classify = classify;
        requestNetwork();
        setManager();
    }

    @Override
    public void destroy() {

    }

    private void requestNetwork(){
        Gson gson = new Gson();
        HashMap<String ,String > postMap = new HashMap<>();
        postMap.put("classify", classify);
        String strGson = gson.toJson(postMap);
        Log.i("STRGSON", strGson);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strGson);
        subscription = getApplication()
                .getNetworkService()
                .result(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(getApplication().defaultSubscribeScheduler())
                .subscribe(new Observer<Response<ResponseClassifyResult>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<ResponseClassifyResult> responseClassifyResultResponse) {
                        if(responseClassifyResultResponse.body().getSuccess().equals("true")){
                            res_list = (ArrayList<DetailEntity>) responseClassifyResultResponse.body().getData();
                            initUI();
                        }
                    }
                });
    }

    public void initUI(){
        for (DetailEntity entity: res_list){
            ItemResultViewModel model = new ItemResultViewModel(mContext, entity);
            viewModels.add(model);
        }
    }

//    public ArrayList<NewsDetail> loadData(){
//        ArrayList<NewsDetail> list = new ArrayList<>();
//        NewsDetailDao dao = MyApplication.daoSession.getNewsDetailDao();
//        list = (ArrayList<NewsDetail>) dao.loadAll();
//
//        return list;
//    }

    public void setManager(){
        GridLayoutManager glm = new GridLayoutManager(mContext,1);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return 1;
            }
        });

        mGridLayoutManager.set(glm);
    }

    public class ItemResultViewModel extends BaseViewModel{
        private Context itemContext;
        private DetailEntity entity;

        public final ObservableField<String > mImage = new ObservableField<>();
        public final ObservableField<String > mAuthor = new ObservableField<>();
        public final ObservableField<String > mTime = new ObservableField<>();
        public final ObservableField<String > mTitle = new ObservableField<>();
        public final ObservableField<String > mContent = new ObservableField<>();
        public final ObservableField<String > mBrowse = new ObservableField<>();
        public final ObservableField<String > love = new ObservableField<>();

        public ItemResultViewModel(Context context, DetailEntity entity) {
            super(context);
            itemContext = context;
            if (null != entity){
                this.entity = entity;
                setData();
            }else{
                setData();
            }
        }

        public void setData(){
            mImage.set(entity.getNede_cover_img());
            mAuthor.set(entity.getNede_author());
            mTime.set(entity.getNede_web_time());
            mTitle.set(entity.getNede_title());
            mContent.set(entity.getNede_content());
            mBrowse.set(entity.getNede_browse() + "w");
            love.set(entity.getNede_love() + "");

        }

        @Override
        public void destroy() {

        }

        public final ReplyCommand<Integer> toDetail = new ReplyCommand<Integer>(()->{
            Bundle bundle = new Bundle();
            ArrayList<ItemIndexEntity> list = new ArrayList<>();
            list.add(transfer(entity));
            bundle.putSerializable("Detail",list);
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        });

        public ItemIndexEntity transfer(DetailEntity entity){
            ItemIndexEntity itemEntity = new ItemIndexEntity();
            itemEntity.setAuthor(entity.getNede_author());
            itemEntity.setImg(entity.getNede_cover_img());
            itemEntity.setTime(entity.getNede_web_time());
            itemEntity.setTitle(entity.getNede_title());
            itemEntity.setId(entity.getPk());
            itemEntity.setContent(entity.getNede_content());
            itemEntity.setClassify(entity.getNede_classify());
            return itemEntity;
        }
    }
}
