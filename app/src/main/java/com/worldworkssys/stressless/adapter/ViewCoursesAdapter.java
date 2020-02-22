package com.worldworkssys.stressless.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.worldworkssys.stressless.activity.CourseInfoActivity;
import com.worldworkssys.stressless.R;
import com.worldworkssys.stressless.databinding.VhViewCourseBinding;
import com.worldworkssys.stressless.model.AddCourse;

import java.util.ArrayList;

public class ViewCoursesAdapter extends RecyclerView.Adapter<ViewCoursesAdapter.ViewHolder> {

    private ArrayList<AddCourse> courses;
    private VhViewCourseBinding binding;
    Context mContext;

    public ViewCoursesAdapter(ArrayList<AddCourse> cours, Context context) {
        this.courses = cours;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.vh_view_course, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final AddCourse currentAddCourse = courses.get(position);
        int count = position + 1;
        binding.tvCourseNumber.setText("Course " + count);
        holder.binding.setModel(currentAddCourse);


        holder.binding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CourseInfoActivity.class);
                int id = currentAddCourse.getId();
                String courseName = currentAddCourse.getName();
                intent.putExtra("id", id);
                intent.putExtra("courseName", courseName);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        VhViewCourseBinding binding;

        private ViewHolder(@NonNull VhViewCourseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}