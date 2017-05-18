package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;

/**
 * Class :  ClassifyFragmentViewModel
 * Created by sunnylu on 2017/5/18.
 *
 */

public class ClassifyFragmentViewModel extends BaseViewModel {
    protected ClassifyFragmentViewModel(Context context) {
        super(context);
    }

    @Override
    protected boolean checkNetworkAvailable() {
        return super.checkNetworkAvailable();
    }

    @Override
    protected void handleNetworkError(Throwable e) {
        super.handleNetworkError(e);
    }

    @Override
    public void destroy() {

    }
}
