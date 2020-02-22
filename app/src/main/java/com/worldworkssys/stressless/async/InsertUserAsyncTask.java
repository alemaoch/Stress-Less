package com.worldworkssys.stressless.async;

import android.os.AsyncTask;

import com.worldworkssys.stressless.model.UserAccount;
import com.worldworkssys.stressless.persistance.StressLessDao;

public class InsertUserAsyncTask extends AsyncTask<UserAccount, Void, Void> {

    private StressLessDao stressLessDao;

    public InsertUserAsyncTask(StressLessDao dao) {
        stressLessDao = dao;
    }

    @Override
    protected Void doInBackground(UserAccount... userAccount) {
        stressLessDao.insertUser(userAccount);
        return null;
    }
}
