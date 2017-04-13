package cuit.drawdream.viewmodel;

import android.content.Context;

/**
 * class : IndexViewModel
 * Created by 路双宁 on 2017/3/30.
 * Desc : 主页ViewModel
 */

public class IndexViewModel extends BaseViewModel {
    private Context context;

    protected IndexViewModel(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    public void destroy() {

    }
}
