package com.dahanlior.vertoapp.base;


import com.dahanlior.vertoapp.database.GeneralDatabase;
import com.dahanlior.vertoapp.repository.BaseRepository;
import com.dahanlior.vertoapp.utils.AssertUtils;

public class Environment {

    private static volatile Environment INSTANCE;

    public static Environment instance() {
        if(INSTANCE == null) {
            synchronized (Environment.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Environment();
                }
            }
        }

        return INSTANCE;
    }



    private AppExecutors executors;
    private BaseRepository repository;
    private GeneralDatabase generalDatabase;

    private Environment() {
    }
    public static AppExecutors executors() {
        return instance().executors;
    }

    public static void installExecutors(AppExecutors executors) {
        if(executors != null) {
            instance().executors = executors;
        } else {
            instance().executors = new DefaultExecutors();
        }
    }

    public static GeneralDatabase database() {
        return instance().generalDatabase;
    }


    public static void installDatabase(GeneralDatabase database) {
        AssertUtils.assertNotNull(database);
        instance().generalDatabase = database;
    }


    public static BaseRepository repository() {
        return instance().repository;
    }


    public static void installRepository(BaseRepository repository) {
        AssertUtils.assertNotNull(repository);
        instance().repository = repository;
    }

}
