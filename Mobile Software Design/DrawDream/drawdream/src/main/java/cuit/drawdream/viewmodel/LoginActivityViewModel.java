package cuit.drawdream.viewmodel;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.ObservableField;
import android.widget.Toast;

import com.kelin.mvvmlight.command.ReplyCommand;

/**
 * Created by sailwish008 on 2017/2/15 0015.
 */

public class LoginActivityViewModel extends BaseViewModel{
    private Context context;
    public final ObservableField<String> mListener = new ObservableField<>();
    public String username;
    public String password;

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

    public final ReplyCommand setUsername = new ReplyCommand(()->{
        mListener.set(username);
    });


    @Override
    public void destroy() {

    }


}
