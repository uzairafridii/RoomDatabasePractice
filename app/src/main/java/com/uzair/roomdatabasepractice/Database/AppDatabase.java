package com.uzair.roomdatabasepractice.Database;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

import com.uzair.roomdatabasepractice.Dao.UserDao;
import com.uzair.roomdatabasepractice.Entity.User;

@Database(entities = {User.class} , version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract UserDao getDao();
}
