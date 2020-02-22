package com.worldworkssys.stressless.persistance;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.worldworkssys.stressless.model.AddAssignment;
import com.worldworkssys.stressless.model.AddCourse;
import com.worldworkssys.stressless.model.StudentDetails;
import com.worldworkssys.stressless.model.UserAccount;

@Database(entities = {UserAccount.class, StudentDetails.class, AddCourse.class, AddAssignment.class}, version = 1, exportSchema = false)
public abstract class StressLessDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "stressLess_db";

    private static StressLessDatabase instance;

    static StressLessDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    StressLessDatabase.class, DATABASE_NAME).build();

        }
        return instance;
    }

    public abstract StressLessDao getStressLessDao();
}