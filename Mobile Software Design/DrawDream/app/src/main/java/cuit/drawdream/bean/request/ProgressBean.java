package cuit.drawdream.bean.request;

/**
 * Created by JWZ on 2016/9/29 0029.
 */
public class ProgressBean {

    private Long ByteRead;
    private Long ContentLength;
    private Boolean Done;

    public Long getByteRead() {
        return ByteRead;
    }

    public void setByteRead(Long byteRead) {
        ByteRead = byteRead;
    }

    public Long getContentLength() {
        return ContentLength;
    }

    public void setContentLength(Long contentLength) {
        ContentLength = contentLength;
    }

    public Boolean getDone() {
        return Done;
    }

    public void setDone(Boolean done) {
        Done = done;
    }




}
