package com.bovink.androidlearing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bovink.androidlearing.activity.RecyclerActivity;
import com.bovink.androidlearing.activity.SecondActivity;
import com.bovink.androidlearing.databinding.MainBind;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Person person;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mContext = this;
        MainBind binding = MainBind.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setView(this);
        person = new Person("jhon", "99", 1, 2);
        View view = new View(Color.parseColor("#66ccff"));

        Map<String, String> map = new HashMap<>();
        map.put("ttt", "hhhh");

        binding.setMap(map);
        binding.setColorview(view);
        binding.setPerson(person);
        EventHandler handler = new EventHandler();
        binding.setHandler(handler);

    }


    public class EventHandler {

        public void changeName() {

            Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
//            person.setName(null);
//            person.setAge("9999");
            person.setNumber1(person.getNumber1() + 1);
            person.setNumber2(person.getNumber2() + 1);
        }

        public void showSecond() {
            Intent intent = new Intent(mContext, SecondActivity.class);
            startActivity(intent);
        }

        public void showRecycler() {
            Intent intent = new Intent(mContext, RecyclerActivity.class);
            startActivity(intent);
        }
    }
}
