package com.dahanlior.vertoapp.repository;


import com.dahanlior.vertoapp.base.AppExecutors;
import com.dahanlior.vertoapp.database.VertoDatabase;

public class VertoRepository extends BaseRepository<VertoDatabase> {

    public VertoRepository(VertoDatabase db, AppExecutors executors) {
        super(db, executors);
    }


}
