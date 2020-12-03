package com.uzair.roomdatabasepractice.Dao;

import androidx.room.Insert;
import androidx.room.Query;

import com.uzair.roomdatabasepractice.Entity.User;

import java.util.List;

@androidx.room.Dao
public interface UserDao
{
    @Insert
    Long insertUser(User user);

    @Query("Select user_name from user")
    List<String> getUserList();

    @Query("Delete from User where user_name = :userName ")
    void deleteUserBy(String userName);


}
