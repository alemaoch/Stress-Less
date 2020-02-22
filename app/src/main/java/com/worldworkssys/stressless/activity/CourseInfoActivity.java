package com.worldworkssys.stressless.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.worldworkssys.stressless.R;
import com.worldworkssys.stressless.databinding.ActivityCourseInfoBinding;
import com.worldworkssys.stressless.model.AddAssignment;
import com.worldworkssys.stressless.persistance.StressLessRepository;

import java.util.List;

public class CourseInfoActivity extends AppCompatActivity {

    ActivityCourseInfoBinding binding;
    Context mContext;
    StressLessRepository stressLessRepository;
    List<AddAssignment> getAllAssignments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_course_info);
        stressLessRepository = new StressLessRepository(getApplicationContext());
        mContext = CourseInfoActivity.this;
        final String courseName = getIntent().getStringExtra("courseName").toUpperCase();
        binding.tvCourseName.setText(courseName);


        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnAddAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddAssignmentsActivity.class);
                int id = getIntent().getIntExtra("id", 0);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        binding.tvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginActivity = new Intent(mContext, LoginActivity.class);
                startActivity(loginActivity);
                finish();
            }
        });

//        binding.cardInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LayoutInflater factory = LayoutInflater.from(mContext);
//                final View customDialog = factory.inflate(R.layout.info_dialog_box, null);
//                final AlertDialog info = new AlertDialog.Builder(mContext).create();
//                info.setView(customDialog);
//                info.setCancelable(false);
//                customDialog.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        info.dismiss();
//                    }
//                });
//                info.show();
//            }
//        });

        binding.btnCalculateCurrentGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = getIntent().getIntExtra("id", 0);
                getAllAssignments = stressLessRepository.getAllAssignments(id);
                if (getAllAssignments.isEmpty()) {
                    LayoutInflater factory = LayoutInflater.from(mContext);
                    final View customDialog = factory.inflate(R.layout.error_dialog_box, null);
                    final AlertDialog error = new AlertDialog.Builder(mContext).create();
                    error.setView(customDialog);
                    error.show();

                } else {
                    double totalWeight = 0;
                    double total = 0;
                    for (int i = 0; i < getAllAssignments.size(); i++) {
                        double currentGradeWeight = getAllAssignments.get(i).getGrade() * getAllAssignments.get(i).getWeight();
                        double weight = getAllAssignments.get(i).getWeight();
                        total = total + currentGradeWeight;
                        totalWeight = totalWeight + weight;
                    }
                    double result = 0;
                    result = total / totalWeight;
                    LayoutInflater factory = LayoutInflater.from(mContext);
                    final View customDialog = factory.inflate(R.layout.current_grade_dialog_box, null);
                    final AlertDialog currentGrade = new AlertDialog.Builder(mContext).create();
                    currentGrade.setView(customDialog);
                    customDialog.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentGrade.dismiss();
                        }
                    });

                    TextView course = customDialog.findViewById(R.id.tv_course_name);
                    course.setText(courseName);

                    TextView grade = customDialog.findViewById(R.id.tv_total_grade);
                    grade.setText("" + result);

                    customDialog.findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    currentGrade.show();
                }
            }
        });
    }
}