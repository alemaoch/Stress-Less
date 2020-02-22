package com.worldworkssys.stressless.async;

import android.os.AsyncTask;

import com.worldworkssys.stressless.model.UserAccount;
import com.worldworkssys.stressless.persistance.StressLessDao;

import java.util.List;

public class LoginUserAsyncTask extends AsyncTask<Void, Void, List<UserAccount>> {

    private StressLessDao stressLessDao;

    public LoginUserAsyncTask(StressLessDao dao) {
        stressLessDao = dao;
    }

    @Override
    protected List<UserAccount> doInBackground(Void... voids) {
        List<UserAccount> userAccount;
        userAccount =  stressLessDao.getDetails();
        return userAccount;
    }
}
