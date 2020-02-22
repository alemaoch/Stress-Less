package com.worldworkssys.stressless.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class AddCourse {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    @Ignore
    public AddCourse() {
    }

    public AddCourse(String name) {
        this.name = name;
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
}
