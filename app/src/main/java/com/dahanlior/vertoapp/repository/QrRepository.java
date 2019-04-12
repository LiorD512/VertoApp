package com.dahanlior.vertoapp.repository;

import com.dahanlior.vertoapp.base.AppExecutors;
import com.dahanlior.vertoapp.database.QrDao;
import com.dahanlior.vertoapp.database.UserDao;
import com.dahanlior.vertoapp.model.Qr;

public class QrRepository extends EntityRepository<QrDao> {

    QrRepository(BaseRepository mainRepository, QrDao db, AppExecutors executors) {
        super(mainRepository, db, executors);
    }

    public void saveQr(Qr qr) {
        getDb().insert(qr);
    }


}
