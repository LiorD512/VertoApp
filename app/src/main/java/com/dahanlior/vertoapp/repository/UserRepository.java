package com.dahanlior.vertoapp.repository;

import com.dahanlior.vertoapp.base.AppExecutors;
import com.dahanlior.vertoapp.database.UserDao;

public class UserRepository extends EntityRepository<UserDao> {

    UserRepository(BaseRepository mainRepository, UserDao db, AppExecutors executors) {
        super(mainRepository, db, executors);
    }


    public boolean isCredentialCorrect(final String email, final String password){
        return getDb().credentialsCorrect(email, password);
    }
}
