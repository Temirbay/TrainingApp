package com.example.trainingapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.example.trainingapp.R;
import com.example.trainingapp.auth.login.LoginActivity;
import com.example.trainingapp.auth.repository.AuthRepository;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        checkLoggedIn();
    }

    private void checkLoggedIn() {
        if (!AuthRepository.checkLoggedIn()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void initViews() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentLayout, new HomeFragment())
                        .commit();
                return true;
            case R.id.navigation_result:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentLayout, new ResultFragment())
                        .commit();
                return true;
            case R.id.navigation_profile:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentLayout, new ProfileFragment())
                        .commit();
                return true;
        }
        return false;
    }
}
