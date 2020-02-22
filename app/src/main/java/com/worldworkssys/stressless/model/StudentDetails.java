package com.worldworkssys.stressless.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "studentDetails")
public class StudentDetails {


    @PrimaryKey
    @NonNull
    private String university;

    private String semester;

    private String program;

    public StudentDetails() {
    }

    public StudentDetails(@NonNull String university, String semester, String program) {
        this.university = university;
        this.semester = semester;
        this.program = program;
    }

    @NonNull
    public String getUniversity() {
        return university;
    }

    public void setUniversity(@NonNull String university) {
        this.university = university;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
