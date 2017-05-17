package com.cuit.drawdream.drawdream.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import static android.view.Gravity.BOTTOM;
import static android.view.Gravity.CENTER;

/**
 * Created by husong on 7/29/16.
 */
public class ToastUtils {

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void showToastAtCenterOfScreen(Context appCxt, int resId) {
        Toast toast = Toast.makeText(appCxt, resId, Toast.LENGTH_SHORT);
        toast.setGravity(CENTER, 0, 0);
        toast.show();
    }

    public static void showToastAtBottomOfScreen(Context appCxt, int resId) {
        Toast toast = Toast.makeText(appCxt, resId, Toast.LENGTH_SHORT);
        toast.setGravity(BOTTOM, 0, 0);
        toast.show();
    }

    public static void showToastAtCenterOfScreen(Context appCxt, String msg) {
        Toast toast = Toast.makeText(appCxt, msg, Toast.LENGTH_SHORT);
        toast.setGravity(CENTER, 0, 0);
        toast.show();
    }

    public static void showToastAtBottomOfScreen(Context appCxt, String msg) {
        Toast toast = Toast.makeText(appCxt, msg, Toast.LENGTH_SHORT);
        toast.setGravity(BOTTOM, 0, 0);
        toast.show();
    }

//    public static void showShortToast(final CharSequence text){
//        sHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                showToast(text, Toast.LENGTH_SHORT);
//            }
//        });
//    }
}
