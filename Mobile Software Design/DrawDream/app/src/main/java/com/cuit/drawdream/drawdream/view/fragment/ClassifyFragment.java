package com.cuit.drawdream.drawdream.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.databinding.FragmentClassifyBinding;
import com.cuit.drawdream.drawdream.viewmodel.ClassifyFragmentViewModel;
/**
 * Created by sunnylu on 2017/6/13.
 */

public class ClassifyFragment  extends Fragment{
    private FragmentClassifyBinding classifyBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        classifyBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_classify, container, false);
        ClassifyFragmentViewModel viewModel = new ClassifyFragmentViewModel(getContext());
        classifyBinding.setClassifyFragmentViewModel(viewModel);
        return classifyBinding.getRoot();
    }
}
