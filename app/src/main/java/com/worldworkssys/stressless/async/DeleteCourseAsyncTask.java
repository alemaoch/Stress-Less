package com.worldworkssys.stressless.async;

import android.os.AsyncTask;

import com.worldworkssys.stressless.model.AddCourse;
import com.worldworkssys.stressless.persistance.StressLessDao;

public class DeleteCourseAsyncTask extends AsyncTask<AddCourse, Void, Void> {

    private StressLessDao stressLessDao;

    public DeleteCourseAsyncTask(StressLessDao dao) {
        stressLessDao = dao;
    }

    @Override
    protected Void doInBackground(AddCourse... course) {
        stressLessDao.deleteCourse(course);
        return null;
    }
}
