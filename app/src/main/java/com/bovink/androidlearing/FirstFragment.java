package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Retina975 on 17/6/6.
 */

public class FirstFragment extends Fragment {
    private RxBus rxBus;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_first, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rxBus = ((MainActivity) getActivity()).getRxBus();
    }


    @OnClick(R.id.iv_rectangle)
    public void click() {
        if (rxBus.hasObservers()) {
            rxBus.send(new MainActivity.TapEvent());
//            System.out.println("FirstFragment.click");
        }
    }
}
