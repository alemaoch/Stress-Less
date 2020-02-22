package com.worldworkssys.stressless.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "assignments")
public class AddAssignment {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private double weight;

    private double grade;

    private int courseId;

    @Ignore
    public AddAssignment() {
    }

    public AddAssignment(String name, double weight, double grade, int courseId) {
        this.name = name;
        this.weight = weight;
        this.grade = grade;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}