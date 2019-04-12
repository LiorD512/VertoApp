package com.dahanlior.vertoapp.database;

import com.dahanlior.vertoapp.constants.DbSettings;
import com.dahanlior.vertoapp.model.User;
import com.dahanlior.vertoapp.utils.DistinctLiveData;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;


@Dao
public abstract class UserDao implements BasicDao<User> {

    protected static final String TABLE_NAME = DbSettings.TABLE_USER;

    @Query("SELECT count(*) FROM " + TABLE_NAME + " ORDER BY email DESC")
    public abstract int count();

    @Query("SELECT * from " + TABLE_NAME + " WHERE email = :email")
    public abstract LiveData<User> loadByEmail(String email);


    @Query("SELECT count(*) FROM " + TABLE_NAME + " WHERE email = :email")
    public abstract boolean userExist(String email);

    @Query("SELECT count(*) FROM " + TABLE_NAME + " WHERE email = :email AND password = :password")
    public abstract boolean credentialsCorrect(String email, String password);


    @Query("DELETE FROM " + TABLE_NAME)
    @Transaction
    public abstract void deleteAll();


    @Query("SELECT * " +
            "FROM " + TABLE_NAME + " " +
            "ORDER BY email DESC")
    protected abstract LiveData<List<User>> loadAll();

    public LiveData<List<User>> loadAllDistinct() {
        return DistinctLiveData.getDistinct(loadAll());
    }
}
