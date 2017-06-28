package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.cuit.drawdream.bean.ReplayEntity;
import com.cuit.drawdream.bean.ReplayEntityDao;
import com.cuit.drawdream.bean.UserInfoEntity;
import com.cuit.drawdream.bean.UserInfoEntityDao;
import com.cuit.drawdream.drawdream.BR;
import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.ordinary.DetialArticleEntity;
import com.cuit.drawdream.drawdream.bean.ordinary.ItemIndexEntity;
import com.cuit.drawdream.drawdream.bean.ordinary.ReviewEntity;
import com.cuit.drawdream.drawdream.bean.response.ResponseReview;
import com.cuit.drawdream.drawdream.view.CommentActivity;
import com.google.gson.Gson;
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * class :    DetailActivityViewModel
 * Created by yangq
 * At         2017/5/18.
 * Desc :
 */

public class DetailActivityViewModel extends BaseViewModel {

    private static final String TAG = "DetaiActivitylVM";
    private static int ITEM_RECOMMEND = 0;     //相关推荐
    private static int ITEM_REVIEW = 1;        //评论
    private static String DETAIL_ID_NOW = "";

    private Context mContext;
    private ItemIndexEntity mEntity;
    private ArrayList<DetialArticleEntity> mListForRecommend;
    private ArrayList<ReviewEntity> mListForReview;
    private Subscription mSubscription;
    private Handler mHeadler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ReviewEntity entity = (ReviewEntity) msg.obj;
            if(msg.arg1 == 1){
                reLoadData(entity);
                Toast.makeText(mContext,"评论成功",Toast.LENGTH_SHORT)
                        .show();
            }else {
                Toast.makeText(mContext,"评论失败",Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };

    public final ObservableBoolean isNoDataShowing = new ObservableBoolean(false);
    public final ObservableField<String > mCommentNum = new ObservableField<>();
    public final ObservableField<String > mTitle = new ObservableField<>();
    public final ObservableField<String > mReporter = new ObservableField<>();
    public final ObservableField<String > mReadNum = new ObservableField<>();
    public final ObservableField<String > mTitleImg = new ObservableField<>();
    public final ObservableArrayList<ItemDetailViewModel> viewModelsForRecommend = new ObservableArrayList<>();
    public final ObservableArrayList<ItemDetailViewModel> viewModelsForReview = new ObservableArrayList<>();
    public final ItemViewSelector<ItemDetailViewModel> itemView = new ItemViewSelector<ItemDetailViewModel>() {
        @Override
        public void select(ItemView itemView, int position, ItemDetailViewModel item) {
            if(ITEM_RECOMMEND == item.mType){
                itemView.set(BR.itemDetailRecommendViewModel, R.layout.item_detail_recommend);
            }else {
                itemView.set(BR.itemDetailReviewViewModel,R.layout.item_detail_review);
            }
        }

        @Override
        public int viewTypeCount() {
            return 2;
        }
    };

    public DetailActivityViewModel(Context context, ItemIndexEntity entity) {
        super(context);
        mContext = context;
        mEntity = entity;
        DETAIL_ID_NOW = mEntity.getId();
        initData();
    }

    private void initData() {

        mTitle.set(mEntity.getTitle());
        mTitleImg.set(mEntity.getImg());
        mReadNum.set(mEntity.getTime());
        mReporter.set("报道：" + mEntity.getAuthor());

        mListForRecommend = new ArrayList<>();
        mListForReview = new ArrayList<>();

        mListForRecommend.add(new DetialArticleEntity("「狐妖小红娘」日语版追加CAST公布，国语版「南国篇」确定制作","阅读3201"));
        mListForRecommend.add(new DetialArticleEntity("魔幻史诗《幻镜诺德琳》上线，很久没有看到这么良心的国产动画了","阅读1011"));
        mListForRecommend.add(new DetialArticleEntity("《夏目的友人帐》破译了！","阅读1924"));
        mListForRecommend.add(new DetialArticleEntity("非遗+动漫有多惊艳？狐妖和灵剑山有新画风了！","阅读5313"));

        //加载评论数据
//        loadReViewDataFromNet();


        //加载推荐
        for(DetialArticleEntity entity: mListForRecommend){
            ItemDetailViewModel viewModel = new ItemDetailViewModel(mContext,ITEM_RECOMMEND,entity,null);
            viewModelsForRecommend.add(viewModel);
        }


    }

    /**
     * 获取评论数据
     */
    private void loadReViewDataFromNet() {
        Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<>();
        //文章id为pk
        map.put("news_id",mEntity.getId());
        String jsonStr = gson.toJson(map);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),jsonStr);
        mSubscription = getApplication()
                .getNetworkService()
                .review(body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(getApplication().defaultSubscribeScheduler())
                .subscribe(new Observer<Response<ResponseReview>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"*******************" + e.getMessage());
                    }

                    @Override
                    public void onNext(Response<ResponseReview> responseReviewResponse) {
                        if(responseReviewResponse.body().getSuccess().equals("true")){
                            loadDataForReview(responseReviewResponse.body().getData());
                            //加载评论
                            for(ReviewEntity entity: mListForReview){
                                ItemDetailViewModel viewModel = new ItemDetailViewModel(mContext,ITEM_REVIEW,null,entity);
                                viewModelsForReview.add(viewModel);
                            }
                        }
                    }
                });
    }

    /**
     * 评论
     */
    public final ReplyCommand comment = new ReplyCommand(()->{
//            Toast.makeText(mContext,"回复成功",Toast.LENGTH_SHORT)
//                    .show();
        MyApplication.getInstance().setHandler(mHeadler);
        Bundle bundle = new Bundle();
        bundle.putString("DetailId",DETAIL_ID_NOW);
        Intent intent = new Intent(mContext, CommentActivity.class);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    });

    /**
     * 加载评论
     */
    private void loadDataForReview(ArrayList<ReviewEntity> list){
        for(ReviewEntity entity : list){
            ReviewEntity entityReView = new ReviewEntity();
//            entityReView.setContent(entity.getCore_content());
//            entityReView.setTime(entity.getCore_date());
//            UserInfoEntity entityUserInfo = new UserInfoEntity();
//            entityUserInfo = userInfoDao.load(entity.getCore_acco_id());
//            entityReView.setName(entityUserInfo.getUser_name());
//            entityReView.setHeader("file:///android_asset/head1.jpg");
            entityReView = entity;
            entityReView.setHeader("file:///android_asset/head1.jpg");
            mListForReview.add(entityReView);
        }
        if(0 == mListForReview.size()){
            isNoDataShowing.set(true);
            mCommentNum.set("评论");
        }else {
            mCommentNum.set("评论（" + mListForReview.size() + ")");
            isNoDataShowing.set(false);
        }
    }
//    /**
//     * 加载评论
//     */
//    private void loadDataForReview() {
//        ReplayEntityDao replyDao = MyApplication.daoSession.getReplayEntityDao();
//        UserInfoEntityDao userInfoDao = MyApplication.daoSession.getUserInfoEntityDao();
//        for(ReplayEntity entity : (ArrayList<ReplayEntity>) replyDao.loadAll()){
//            if(entity.getCore_nede_id().equals(mEntity.getId())){
//                ReviewEntity entityReView = new ReviewEntity();
//                entityReView.setContent(entity.getCore_content());
//                entityReView.setTime(entity.getCore_date());
//                UserInfoEntity entityUserInfo = new UserInfoEntity();
//                entityUserInfo = userInfoDao.load(entity.getCore_acco_id());
//                entityReView.setName(entityUserInfo.getUser_name());
//                entityReView.setHeader("file:///android_asset/head1.jpg");
//                mListForReview.add(entityReView);
//            }
//        }
//        if(0 == mListForReview.size()){
//            isNoDataShowing.set(true);
//            mCommentNum.set("评论");
//        }else {
//            mCommentNum.set("评论（" + mListForReview.size() + ")");
//            isNoDataShowing.set(false);
//        }
//    }

    /**
     * 重新加载数据
     */
    public void reLoadData(ReviewEntity entity){
        ItemDetailViewModel viewModel = new ItemDetailViewModel(mContext,ITEM_REVIEW,null,entity);
        //最新的评论在第一个
        viewModelsForReview.add(0,viewModel);
        isNoDataShowing.set(false);
        mCommentNum.set("评论（" + viewModelsForReview.size() + "）");
    }

    @Override
    public void destroy() {

    }

    public class ItemDetailViewModel extends BaseViewModel{

        private Context mContext;
        private int mType;
        private DetialArticleEntity mArtEntity;
        private ReviewEntity mReviewEntity;

        public final ObservableField<String > mTitle = new ObservableField<>();
        public final ObservableField<String > mReadNum = new ObservableField<>();
        public final ObservableField<String > mHeadImg = new ObservableField<>();
        public final ObservableField<String > mName = new ObservableField<>();
        public final ObservableField<String > mTime = new ObservableField<>();
        public final ObservableField<String > mContent = new ObservableField<>();
        public final ObservableField<String > mToWho = new ObservableField<>();

        public ItemDetailViewModel(Context context,
                                   int type,
                                   DetialArticleEntity artEntity,
                                   ReviewEntity reviewEntity) {
            super(context);
            mContext = context;
            mType = type;
            if(null != artEntity){
                mArtEntity = artEntity;
                setRecommendData();
            }else if(null != reviewEntity){
                mReviewEntity = reviewEntity;
                setReviewData();
            }
        }

        /**
         * 设置评论数据
         */
        private void setReviewData() {
            mHeadImg.set(mReviewEntity.getHeader());
            mName.set(mReviewEntity.getName());
            mTime.set(mReviewEntity.getTime());
            mContent.set(mReviewEntity.getContent());
//            if(!mReviewEntity.getToWhoByName().equals("art")){
//                mContent.set("回复@" + mReviewEntity.getToWhoByName() + ":" + mReviewEntity.getContent());
//            }else {
//                mContent.set("阅读" + mReviewEntity.getContent());
//            }
        }

        /**
         * 设置推荐数据
         */
        private void setRecommendData() {
            mTitle.set(mArtEntity.getTitle());
            mReadNum.set(mArtEntity.getReadNum());
        }

        public final ReplyCommand replyToWho = new ReplyCommand(()->{
            Bundle bundle = new Bundle();
            MyApplication.getInstance().setHandler(mHeadler);
            bundle.putString("DetailId",DETAIL_ID_NOW);
            Intent intent = new Intent(mContext, CommentActivity.class);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        });

        @Override
        public void destroy() {

        }
    }
}
