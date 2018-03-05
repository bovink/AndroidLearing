package com.bovink.androidlearing;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.databinding.MainBind;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        MainBind binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Person person = new Person("jhon", "23");
        View view = new View(Color.parseColor("#66ccff"));

        Map<String, String> map = new HashMap<>();
        map.put("ttt", "hhhh");

        binding.setMap(map);
        binding.setColorview(view);
        binding.setPerson(person);

    }
}
