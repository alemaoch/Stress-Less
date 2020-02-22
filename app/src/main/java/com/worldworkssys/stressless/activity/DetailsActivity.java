package com.worldworkssys.stressless.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.worldworkssys.stressless.R;
import com.worldworkssys.stressless.databinding.ActivityDetailsBinding;
import com.worldworkssys.stressless.model.StudentDetails;
import com.worldworkssys.stressless.persistance.StressLessRepository;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    private static final String TAG = "DetailsActivity";
    StressLessRepository stressLessRepository;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        mContext = DetailsActivity.this;
        stressLessRepository = new StressLessRepository(getApplicationContext());

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        binding.btnSlide.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
//            @Override
//            public void onSlideComplete(@NonNull SlideToActView view) {
//                detailsValidation();
//            }
//        });

        binding.btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailsValidation();
            }
        });
    }

    private void detailsValidation() {

        String university = binding.etUniversityName.getText().toString().trim();
        String semester = binding.etCampusAddress.getText().toString().trim();
        String program = binding.etSemester.getText().toString().trim();

        if (university.isEmpty()) {
            Toast.makeText(mContext, "Enter University name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (semester.isEmpty()) {
            Toast.makeText(mContext, "Enter Semester", Toast.LENGTH_SHORT).show();
            return;
        }
        if (program.isEmpty()) {
            Toast.makeText(mContext, "Enter Program", Toast.LENGTH_SHORT).show();
            return;
        }
        StudentDetails studentDetails = new StudentDetails(university, semester, program);
        addDetails(studentDetails);
    }

    private void addDetails(StudentDetails studentDetails) {
        stressLessRepository.insertStudentDetails(studentDetails);
        Intent intent = new Intent(DetailsActivity.this, AddCoursesActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Details Added Successfully", Toast.LENGTH_SHORT).show();
    }
}
