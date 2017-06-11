package com.cuit.drawdream.drawdream.viewmodel;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.util.Log;

import com.kelin.mvvmlight.command.ReplyCommand;


/**
 * ClassName : MyInfoMgtActivityViewModel
 * Created by yangq
 * On 2017/6/11.
 * Desc :
 */

public class MyInfoMgtActivityViewModel extends BaseViewModel {

    public final ObservableBoolean isMan = new ObservableBoolean(true);
    public final ObservableBoolean isWoman = new ObservableBoolean(false);

    public MyInfoMgtActivityViewModel(Context context) {
        super(context);

    }

    public final ReplyCommand handIn = new ReplyCommand(()->{
        if(isMan.get()){
            Log.d("The gander is ","man");
        }else if(isWoman.get()){
            Log.d("The gander is ","woman");
        }else {
            Log.d("The gander is ","null");
        }

    });

    @Override
    public void destroy() {

    }
}
