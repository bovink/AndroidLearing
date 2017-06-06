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
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Retina975 on 17/6/6.
 */

public class SecondFragment extends Fragment {
    private RxBus rxBus;
    private CompositeDisposable disposable;
    @BindView(R.id.tv_hint)
    TextView tv_hint;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_second, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rxBus = ((MainActivity) getActivity()).getRxBus();
    }

    @Override
    public void onStart() {
        super.onStart();

        disposable = new CompositeDisposable();

        disposable.add(rxBus.toObservable().subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                tv_hint.setVisibility(View.VISIBLE);

            }
        }));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
