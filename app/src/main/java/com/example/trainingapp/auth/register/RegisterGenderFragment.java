package com.example.trainingapp.auth.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.trainingapp.R;
import com.example.trainingapp.core.model.CurrentUser;

public class RegisterGenderFragment extends Fragment {


    @BindView(R.id.male)
    RadioButton male;

    @BindView(R.id.female)
    RadioButton female;

    @BindView(R.id.bnNext)
    Button bnNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_gender, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        male.setChecked(true);
        bnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentUser.gender = getGender();
                ((RegisterActivity)getActivity()).showFragment(new RegisterInfoFragment());
            }
        });
    }

    private String getGender() {
        if (male.isChecked()) {
            return "male";
        } else {
            return "female";
        }
    }

}
