package cuit.drawdream.utils.tool;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import cuit.drawdream.bean.request.ProgressBean;


/**
 * Created by JWZ on 2016/9/29 0029.
 */
public abstract class ProgressHandler {

    protected abstract void sendMessage(ProgressBean progressBean);

    protected abstract void handleMessage(Message message);

    protected abstract void onProgress(long progress, long total, boolean done);

    protected static class ResponseHandler extends Handler{

        private ProgressHandler mProgressHandler;
        public ResponseHandler(ProgressHandler mProgressHandler, Looper looper) {
            super(looper);
            this.mProgressHandler = mProgressHandler;
        }

        @Override
        public void handleMessage(Message msg) {
            mProgressHandler.handleMessage(msg);
        }
    }
}

