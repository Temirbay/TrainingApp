package com.example.trainingapp.auth.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.trainingapp.R;
import com.example.trainingapp.model.CurrentUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterProgramFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.bnMedium)
    Button bnMedium;

    @BindView(R.id.bnHard)
    Button bnHard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_program, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        bnMedium.setOnClickListener(this);
        bnHard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bnHard:
                CurrentUser.program = bnHard.getText().toString();
                break;
            case R.id.layoutMedium:
                CurrentUser.program = bnMedium.getText().toString();
                break;
        }
        ((RegisterActivity)getActivity()).showFragment(new RegisterFragment());
    }

}
