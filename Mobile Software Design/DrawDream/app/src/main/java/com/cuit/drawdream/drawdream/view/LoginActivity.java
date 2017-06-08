package com.cuit.drawdream.drawdream.view;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.databinding.ActivityLoginBinding;
import com.cuit.drawdream.drawdream.viewmodel.LoginActivityViewModel;

import java.util.List;

public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding binding;
    private LoginActivityViewModel viewModel;
    private List<Student > list ;

    public static Activity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        viewModel = new LoginActivityViewModel(this);
        binding.setViewmodel(viewModel);
    }

    @Override
    protected void destroy() {

    }

    public class Student{

        private String name;

        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
