package com.worldworkssys.stressless.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.worldworkssys.stressless.R;
import com.worldworkssys.stressless.adapter.ViewCoursesAdapter;
import com.worldworkssys.stressless.databinding.ActivityHomeBinding;
import com.worldworkssys.stressless.model.AddCourse;
import com.worldworkssys.stressless.persistance.StressLessRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    ArrayList<AddCourse> courses = new ArrayList<>();
    private ViewCoursesAdapter viewCoursesAdapter;
    Context mContext;
    StressLessRepository stressLessRepository;
    List<AddCourse> allCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        stressLessRepository = new StressLessRepository(getApplicationContext());
        mContext = HomeActivity.this;
        initRecyclerView();
        retrieveCourses();

        binding.btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddCoursesActivity.class);
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
    }

    private void retrieveCourses() {
        allCourses = stressLessRepository.getAllCourses();
        courses.addAll(allCourses);
    }

    private void deleteCourse(AddCourse course) {
        courses.remove(course);
        viewCoursesAdapter.notifyDataSetChanged();
        stressLessRepository.deleteCourse(course);
        Toast.makeText(mContext, "Course successfully deleted", Toast.LENGTH_SHORT).show();
    }

    private void initRecyclerView() {
        new ItemTouchHelper(itemTouchhelperCallback).attachToRecyclerView(binding.coursesRV);
        viewCoursesAdapter = new ViewCoursesAdapter(courses, this);
        binding.setAdapter(viewCoursesAdapter);

    }

    private ItemTouchHelper.SimpleCallback itemTouchhelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteCourse(courses.get(viewHolder.getAdapterPosition()));
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
