package com.google.signinsample.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository {

    private static String TAG = UserRepository.class.getSimpleName();
    private static FirebaseAuth auth = FirebaseAuth.getInstance();
    private static MutableLiveData<Resource<User>> user = new MutableLiveData<>();

    private static UserRepository userRepository;

    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();

            auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    Log.d(TAG, "onAuthStateChanged");
                    FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                        User tempUser = new User();
                        tempUser.email = currentUser != null ? currentUser.getEmail() : "";
                        tempUser.id = currentUser != null ? currentUser.getUid() : "";
                        user.setValue(Resource.success(tempUser));
                }
            });
        }
        return userRepository;
    }

    public LiveData<Resource<User>> getUser() {
        return user;
    }

    public LiveData<Resource<User>> signIn(String username, String password) {
        auth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Resource resource;
                User tempUser = new User();

                if (task.isSuccessful()) {
                    Log.i(TAG, "Firebase Login Success");

                    tempUser.email = auth.getCurrentUser().getEmail() != null ? auth.getCurrentUser().getEmail() : "";
                    tempUser.id = auth.getCurrentUser().getUid() != null ? auth.getCurrentUser().getEmail() : "";
                    resource = Resource.success(tempUser);
                } else {

                    FirebaseAuthException exception = (FirebaseAuthException) task.getException();
                    resource = Resource.error(tempUser, exception.getLocalizedMessage());
                    Log.e(TAG, exception.getLocalizedMessage());
                }

                user.setValue(resource);
            }
        });

        return user;
    }

}
