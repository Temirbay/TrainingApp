package com.example.trainingapp.auth.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.trainingapp.R;
import com.example.trainingapp.auth.register.RegisterActivity;
import com.example.trainingapp.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.login)
    TextView login;

    @BindView(R.id.password)
    TextView password;

    @BindView(R.id.bnLogin)
    Button bnLogin;

    @BindView(R.id.bnRegister)
    TextView bnRegister;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        bnLogin.setOnClickListener(this);
        bnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bnLogin :
                login();
                break;
            case R.id.bnRegister:
                register();
                break;
        }
    }

    private void login() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (login.getText().toString().equals("") || password.getText().toString().equals("")) return;
        showProgress();
        auth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            onLoginSucceed();
                            hideProgress();
                        } else {
                            onLoginFailed(task.getException());
                            hideProgress();
                        }
                    }
                });
    }

    private void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }



    private void onLoginFailed(Exception exception) {
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

    private void showMessage (String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void onLoginSucceed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

}
