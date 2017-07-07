package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Retina975
 * @since 2017/07/07
 */

public class MainFragment extends Fragment {
    private int index;

    @BindView(R.id.tv_index)
    TextView indexTextView;

    public static MainFragment getInstance(int index) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.index = index;
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);

        indexTextView.setText("第" + index + "个");
        return root;
    }
}
