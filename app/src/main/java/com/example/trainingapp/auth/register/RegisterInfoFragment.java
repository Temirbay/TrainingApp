package com.example.trainingapp.auth.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.trainingapp.R;
import com.example.trainingapp.model.CurrentUser;

public class RegisterInfoFragment extends Fragment {

    @BindView(R.id.label)
    TextView label;

    @BindView(R.id.info)
    EditText info;

    @BindView(R.id.bnNext)
    Button bnNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        bnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info.getText().toString().equals("")) return;

                if (label.getText() == getResources().getString(R.string.your_age)) {
                    CurrentUser.age = Integer.parseInt(info.getText().toString());
                    label.setText(getResources().getString(R.string.your_height));
                    info.setText("");
                }

                else if (label.getText() == getResources().getString(R.string.your_height)) {
                    CurrentUser.height = Integer.parseInt(info.getText().toString());
                    label.setText(getResources().getString(R.string.your_weight));
                    info.setText("");
                }

                else if (label.getText() == getResources().getString(R.string.your_weight)) {
                    CurrentUser.weight = Integer.parseInt(info.getText().toString());
                    label.setText(getResources().getString(R.string.need_weight));
                    info.setText("");
                }

                else if (label.getText() == getResources().getString(R.string.need_weight)) {
                    CurrentUser.needWeight = Integer.parseInt(info.getText().toString());
                    ((RegisterActivity)getActivity()).showFragment(new RegisterProgramFragment());
                }
            }
        });
    }
}
