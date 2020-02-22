package com.worldworkssys.stressless.async;

import android.os.AsyncTask;

import com.worldworkssys.stressless.model.AddCourse;
import com.worldworkssys.stressless.persistance.StressLessDao;

public class InsertCourseAsyncTask extends AsyncTask<AddCourse, Void, Void> {

    private StressLessDao stressLessDao;

    public InsertCourseAsyncTask(StressLessDao dao) {
        stressLessDao = dao;
    }

    @Override
    protected Void doInBackground(AddCourse... addCourse) {
        stressLessDao.course(addCourse);
        return null;
    }
}
