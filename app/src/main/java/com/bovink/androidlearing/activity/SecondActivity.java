package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bovink.androidlearing.CustomView;
import com.bovink.androidlearing.SecondViewModel;
import com.bovink.androidlearing.databinding.SecondBind;


/**
 * @author fox
 * @since 2018/03/06
 */

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SecondBind bind = SecondBind.inflate(getLayoutInflater());
        bind.setView(this);
        setContentView(bind.getRoot());
        bind.setViewModel(new SecondViewModel(false));
        bind.setHandler(new EventHandler());
    }

    public class EventHandler {

        public void clickCustom(View view) {

            CustomView customView = (CustomView) view;

            Toast.makeText(SecondActivity.this, customView.getTime(), Toast.LENGTH_SHORT).show();
        }
    }
}
