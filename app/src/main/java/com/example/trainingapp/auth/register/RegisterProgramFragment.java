package com.example.trainingapp.auth.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.trainingapp.R;
import com.example.trainingapp.model.CurrentUser;
import com.example.trainingapp.utlis.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterProgramFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.layoutMedium)
    LinearLayout bnMedium;

    @BindView(R.id.layoutHard)
    LinearLayout bnHard;

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
            case R.id.layoutHard:
                CurrentUser.program = Constants.HARD_LEVEL;
                break;
            case R.id.layoutMedium:
                CurrentUser.program = Constants.MEDIUM_LEVEL;
                break;
        }
        ((RegisterActivity)getActivity()).showFragment(new RegisterFragment());
    }

}
