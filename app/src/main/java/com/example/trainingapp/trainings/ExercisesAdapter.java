package com.example.trainingapp.trainings;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trainingapp.R;
import com.example.trainingapp.core.model.Exercise;

import java.util.ArrayList;
import java.util.List;


public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder> {

    private ArrayList<Exercise> exercises = new ArrayList<>();
    private Context context;

    public ExercisesAdapter(Context context) {
        this.context = context;
    }

    public void update (List<Exercise> list) {
        this.exercises.clear();
        this.exercises.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ExerciseViewHolder(
                        LayoutInflater.from(viewGroup.getContext())
                                .inflate(R.layout.item_exercise, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder exerciseViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    static class ExerciseViewHolder extends RecyclerView.ViewHolder{

        ExerciseViewHolder(View itemView) {
            super(itemView);
        }

    }
}
