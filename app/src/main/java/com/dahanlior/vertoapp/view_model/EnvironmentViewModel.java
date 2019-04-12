package com.dahanlior.vertoapp.view_model;

import com.dahanlior.vertoapp.base.AppExecutors;
import com.dahanlior.vertoapp.base.Environment;
import com.dahanlior.vertoapp.repository.BaseRepository;

import androidx.lifecycle.ViewModel;

public class EnvironmentViewModel extends ViewModel {

    protected <T extends BaseRepository> T getRepository() {
        return (T) Environment.repository();
    }


    protected AppExecutors executors() {
        return Environment.executors();
    }
}
