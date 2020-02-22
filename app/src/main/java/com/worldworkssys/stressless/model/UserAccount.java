package com.worldworkssys.stressless.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserAccount {


    @PrimaryKey
    @NonNull
    private String userName;

    private String email;

    private String password;

    public UserAccount(@NonNull String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
