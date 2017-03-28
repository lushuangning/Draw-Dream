package cuit.drawdream.view;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.yangq.drawdream.R;
import com.example.yangq.drawdream.databinding.ActivityLoginBinding;
import cuit.drawdream.viewmodel.LoginActivityViewModel;

public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding binding;
    private LoginActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        viewModel = new LoginActivityViewModel(this);
        binding.setViewmodel(viewModel);
    }

    @Override
    protected void destroy() {

    }
}
