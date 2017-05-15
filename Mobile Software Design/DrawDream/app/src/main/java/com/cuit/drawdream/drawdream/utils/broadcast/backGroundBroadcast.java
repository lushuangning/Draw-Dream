package com.cuit.drawdream.drawdream.utils.broadcast;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cuit.drawdream.drawdream.utils.service.MyService;


/**
 * Created by JWZ on 2016/11/8 0008.
 */

public class backGroundBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
            //检查Service状态
            boolean isServiceRunning = false;
            ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo service :manager.getRunningServices(Integer.MAX_VALUE)) {
                if("com.sw.app.cons.utils.service.MyService".equals(service.service.getClassName()))
                //Service的类名
                {
                    isServiceRunning = true;
                }

            }
            if (!isServiceRunning) {
                Intent i = new Intent(context, MyService.class);
                context.startService(i);
            }
        }
    }
}
