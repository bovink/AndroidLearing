package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.databinding.MainActBinding;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActBinding binding = MainActBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }


}
