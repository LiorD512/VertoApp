package com.dahanlior.vertoapp.database;

import androidx.room.RoomDatabase;


public abstract class GeneralDatabase extends RoomDatabase {

    private static final String TAG = GeneralDatabase.class.getSimpleName();

    public abstract UserDao userDao();
    public abstract QrDao qrDao();


}
