package cuit.drawdream.viewmodel;

import android.content.Context;
import android.widget.Toast;

import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * Created by sailwish008 on 2017/2/15 0015.
 */

public class LoginActivityViewModel extends BaseViewModel{
    private Context context;
    public LoginActivityViewModel(Context context){
        super(context);
        this.context = context;
    }

    /**
     * 删除文本内容
     */
    public final ReplyCommand click = new ReplyCommand(() -> {
        Toast.makeText(context,"你点击了button",Toast.LENGTH_LONG).show();
    });
    @Override
    public void destroy() {

    }
}
