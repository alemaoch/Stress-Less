package com.worldworkssys.stressless.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;



import com.worldworkssys.stressless.R;
import com.worldworkssys.stressless.databinding.ActivityAddCoursesBinding;
import com.worldworkssys.stressless.model.AddCourse;
import com.worldworkssys.stressless.persistance.StressLessRepository;

public class AddCoursesActivity extends AppCompatActivity {

    ActivityAddCoursesBinding binding;
    Context mContext;
    StressLessRepository stressLessRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_courses);
        stressLessRepository = new StressLessRepository(getApplicationContext());
        mContext = AddCoursesActivity.this;

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater factory = LayoutInflater.from(mContext);
                final View customDialog = factory.inflate(R.layout.add_course_dialog_box, null);
                final AlertDialog addCourse = new AlertDialog.Builder(mContext).create();
                addCourse.setView(customDialog);
                //addCourse.setCancelable(false);

                customDialog.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText courseName = customDialog.findViewById(R.id.et_course_name);
                        String name = courseName.getText().toString().trim();
                        AddCourse course = new AddCourse(name);
                        stressLessRepository.insertCourse(course);
                        addCourse.dismiss();
                        //binding.btnSave.setVisibility(View.VISIBLE);

                        LayoutInflater factory = LayoutInflater.from(mContext);
                        final View customDialog = factory.inflate(R.layout.saved_dialog_box, null);
                        final AlertDialog saved = new AlertDialog.Builder(mContext).create();
                        saved.setView(customDialog);
                        saved.setCancelable(false);
                        saved.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent intent = new Intent(mContext, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 2000);
                    }
                });
                addCourse.show();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
