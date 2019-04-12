package com.dahanlior.vertoapp.database;

import com.dahanlior.vertoapp.constants.DbSettings;
import com.dahanlior.vertoapp.model.Qr;
import com.dahanlior.vertoapp.model.User;
import com.dahanlior.vertoapp.utils.DistinctLiveData;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public abstract class  QrDao implements BasicDao<Qr> {

    protected static final String TABLE_NAME = DbSettings.TABLE_QR;

    @Query("SELECT count(*) FROM " + TABLE_NAME + " ORDER BY created_on DESC")
    public abstract int count();


    @Query("DELETE FROM " + TABLE_NAME)
    @Transaction
    public abstract void deleteAll();


    @Query("SELECT * " +
            "FROM " + TABLE_NAME + " " +
            "ORDER BY created_on DESC")
    protected abstract LiveData<List<Qr>> loadAll();

    public LiveData<List<Qr>> loadAllDistinct() {
        return DistinctLiveData.getDistinct(loadAll());
    }
}
