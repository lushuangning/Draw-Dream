package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.widget.GridLayoutManager;

import com.cuit.drawdream.bean.ClassifyDetailEntity;
import com.cuit.drawdream.bean.ClassifyDetailEntityDao;
import com.cuit.drawdream.drawdream.BR;
import com.cuit.drawdream.drawdream.MyApplication;
import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.ordinary.ClassifyItemEntity;
import com.cuit.drawdream.drawdream.view.ResultActivity;
import com.kelin.mvvmlight.command.ReplyCommand;
import java.util.ArrayList;
import me.tatarka.bindingcollectionadapter.ItemView;

/**
 * Created by sunnlu on 2017/6/13.
 */

public class ClassifyFragmentViewModel extends BaseViewModel {
    private Context mContext;
    private static ArrayList<ClassifyItemEntity> mList;

    public final ObservableBoolean isProgressBarShowing = new ObservableBoolean(true);
    public final ObservableBoolean isRefreshing = new ObservableBoolean(true);

    //布局管理器
    public final ObservableField<GridLayoutManager> mGridLayoutManager = new ObservableField<>();
    //数据源
    public final ObservableArrayList<ItemClassifyViewModel> viewModels = new ObservableArrayList<>();
    //子布局模板
    public final ItemView itemView = ItemView.of(BR.itemClassifyViewModel, R.layout.item_classify);

    //多种子布局
//    public final ItemViewSelector<ItemClassifyViewModel> itemView = new ItemViewSelector<ItemClassifyViewModel>() {
//        @Override
//        public void select(ItemView itemView, int position, ItemClassifyViewModel item) {
//
//            itemView.set(BR.itemClassifyViewModel, R.layout.item_classify);
//        }
//
//        @Override
//        public int viewTypeCount() {
//            return 1;
//        }
//    };


    public ClassifyFragmentViewModel(Context context) {
        super(context);
        mContext = context;
        initUI();
        setManager();
    }

    private void initUI(){
        isProgressBarShowing.set(false);
        isRefreshing.set(false);

        mList = new ArrayList<>();
        for(ClassifyDetailEntity entity: loadData()){
            ClassifyItemEntity itemEntity = new ClassifyItemEntity();
            itemEntity.setName(entity.getClassify_name());
            itemEntity.setImg(entity.getClassify_img());

            mList.add(itemEntity);
        }

        for(ClassifyItemEntity entity: mList){
            ItemClassifyViewModel viewModel = new ItemClassifyViewModel(mContext, entity);
            viewModels.add(viewModel);
        }
    }

    //拿到数据，从本地数据库或者从网络获取
    private ArrayList<ClassifyDetailEntity> loadData(){
        ArrayList<ClassifyDetailEntity> list = new ArrayList<>();
        ClassifyDetailEntityDao dao = MyApplication.daoSession.getClassifyDetailEntityDao();
        list = (ArrayList<ClassifyDetailEntity>)dao.loadAll();

        return list;
    }

    private void setManager(){
        GridLayoutManager glm = new GridLayoutManager(mContext,2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return 1;
            }
        });
        mGridLayoutManager.set(glm);

    }

//    /*
//    * 下拉刷新
//     */
//
//    public final ReplyCommand onRefreshCommand = new ReplyCommand(()->{
//        isRefreshing.set(true);
//        isRefreshing.set(false);
//    });

    @Override
    public void destroy() {

    }

    //对RecyclerView的逻辑处理
    public class ItemClassifyViewModel extends BaseViewModel{

        private Context mContext;
        private ClassifyItemEntity mEntity;

        public final ObservableField<String > mImage = new ObservableField<>();
        public final ObservableField<String > mClassify = new ObservableField<>();

        public ItemClassifyViewModel(Context context, ClassifyItemEntity entity){
            super(context);
            mContext = context;
            if (null != entity){
                mEntity = entity;
                setData();
            }else{
                setData();
            }

        }

        private void setData(){
            mImage.set(mEntity.getImg());
            mClassify.set(mEntity.getName());
        }

        public final ReplyCommand<Integer> toDetial = new ReplyCommand<Integer>(()->{
            Intent intent = new Intent(mContext,ResultActivity.class);
            mContext.startActivity(intent);
        });


        @Override
        public void destroy() {

        }
    }
}
