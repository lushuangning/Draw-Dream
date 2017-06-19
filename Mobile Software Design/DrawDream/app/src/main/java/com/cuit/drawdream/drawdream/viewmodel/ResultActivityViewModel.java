package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;

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

    }




    public class ItemResultViewModel extends BaseViewModel{
        private Context itemContext;
        private SearchResultEntity entity;

        public ItemResultViewModel(Context context, SearchResultEntity entity) {
            super(context);
            itemContext = context;
            this.entity = entity;
        }

        public void setData(){

        }

        @Override
        public void destroy() {

        }
    }




}
