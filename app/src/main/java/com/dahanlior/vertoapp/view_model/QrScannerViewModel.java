package com.dahanlior.vertoapp.view_model;

import com.dahanlior.vertoapp.model.Qr;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class QrScannerViewModel extends EnvironmentViewModel {

    private final MutableLiveData<Qr> qr;

    public QrScannerViewModel() {
        qr = new MutableLiveData<>();
    }

    public LiveData<Qr> getQr() {
        return qr;
    }


    public void saveNewQr(final Qr qr) {
        getRepository().qrRepo().saveQr(qr);
    }

}
