package com.bovink.androidlearing.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bovink.androidlearing.Student;
import com.bovink.androidlearing.databinding.ItemStudentBinding;
import com.bovink.androidlearing.databinding.RecyclerBind;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fox
 * @since 2018/03/08
 */

public class RecyclerActivity extends AppCompatActivity {
    private Context mContext;

    private List<Student> studentList = new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerBind bind = RecyclerBind.inflate(getLayoutInflater());
        bind.setView(this);
        setContentView(bind.getRoot());

        mContext = this;

        studentList.add(new Student("lilei", "12"));
        studentList.add(new Student("shilu", "14"));
        studentList.add(new Student("ddws", "15"));
        bind.rvStu.setLayoutManager(new LinearLayoutManager(mContext));
        bind.rvStu.setAdapter(new StuAdapter());

    }

    public class StuAdapter extends RecyclerView.Adapter<StuAdapter.StuHolder> {

        @Override
        public StuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);

            ItemStudentBinding bind = ItemStudentBinding.inflate(layoutInflater, parent, false);
            return new StuHolder(bind);
        }

        @Override
        public void onBindViewHolder(StuHolder holder, int position) {
            System.out.println("StuAdapter.onBindViewHolder");

            Student student = studentList.get(position);
            holder.bind(student);

        }

        @Override
        public int getItemCount() {
            return studentList.size();
        }

        public class StuHolder extends RecyclerView.ViewHolder {
            private final ItemStudentBinding bind;

            public StuHolder(ItemStudentBinding bind) {
                super(bind.getRoot());
                this.bind = bind;
            }

            public void bind(Student student) {
                bind.setStu(student);
                bind.executePendingBindings();
            }

        }
    }

}
