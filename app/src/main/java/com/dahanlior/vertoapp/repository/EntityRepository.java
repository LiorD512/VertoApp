package com.dahanlior.vertoapp.repository;


import com.dahanlior.vertoapp.base.AppExecutors;

public class EntityRepository<DAO> {


    private DAO db;
    private AppExecutors executors;
    private BaseRepository repository;

    EntityRepository(BaseRepository mainRepository, DAO db, AppExecutors executors) {
        this.repository = mainRepository;
        this.db = db;
        this.executors = executors;
    }

    protected AppExecutors getExecutors() {
        return executors;
    }


    protected DAO getDb() {
        return db;
    }

    protected BaseRepository getMainRepository() {
        return repository;
    }

}
