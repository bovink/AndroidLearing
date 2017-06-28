package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Button;

import com.bovink.androidlearing.popupwindow.ListPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_popupwindow)
    Button popupWindowTextView;

    ListPopupWindow listPopupWindow;
    boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.tv_popupwindow)
    void popupwindow() {

        if (isShow) {
            listPopupWindow.dismiss();
        } else {
            List<String> list = new ArrayList<>();
            list.add("string");
            list.add("英语");
            listPopupWindow = new ListPopupWindow(this, list);
            listPopupWindow.showAtLocation(popupWindowTextView, Gravity.BOTTOM, 0, -100);
        }
        isShow = !isShow;
    }
}
