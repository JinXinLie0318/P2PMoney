package com.example.administrator.p2pmoney.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.p2pmoney.R;
import com.example.administrator.p2pmoney.util.UiUtils;

/**
 * Created by Administrator on 2016/2/24.
 */
public class MoreFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = UiUtils.getXmlView(R.layout.fragment_more);
//        View view = View.inflate(container.getContext(), R.layout.activity_more, null);
        return view;
    }
}
