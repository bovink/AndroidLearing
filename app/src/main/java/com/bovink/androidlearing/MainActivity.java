package com.bovink.androidlearing;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import com.bovink.androidlearing.databinding.MainActBinding;
import com.bovink.androidlearing.databinding.ViewStubBinding;


public class MainActivity extends AppCompatActivity {

    MainActBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MainActBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final Person person = new Person("li", "23", 1, 2);
        binding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                ViewStubBinding viewStubBinding = DataBindingUtil.bind(inflated);
                viewStubBinding.setPerson(person);


            }
        });

        EventHandler handler = new EventHandler();
        binding.setHandler(handler);
    }

    public class EventHandler {

        public void showView() {

            if (!binding.viewStub.isInflated()) {
                binding.viewStub.getViewStub().inflate();
            }

        }
    }


}
