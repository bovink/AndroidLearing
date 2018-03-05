package com.bovink.androidlearing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bovink.androidlearing.databinding.MainBind;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        MainBind binding = MainBind.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setView(this);
        person = new Person("jhon", "23");
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

            Toast.makeText(MainActivity.this, "aaa", Toast.LENGTH_SHORT).show();
            person.setName(null);
            person.setAge("9999");
        }
    }
}
