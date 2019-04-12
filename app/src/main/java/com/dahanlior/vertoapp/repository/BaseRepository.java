package com.dahanlior.vertoapp.repository;

import com.dahanlior.vertoapp.base.AppExecutors;
import com.dahanlior.vertoapp.database.GeneralDatabase;


public abstract class BaseRepository<DB extends GeneralDatabase> {

    private final UserRepository userRepo;
    private final QrRepository qrRepo;
    private final DB database;
    private final AppExecutors executors;

    public BaseRepository(DB database, AppExecutors executors) {
        this.database = database;
        this.executors = executors;

        this.userRepo = new UserRepository(this, database.userDao(), executors);
        this.qrRepo = new QrRepository(this, database.qrDao(), executors);

    }

    public UserRepository userRepo() {
        return userRepo;
    }

    public QrRepository qrRepo(){
        return qrRepo;
    }

    public DB getDatabase() {
        return database;
    }

    public AppExecutors getExecutors() {
        return executors;
    }

}
