package com.worldworkssys.stressless.async;

import android.os.AsyncTask;

import com.worldworkssys.stressless.model.StudentDetails;
import com.worldworkssys.stressless.persistance.StressLessDao;

public class InsertStudentDetailsAsyncTask extends AsyncTask<StudentDetails, Void, Void> {

    private StressLessDao stressLessDao;

    public InsertStudentDetailsAsyncTask(StressLessDao dao) {
        stressLessDao = dao;
    }

    @Override
    protected Void doInBackground(StudentDetails... studentDetails) {
        stressLessDao.studentDetails(studentDetails);
        return null;
    }
}
