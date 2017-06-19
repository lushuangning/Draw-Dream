package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v7.widget.GridLayoutManager;

import com.cuit.drawdream.bean.NewsDetail;
import com.cuit.drawdream.bean.NewsDetailDao;
import com.cuit.drawdream.drawdream.BR;
import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.ordinary.SearchResultEntity;

import java.util.ArrayList;

import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * Created by sunnylu on 2017/6/19.
 */

public class ResultActivityViewModel extends BaseViewModel {

    private Context mContext;
    private static ArrayList<SearchResultEntity> rList;

    //数据源
    public final ObservableArrayList<ItemResultViewModel> viewModels = new ObservableArrayList<>();
    //子布局模板
    public final ItemView itemView = ItemView.of(BR.itemResultViewModel, R.layout.item_result);
    //布局管理器
    public final ObservableField<GridLayoutManager> mGridLayoutManager = new ObservableField<>();
    public ResultActivityViewModel(Context context){
        super(context);
        mContext = context;
        initUI();
        setManager();
    }

    @Override
    public void destroy() {

    }

    public void initUI(){
        rList = new ArrayList<>();

        for (NewsDetail entity: loadData()){
            SearchResultEntity searchEntity = new SearchResultEntity();
            searchEntity.setTable_id(entity.getId());
            searchEntity.setId(entity.getNede_id());
            searchEntity.setAuthor(entity.getNede_author());
            searchEntity.setImg(entity.getNede_img());
            searchEntity.setTime(entity.getNede_time());
            searchEntity.setTitle(entity.getNede_title());
            searchEntity.setContent(entity.getNede_content());
            searchEntity.setBrowse(2400);

            rList.add(searchEntity);
        }

        for (SearchResultEntity entity: rList){
            ItemResultViewModel model = new ItemResultViewModel(mContext, entity);
            viewModels.add(model);
        }
    }

    public ArrayList<NewsDetail> loadData(){
        ArrayList<NewsDetail> list = new ArrayList<>();
        NewsDetailDao dao = MyApplication.daoSession.getNewsDetailDao();
        list = (ArrayList<NewsDetail>) dao.loadAll();

        return list;
    }

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
        private SearchResultEntity entity;

        public final ObservableField<String > mImage = new ObservableField<>();
        public final ObservableField<String > mAuthor = new ObservableField<>();
        public final ObservableField<String > mTime = new ObservableField<>();
        public final ObservableField<String > mTitle = new ObservableField<>();
        public final ObservableField<String > mContent = new ObservableField<>();
        public final ObservableField<Integer > mBrowse = new ObservableField<>();

        public ItemResultViewModel(Context context, SearchResultEntity entity) {
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
            mImage.set(entity.getImg());
            mAuthor.set(entity.getAuthor());
            mTime.set(entity.getTime());
            mTitle.set(entity.getTitle());
            mContent.set(entity.getContent());
            mBrowse.set(entity.getBrowse());
        }

        @Override
        public void destroy() {

        }
    }




}
