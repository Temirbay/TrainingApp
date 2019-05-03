package com.example.trainingapp.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.trainingapp.R;
import com.example.trainingapp.model.Exercise;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.trainingapp.exercises.ExercisesFabrique.generateExercises;

public class ExercisesActivity extends AppCompatActivity implements ExercisesAdapter.OnExerciseClickListener {

    private List<Exercise> exercises = new ArrayList<>();
    private ExercisesAdapter adapter;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        ButterKnife.bind(this);
        initRecycler();
      //  loadData();
      //  writeExercises();
        loadRemoteData();
    }

    private void loadRemoteData() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("exercises").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> items = dataSnapshot.getChildren().iterator();
                exercises.clear();
                while (items.hasNext()) {
                    DataSnapshot iterator = items.next();

                    HashMap<String, String> hashMap = (HashMap<String, String>) iterator.getValue();
                    Exercise exercise = new Exercise(
                            hashMap.get("name"),
                            hashMap.get("body"),
                            hashMap.get("gif")
                    );
                    exercises.add(exercise);
                }
                adapter.update(exercises);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void writeExercises() {
        List<Exercise> exercises = generateExercises();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        for (Exercise exercise : exercises) {
            DatabaseReference reference = database.getReference().child("exercises").push();
            reference.setValue(exercise);
        }
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
