package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
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
import com.kelin.mvvmlight.command.ReplyCommand;

import java.util.ArrayList;

import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

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

    private Context mContext;
    private ItemIndexEntity mEntity;
    private ArrayList<DetialArticleEntity> mListForRecommend;
    private ArrayList<ReviewEntity> mListForReview;

    public final ObservableBoolean isNoDataShowing = new ObservableBoolean(false);
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
        initData();
    }

    private void initData() {

//        mTitle.set("《夏目友人帐》第五季官方图确认公布时间倒计时！");
//        mReporter.set("报道：" + "丢仔");
//        mReadNum.set("阅读" + "2930");
//        mTitleImg.set("http://img4.178.com/acg1/201705/289093273635/289093503178.jpg");
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
        loadDataForReview();


        //加载推荐
        for(DetialArticleEntity entity: mListForRecommend){
            ItemDetailViewModel viewModel = new ItemDetailViewModel(mContext,ITEM_RECOMMEND,entity,null);
            viewModelsForRecommend.add(viewModel);
        }
        for(ReviewEntity entity:mListForReview){
            ItemDetailViewModel viewModel = new ItemDetailViewModel(mContext,ITEM_REVIEW,null,entity);
            viewModelsForReview.add(viewModel);
        }

    }

    /**
     * 加载评论
     */
    private void loadDataForReview() {
        ReplayEntityDao replyDao = MyApplication.daoSession.getReplayEntityDao();
        UserInfoEntityDao userInfoDao = MyApplication.daoSession.getUserInfoEntityDao();
        for(ReplayEntity entity : (ArrayList<ReplayEntity>) replyDao.loadAll()){
            if(entity.getCore_nede_id().equals(mEntity.getId())){
                ReviewEntity entityReView = new ReviewEntity();
                entityReView.setContent(entity.getCore_content());
                entityReView.setTime(entity.getCore_date());
                UserInfoEntity entityUserInfo = new UserInfoEntity();
                entityUserInfo = userInfoDao.load(entity.getCore_acco_id());
                entityReView.setName(entityUserInfo.getUser_name());
                entityReView.setHeader("file:///android_asset/head1.jpg");
                mListForReview.add(entityReView);
            }
        }
        if(0 == mListForReview.size()){
            isNoDataShowing.set(true);
        }
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
            Toast.makeText(mContext,"回复成功",Toast.LENGTH_SHORT)
                    .show();
        });

        @Override
        public void destroy() {

        }
    }
}
