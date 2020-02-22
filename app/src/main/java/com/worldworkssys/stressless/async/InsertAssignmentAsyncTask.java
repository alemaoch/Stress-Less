package com.worldworkssys.stressless.async;

import android.os.AsyncTask;

import com.worldworkssys.stressless.model.AddAssignment;
import com.worldworkssys.stressless.persistance.StressLessDao;

public class InsertAssignmentAsyncTask extends AsyncTask<AddAssignment, Void, Void> {

    private StressLessDao stressLessDao;

    public InsertAssignmentAsyncTask(StressLessDao dao) {
        stressLessDao = dao;
    }

    @Override
    protected Void doInBackground(AddAssignment... addAssignment) {
        stressLessDao.insertAssignment(addAssignment);
        return null;
    }
}
