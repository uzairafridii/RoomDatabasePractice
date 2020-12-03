package com.uzair.roomdatabasepractice.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User
{

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private String userId;

    @ColumnInfo(name = "user_course")
    private String userCourse;

    @ColumnInfo(name = "user_name")
    private String userName;

    public User(@NonNull String userId, String userCourse, String userName) {
        this.userId = userId;
        this.userCourse = userCourse;
        this.userName = userName;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    public String getUserCourse() {
        return userCourse;
    }

    public void setUserCourse(String userCourse) {
        this.userCourse = userCourse;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
