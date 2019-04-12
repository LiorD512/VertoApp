package com.dahanlior.vertoapp;

import android.app.Application;

import com.dahanlior.vertoapp.base.AppExecutors;
import com.dahanlior.vertoapp.base.DefaultExecutors;
import com.dahanlior.vertoapp.base.Environment;
import com.dahanlior.vertoapp.constants.DbSettings;
import com.dahanlior.vertoapp.database.VertoDatabase;
import com.dahanlior.vertoapp.model.User;
import com.dahanlior.vertoapp.repository.VertoRepository;

import java.lang.reflect.Method;
import java.util.HashMap;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        final AppExecutors executors = new DefaultExecutors();

        final VertoDatabase database = Room.databaseBuilder(this, VertoDatabase.class, DbSettings.DATABASE_FILENAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .setJournalMode(RoomDatabase.JournalMode.AUTOMATIC)
                .build();

        setInMemoryRoomDatabases(database.getOpenHelper().getWritableDatabase());

        final VertoRepository repository = new VertoRepository(database, executors);


        Environment.installExecutors(executors);
        Environment.installDatabase(database);
        Environment.installRepository(repository);


        database.userDao().insert(User.create("verto@gmail.com", "1111"));


    }

    public static void setInMemoryRoomDatabases(SupportSQLiteDatabase... database) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Class[] argTypes = new Class[]{HashMap.class};
                HashMap<String, SupportSQLiteDatabase> inMemoryDatabases = new HashMap<>();
                // set your inMemory databases
                inMemoryDatabases.put("InMemoryOne.db", database[0]);
                Method setRoomInMemoryDatabase = debugDB.getMethod("setInMemoryRoomDatabases", argTypes);
                setRoomInMemoryDatabase.invoke(null, inMemoryDatabases);
            } catch (Exception ignore) {

            }
        }
    }
}
