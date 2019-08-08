package com.google.signinsample.data;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.google.signinsample.R;

public class SignInViewModel extends AndroidViewModel {

    public enum State {
        NOT_READY_TO_SUBMIT, READY_TO_SUBMIT, IN_PROGRESS, SIGNED_IN, SIGN_IN_FAILED
    }

    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    private String TAG = SignInViewModel.class.getSimpleName();
//    private UserRepository repo = UserRepository.getInstance();
    private MediatorLiveData<State> state = new MediatorLiveData<>();
//    private LiveData<Resource<User>> user = repo.getUser();
//    private MediatorLiveData<Integer> message = new MediatorLiveData<>();


    public SignInViewModel(@NonNull Application application) {
        super(application);

        state.setValue(State.NOT_READY_TO_SUBMIT);
//        message.setValue(R.string.message_default);

        state.addSource(username, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                updateState();
            }
        });

        state.addSource(password, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                updateState();
            }
        });

//        state.addSource(user, new Observer<Resource<User>>() {
//            @Override
//            public void onChanged(Resource<User> userResource) {
//                updateState();
//            }
//        });



    }

    private void updateState(){
        Log.d(TAG, "Update State ");

    if (!TextUtils.isEmpty(username.getValue()) && !TextUtils.isEmpty(password.getValue()) && Patterns.EMAIL_ADDRESS.matcher(username.getValue()).matches() ) {
            Log.d(TAG, "password and username is GOOD");
            state.setValue(State.READY_TO_SUBMIT);
            return;
        }

        state.setValue(State.NOT_READY_TO_SUBMIT);

    }




    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setUsername(MutableLiveData<String> username) {
        this.username = username;
    }

    public MediatorLiveData<State> getState() {
        return state;
    }
}
