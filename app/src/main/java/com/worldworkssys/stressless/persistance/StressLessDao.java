package com.worldworkssys.stressless.persistance;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.worldworkssys.stressless.model.AddAssignment;
import com.worldworkssys.stressless.model.AddCourse;
import com.worldworkssys.stressless.model.StudentDetails;
import com.worldworkssys.stressless.model.UserAccount;

import java.util.List;

@Dao
public interface StressLessDao {

    @Insert
    void insertUser(UserAccount... userAccount);

    @Query("select * from users")
    List<UserAccount> getDetails();

    @Insert
    void studentDetails(StudentDetails... studentDetails);

    @Insert
    void course(AddCourse... addCourse);

    @Query("select * from courses")
    List<AddCourse> getAllCourses();

    @Delete
    void deleteCourse(AddCourse... course);

    @Insert
    void insertAssignment(AddAssignment... addAssignment);

    @Query("SELECT * FROM assignments WHERE assignments.courseId LIKE :id")
    List<AddAssignment> getAllAssignments(int id);

}
