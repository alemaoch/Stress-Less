package com.worldworkssys.stressless.async;

import android.os.AsyncTask;

import com.worldworkssys.stressless.model.AddAssignment;
import com.worldworkssys.stressless.persistance.StressLessDao;

import java.util.List;

public class AllAssignmentsAsyncTask extends AsyncTask<Integer, Void, List<AddAssignment>> {

    private StressLessDao stressLessDao;

    public AllAssignmentsAsyncTask(StressLessDao dao) {
        stressLessDao = dao;
    }

    @Override
    protected List<AddAssignment> doInBackground(Integer... integers) {
        List<AddAssignment> getAllAssignments;
        getAllAssignments = stressLessDao.getAllAssignments(integers[0]);
        return getAllAssignments;
    }
}
