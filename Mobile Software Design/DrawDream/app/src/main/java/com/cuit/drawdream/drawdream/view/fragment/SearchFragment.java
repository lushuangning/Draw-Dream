package com.cuit.drawdream.drawdream.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuit.drawdream.drawdream.R;
import com.cuit.drawdream.drawdream.databinding.FragmentSearchNewBinding;
import com.cuit.drawdream.drawdream.viewmodel.SearchFragmentViewModel;

/**
 * class :
 *
 * @auther 杨庆
 * data on 2017/6/28.
 * desc :
 */

public class SearchFragment extends Fragment {

    private FragmentSearchNewBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_search_new,container,false);
        SearchFragmentViewModel viewModel = new SearchFragmentViewModel(getContext());
        mBinding.setSearchViewModel(viewModel);
        return mBinding.getRoot();
    }
}
