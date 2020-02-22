package com.worldworkssys.stressless.async;

import android.os.AsyncTask;

import com.worldworkssys.stressless.model.AddCourse;
import com.worldworkssys.stressless.persistance.StressLessDao;

import java.util.List;

public class AllCoursesAsyncTask extends AsyncTask<Void, Void, List<AddCourse>> {

    private StressLessDao stressLessDao;

    public AllCoursesAsyncTask(StressLessDao dao) {
        stressLessDao = dao;
    }

    @Override
    protected List<AddCourse> doInBackground(Void... voids) {
        List<AddCourse> getAllCourses;
        getAllCourses =  stressLessDao.getAllCourses();
        return getAllCourses;
    }
}
