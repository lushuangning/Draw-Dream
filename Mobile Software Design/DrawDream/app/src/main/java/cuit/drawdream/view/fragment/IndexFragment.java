package cuit.drawdream.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drawdream.R;


/**
 * class :    IndexFragment
 * Created by yangq
 * At         2017/3/28.
 * Desc :
 */

public class IndexFragment extends Fragment {
    public static final String TAG = IndexFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_index,container,false);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }
}
