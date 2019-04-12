package com.dahanlior.vertoapp.database;

import com.dahanlior.vertoapp.model.Qr;
import com.dahanlior.vertoapp.model.User;

import androidx.room.Database;


@Database(
        entities = {
                User.class,
                Qr.class
        },
        version = 1,
        exportSchema = false

)

public abstract class VertoDatabase extends GeneralDatabase {


}