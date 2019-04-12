package com.dahanlior.vertoapp.database;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

public interface BasicDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<T> items);

    @Delete
    void delete(T item);

    @Update
    void update(T item);
}
