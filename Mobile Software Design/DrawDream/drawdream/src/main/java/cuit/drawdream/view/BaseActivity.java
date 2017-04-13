package cuit.drawdream.view;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import cuit.drawdream.utils.tool.Config;
import cuit.drawdream.utils.tool.SharedPreferencesHelper;

import java.util.List;

/**
 * Created by hus on 9/2/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏代码
        mContext = this;
    }

    protected abstract void destroy();

    @Override
    protected void onDestroy() {
        destroy();
        super.onDestroy();
    }
    @Override
    protected void onStop(){
        super.onStop();
        if (!isAppOnForeground()) {
            //app 进入后台
            Config.isActive = false ;//记录当前已经进入后台
            SharedPreferencesHelper.WriteSharedPreferences(mContext);
        }
    }
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        SharedPreferencesHelper.ReadSharedPreferences(mContext);
        if (!Config.isActive) {
            //app 从后台唤醒，进入前台
            Config.isActive = true;
            SharedPreferencesHelper.WriteSharedPreferences(mContext);
        }
    }
    /**
     *  程序是否在前台运行
     *
     * @return
     */

    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }


}
