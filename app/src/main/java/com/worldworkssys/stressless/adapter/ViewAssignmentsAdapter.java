package com.worldworkssys.stressless.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.worldworkssys.stressless.R;
import com.worldworkssys.stressless.databinding.VhViewAssignmentBinding;
import com.worldworkssys.stressless.model.AddAssignment;

import java.util.ArrayList;

public class ViewAssignmentsAdapter extends RecyclerView.Adapter<ViewAssignmentsAdapter.ViewHolder> {

    private ArrayList<AddAssignment> assignments;
    private VhViewAssignmentBinding binding;

    public ViewAssignmentsAdapter(ArrayList<AddAssignment> assignments) {
        this.assignments = assignments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.vh_view_assignment, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //AddAssignment currentAssignment = assignments.get(position);
        //holder.binding.setModel(currentAssignment);
        holder.binding.tvAssignmentName.setText(assignments.get(position).getName());
        String weight = Double.toString(assignments.get(position).getWeight());
        holder.binding.etWeight.setText(weight);

        String grade = Double.toString(assignments.get(position).getGrade());
        holder.binding.etGrade.setText(grade);
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        VhViewAssignmentBinding binding;

        private ViewHolder(@NonNull VhViewAssignmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}