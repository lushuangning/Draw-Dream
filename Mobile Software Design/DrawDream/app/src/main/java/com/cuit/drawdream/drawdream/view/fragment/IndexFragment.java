package com.cuit.drawdream.drawdream.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.databinding.FragmentIndexBinding;
import com.cuit.drawdream.drawdream.utils.tool.GlideImageLoader;
import com.cuit.drawdream.drawdream.viewmodel.IndexFragmentViewModel;
import com.youth.banner.Banner;

import java.util.ArrayList;

/**
 * class :    IndexFragment
 * Created by yangq
 * At         2017/3/28.
 * Desc :
 */

public class IndexFragment extends Fragment {

    private FragmentIndexBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_index, container, false);
        IndexFragmentViewModel viewModel = new IndexFragmentViewModel(getContext());
        mBinding.setIndexFragmentViewModel(viewModel);

        return mBinding.getRoot();
    }
}
