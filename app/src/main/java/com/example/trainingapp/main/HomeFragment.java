package com.example.trainingapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trainingapp.R;
import com.example.trainingapp.exercises.ExercisesActivity;
import com.example.trainingapp.model.CurrentUser;
import com.example.trainingapp.utlis.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.nameLabel)
    TextView nameLabel;

    @BindView(R.id.card)
    CardView card;

    @BindView(R.id.training)
    TextView training;

    @BindView(R.id.time)
    TextView time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        StringBuilder builder = new StringBuilder();
        builder.append("Привет, ");
        builder.append(CurrentUser.name);
        nameLabel.setText(builder.toString());

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExercisesActivity.class);
                startActivity(intent);
            }
        });

        if (CurrentUser.program.equals(Constants.MEDIUM_LEVEL)) {
            training.setText("8 тренировок");
            time.setText("8 мин");
        }
        else {
            training.setText("16 тренировок");
            time.setText("16 мин");
        }
    }
}
