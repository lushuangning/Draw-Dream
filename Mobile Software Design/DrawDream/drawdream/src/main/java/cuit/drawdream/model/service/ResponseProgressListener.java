package cuit.drawdream.model.service;

/**
 * Created by JWZ on 2016/9/29 0029.
 */
public interface ResponseProgressListener {

    /**
    *  方法的简要概括
    *  @param  progress 以下载或上传字符数 ， total:总字节数 done:是否完成
    *  @return
    */
    void onProgress(long progress, long total, boolean done);
}
