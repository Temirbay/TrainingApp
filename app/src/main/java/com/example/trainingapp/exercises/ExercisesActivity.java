package com.example.trainingapp.exercises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.trainingapp.R;
import com.example.trainingapp.core.model.Exercise;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.trainingapp.exercises.ExercisesFabrique.generateExercises;

public class ExercisesActivity extends AppCompatActivity implements ExercisesAdapter.OnExerciseClickListener {

    private ExercisesAdapter adapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        ButterKnife.bind(this);
        initRecycler();
        loadData();
    }

    private void loadData() {
        adapter.update(generateExercises());
    }

    private void initRecycler() {
        adapter = new ExercisesAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onExerciseClicked(Exercise exercise) {
        Intent intent = new Intent(this, ExerciseDetailsActivity.class);
        intent.putExtra("exercise", exercise);
        startActivity(intent);
    }
}
