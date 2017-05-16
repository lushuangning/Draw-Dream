package com.cuit.drawdream.drawdream.model.service;

/**
 * Created by JWZ on 2016/9/29 0029.
 */
public interface RequestProgressListener {

    /**
    *  方法的简要概括
    *  @param  hasWrittenLen 上传字符数 ， totalLen:总字节数 hasFinish:是否完成
    *  @return
    */
    void onProgress(long hasWrittenLen, long totalLen, boolean hasFinish);

}
