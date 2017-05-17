package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.v7.widget.GridLayoutManager;


import com.bumptech.glide.Glide;
import com.cuit.drawdream.drawdream.BR;
import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.bean.ordinary.ItemIndexEntity;
import com.cuit.drawdream.drawdream.utils.tool.GlideImageLoader;


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
    private static final int ITEM_HEADER_AUTHOR = 1;
    private static final int ITEM_HEADER_RECYCLER = 0;
    private static final int ITEM_GENERAL = 2;

    private Context mContext;
    private ArrayList<ItemIndexEntity> mList;

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

    private void setManager() {
        GridLayoutManager glm = new GridLayoutManager(mContext,2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
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
        mList = new ArrayList<>();

        ArrayList<String > images = new ArrayList<>();

        images.add("http://img1.178.com/acg1/201705/288466908147/288471126293.jpg");
        images.add("http://img0.178.com/acg1/201705/288926238343/288926825037.jpg");
        images.add("http://img1.178.com/acg1/201705/288466908147/288471126293.jpg");
        images.add("http://img0.178.com/acg1/201705/288926238343/288926825037.jpg");

        ItemIndexViewModel viewModelForRecycler = new ItemIndexViewModel(mContext,
                new ItemIndexEntity(images),
                ITEM_HEADER_RECYCLER);
        viewModels.add(viewModelForRecycler);

        ItemIndexViewModel viewModelForAuthor = new ItemIndexViewModel(mContext,
                null,
                ITEM_HEADER_AUTHOR);
        viewModels.add(viewModelForAuthor);

        mList.add(new ItemIndexEntity("http://img3.178.com/acg1/201705/288980777014/288982536952.jpg",
                "今日早报",
                "动漫",
                "Fragd",
                "09-12"));
        mList.add(new ItemIndexEntity("http://img3.178.com/acg1/201705/288980777014/288982536952.jpg",
                "今日早报",
                "动漫",
                "Fragd",
                "09-12"));
        mList.add(new ItemIndexEntity("http://img3.178.com/acg1/201705/288980777014/288982536952.jpg",
                "今日早报",
                "动漫",
                "Fragd",
                "09-12"));
        mList.add(new ItemIndexEntity("http://img3.178.com/acg1/201705/288980777014/288982536952.jpg",
                "今日早报",
                "动漫",
                "Fragd",
                "09-12"));
        mList.add(new ItemIndexEntity("http://img3.178.com/acg1/201705/288980777014/288982536952.jpg",
                "今日早报",
                "动漫",
                "Fragd",
                "09-12"));
        mList.add(new ItemIndexEntity("http://img3.178.com/acg1/201705/288980777014/288982536952.jpg",
                "今日早报",
                "动漫",
                "Fragd",
                "09-12"));

        for(ItemIndexEntity entity :mList){
            ItemIndexViewModel viewModel = new ItemIndexViewModel(mContext,entity,ITEM_GENERAL);
            viewModels.add(viewModel);
        }
        for(ItemIndexEntity entity :mList){
            ItemIndexViewModel viewModel = new ItemIndexViewModel(mContext,entity,ITEM_GENERAL);
            viewModels.add(viewModel);
        }
    }


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

        @Override
        public void destroy() {

        }
    }

}
