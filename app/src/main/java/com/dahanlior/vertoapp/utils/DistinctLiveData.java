package com.dahanlior.vertoapp.utils;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;


public class DistinctLiveData {

    public static <T> LiveData<T> getDistinct(@NonNull LiveData<T> source) {

        final MediatorLiveData<T> distinct = new MediatorLiveData<>();
        distinct.addSource(source, new Observer<T>() {

            private boolean initialized = false;
            private T lastObj = null;

            @Override
            public void onChanged(@Nullable T obj) {
                if (!initialized) {
                    initialized = true;
                    lastObj = obj;
                    distinct.postValue(lastObj);
                } else if (!Objects.equals(lastObj, obj)) {//((obj == null && lastObj != null) || obj != lastObj) {
                    lastObj = obj;
                    distinct.postValue(lastObj);
                }
            }
        });

        return distinct;
    }
}
