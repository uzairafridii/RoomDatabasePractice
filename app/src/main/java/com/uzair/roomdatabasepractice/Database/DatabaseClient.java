package com.uzair.roomdatabasepractice.Database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient
{
    private static Context ctx;
    private static DatabaseClient databaseClient;
    private AppDatabase appDatabase;


    private DatabaseClient(Context ctx) {
        this.ctx = ctx;

        //create database
        appDatabase = Room.databaseBuilder(ctx,AppDatabase.class,"MyUsers").build();
    }


    // get the instance of current class
    public static synchronized DatabaseClient getInstance(Context ctx)
    {
        if(databaseClient == null)
        {
            databaseClient = new DatabaseClient(ctx);
        }

        return  databaseClient;
    }


    // get the database
    public AppDatabase getAppDatabase()
    {
        return  appDatabase;
    }


}
