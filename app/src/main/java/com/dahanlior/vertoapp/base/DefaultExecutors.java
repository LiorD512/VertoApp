package com.dahanlior.vertoapp.base;

import android.os.Handler;
import android.os.Looper;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;


public class DefaultExecutors implements AppExecutors {


    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor main;
    private final Executor background;

    public DefaultExecutors() {
        this(
                Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(3),
                new MainExecutor(),
                Executors.newCachedThreadPool()
        );
    }

    private DefaultExecutors(Executor diskIO, Executor networkIO, Executor main, Executor background) {

        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.main = main;
        this.background = background;
    }

    @Override
    public Executor diskIO() {
        return diskIO;
    }

    @Override
    public Executor networkIO() {
        return networkIO;
    }

    @Override
    public Executor mainThread() {
        return main;
    }

    @Override
    public Executor background() {
        return background;
    }

    private static class MainExecutor implements Executor {

        private Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            handler.post(command);
        }
    }
}
