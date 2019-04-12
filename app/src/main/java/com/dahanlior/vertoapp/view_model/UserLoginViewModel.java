package com.dahanlior.vertoapp.view_model;



import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class UserLoginViewModel extends EnvironmentViewModel {

    private MutableLiveData<Boolean> isCredentialsCorrect;

    public UserLoginViewModel() {
        isCredentialsCorrect = new MutableLiveData<>();
    }

    public LiveData<Boolean> getisCredentialsCorrect() {
        return isCredentialsCorrect;
    }

    public void getIsUserExist(@NonNull String email, @NonNull String password) {

        boolean correct = getRepository().userRepo().isCredentialCorrect(email, password);
        if (correct) {
            isCredentialsCorrect.setValue(true);
        } else {
            isCredentialsCorrect.setValue(false);
        }

    }

}
