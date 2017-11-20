package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tiilii.jhcloud.R;
import com.tiilii.jhcloud.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * @author fox
 * @since 2017/11/20
 */

public class MessageListActivity extends BaseActivity {


    @Inject
    MessageListFragment messageListFragment;

    @Inject
    MessageListPresenter messageListPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_message_list_content, messageListFragment)
                .commit();
    }
}
