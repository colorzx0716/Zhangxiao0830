package com.bwie.zhangxiao0830.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.zhangxiao0830.R;

/**
 * Created by 张肖肖 on 2017/8/30.
 */

public class RightFragment extends Fragment {

    private View mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView==null){
            mRootView = inflater.inflate(R.layout.right_item,container,false);
        }

        return mRootView;
    }
}
