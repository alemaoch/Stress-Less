package com.worldworkssys.stressless.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CourseWithAssignments {

    @Embedded
    public AddCourse course;
    @Relation(
            parentColumn = "id",
            entityColumn = "courseId"
    )
    public List<AddAssignment> assignmentList;
}
