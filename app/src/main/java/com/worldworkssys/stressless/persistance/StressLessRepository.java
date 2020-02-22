package com.worldworkssys.stressless.persistance;

import android.content.Context;

import com.worldworkssys.stressless.async.AllAssignmentsAsyncTask;
import com.worldworkssys.stressless.async.AllCoursesAsyncTask;
import com.worldworkssys.stressless.async.DeleteCourseAsyncTask;
import com.worldworkssys.stressless.async.InsertAssignmentAsyncTask;
import com.worldworkssys.stressless.async.InsertCourseAsyncTask;
import com.worldworkssys.stressless.async.InsertStudentDetailsAsyncTask;
import com.worldworkssys.stressless.async.InsertUserAsyncTask;
import com.worldworkssys.stressless.async.LoginUserAsyncTask;
import com.worldworkssys.stressless.model.AddAssignment;
import com.worldworkssys.stressless.model.AddCourse;
import com.worldworkssys.stressless.model.StudentDetails;
import com.worldworkssys.stressless.model.UserAccount;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StressLessRepository {

    private StressLessDatabase stressLessDatabase;
    private List<UserAccount> allData;
    private List<AddCourse> allCourses;
    private List<AddAssignment> allAssignments;

    public StressLessRepository(Context context) {

        stressLessDatabase = StressLessDatabase.getInstance(context);
    }

    public void insertUser(UserAccount userAccount) {
        new InsertUserAsyncTask(stressLessDatabase.getStressLessDao()).execute(userAccount);
    }

//    public void userLoginTask(String userName) {
//        new InsertUserAsyncTask(stressLessDatabase.getStressLessDao()).execute(userName);
//    }

//

    public List<UserAccount> getAllData() {
        try {
            allData = new LoginUserAsyncTask(stressLessDatabase.getStressLessDao()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allData;
    }
    public void insertStudentDetails(StudentDetails studentDetails) {
        new InsertStudentDetailsAsyncTask(stressLessDatabase.getStressLessDao()).execute(studentDetails);
    }

    public void insertCourse(AddCourse addCourse) {
        new InsertCourseAsyncTask(stressLessDatabase.getStressLessDao()).execute(addCourse);
    }

    public List<AddCourse> getAllCourses() {
        try {
            allCourses = new AllCoursesAsyncTask(stressLessDatabase.getStressLessDao()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allCourses;
    }

    public void deleteCourse(AddCourse course) {
        new DeleteCourseAsyncTask(stressLessDatabase.getStressLessDao()).execute(course);
    }

    public void insertAssignment(AddAssignment addAssignment) {
        new InsertAssignmentAsyncTask(stressLessDatabase.getStressLessDao()).execute(addAssignment);
    }

    public List<AddAssignment> getAllAssignments(int courseId) {
        try {
            allAssignments = new AllAssignmentsAsyncTask(stressLessDatabase.getStressLessDao()).execute(courseId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allAssignments;
    }
}
