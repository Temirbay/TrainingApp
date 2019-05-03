package com.example.trainingapp.auth.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trainingapp.R;
import com.example.trainingapp.model.CurrentUser;
import com.example.trainingapp.model.User;
import com.example.trainingapp.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterFragment extends Fragment {

    @BindView(R.id.login)
    TextView login;

    @BindView(R.id.password)
    TextView password;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.bnRegister)
    Button bnRegister;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        bnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginString = login.getText().toString();
                String passwordString = password.getText().toString();
                if (!loginString.equals("") && !passwordString.equals("")) {
                    showProgress();
                    register(loginString, passwordString);
                }
            }
        });
    }

    private void register(String loginString, String passwordString) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(loginString, passwordString)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            onRegisterSucceed();
                            hideProgress();

                        } else {
                            onRegisterFailed(task.getException());
                            hideProgress();
                        }
                    }
                });
    }

    private void onRegisterSucceed() {
        CurrentUser.login = login.getText().toString();
        CurrentUser.password = password.getText().toString();
        CurrentUser.name = name.getText().toString();
        CurrentUser.uuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        CurrentUser.imageUrl = "";
        User user = CurrentUser.getUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("users").child(user.uuid).setValue(user);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


    private void showMessage (String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    private void onRegisterFailed(Exception exception) {
        if (exception instanceof FirebaseAuthWeakPasswordException) {
            showMessage("Слабый пароль. Пароль должен содержать минимум 8 символов");
        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            showMessage("Неправильный логин или пароль");
        } else if (exception instanceof FirebaseAuthUserCollisionException) {
            showMessage("Пользававтель с таким логин уже существует");
        } else {
            showMessage(exception.getMessage());
        }
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

}
