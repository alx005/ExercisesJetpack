package com.google.signinsample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.signinsample.data.SignInViewModel;
import com.google.signinsample.databinding.SigninFragmentBinding;


public class SignInFragment extends Fragment {

    private SigninFragmentBinding binding;
    private SignInViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.signin_fragment, container, false);
        binding.setLifecycleOwner(SignInFragment.this);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        model = ViewModelProviders.of(SignInFragment.this).get(SignInViewModel.class);
        binding.setModel(model);


        model.getState().observe(SignInFragment.this, new Observer<SignInViewModel.State>() {
            @Override
            public void onChanged(SignInViewModel.State state) {

            }
        });
    }
}
