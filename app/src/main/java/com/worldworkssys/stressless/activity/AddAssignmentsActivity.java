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
import com.worldworkssys.stressless.adapter.ViewAssignmentsAdapter;
import com.worldworkssys.stressless.databinding.ActivityAddAssignmentsBinding;
import com.worldworkssys.stressless.model.AddAssignment;
import com.worldworkssys.stressless.persistance.StressLessRepository;

import java.util.ArrayList;
import java.util.List;

public class AddAssignmentsActivity extends AppCompatActivity {

    ActivityAddAssignmentsBinding binding;
    Context mContext;
    ArrayList<AddAssignment> assignments = new ArrayList<>();
    private ViewAssignmentsAdapter viewAssignmentsAdapter;
    StressLessRepository stressLessRepository;
    List<AddAssignment> allAssignments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_assignments);
        stressLessRepository = new StressLessRepository(getApplicationContext());
        mContext = AddAssignmentsActivity.this;
        retrieveAssignments();
        initRecyclerView();


        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

        binding.btnAddAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater factory = LayoutInflater.from(mContext);
                final View customDialog = factory.inflate(R.layout.add_assignment_dialog_box, null);
                final AlertDialog addAssignment = new AlertDialog.Builder(mContext).create();
                addAssignment.setView(customDialog);
                addAssignment.setCancelable(false);
                customDialog.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addAssignment.dismiss();
                    }
                });

                customDialog.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText assignment = customDialog.findViewById(R.id.et_assignment_name);
                        EditText wei = customDialog.findViewById(R.id.et_weight);
                        EditText gra = customDialog.findViewById(R.id.et_grade);

                        String assignmentName = assignment.getText().toString().trim();

                        String weight = wei.getText().toString().trim();
                        double finalWeight = Double.parseDouble(weight);

                        String grade = gra.getText().toString().trim();
                        double finalGrade = Double.parseDouble(grade);

                        int courseId = getIntent().getIntExtra("id", 0);

                        AddAssignment addAssignment1 = new AddAssignment(assignmentName, finalWeight, finalGrade, courseId);
                        stressLessRepository.insertAssignment(addAssignment1);

                        addAssignment.dismiss();

                        LayoutInflater factory = LayoutInflater.from(mContext);
                        final View customDialog = factory.inflate(R.layout.saved_dialog_box, null);
                        final AlertDialog saved = new AlertDialog.Builder(mContext).create();
                        saved.setView(customDialog);
                        saved.setCancelable(false);
                        saved.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                finish();
                            }
                        }, 2000);
                    }
                });
                addAssignment.show();
            }
        });
    }

    private void retrieveAssignments() {
        int courseId = getIntent().getIntExtra("id", 0);
        allAssignments = stressLessRepository.getAllAssignments(courseId);
        assignments.addAll(allAssignments);
    }

    private void initRecyclerView() {
        //new ItemTouchHelper(itemTouchhelperCallback).attachToRecyclerView(binding.coursesRV);
        viewAssignmentsAdapter = new ViewAssignmentsAdapter(assignments);
        binding.setAdapter(viewAssignmentsAdapter);

    }
}
