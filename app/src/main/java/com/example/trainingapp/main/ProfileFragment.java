package com.example.trainingapp.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trainingapp.NotificationsActivity;
import com.example.trainingapp.R;
import com.example.trainingapp.auth.login.LoginActivity;
import com.example.trainingapp.core.model.CurrentUser;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.bnSignOut)
    TextView bnSignOut;

    @BindView(R.id.bnNotifications)
    TextView bnNotifications;

    @BindView(R.id.height_edit)
    EditText height;

    @BindView(R.id.weight_edit)
    EditText weight;

    @BindView(R.id.need_weight_edit)
    EditText needWeight;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @SuppressLint("SetTextI18n")
    private void initViews() {
        bnSignOut.setOnClickListener(this);
        bnNotifications.setOnClickListener(this);
        height.setText(CurrentUser.height.toString());
        weight.setText(CurrentUser.weight.toString());
        needWeight.setText(CurrentUser.needWeight.toString());
    }

    private void signOut() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bnSignOut:
                signOut();
                break;

            case R.id.bnNotifications:
                Intent intent = new Intent(getActivity(), NotificationsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
