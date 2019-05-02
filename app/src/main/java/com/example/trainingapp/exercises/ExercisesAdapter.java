package com.example.trainingapp.exercises;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trainingapp.R;
import com.example.trainingapp.core.model.Exercise;

import java.util.ArrayList;
import java.util.List;


public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder> {

    private OnExerciseClickListener listener;
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private Context context;

    public ExercisesAdapter(Context context, OnExerciseClickListener listener) {
        this.context = context;
        this.listener = listener;
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
        final Exercise exercise = exercises.get(i);
        exerciseViewHolder.name.setText(exercise.name);
        exerciseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onExerciseClicked(exercise);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }


    class ExerciseViewHolder extends RecyclerView.ViewHolder {
        ImageView gif;
        TextView name;
        ExerciseViewHolder(View itemView) {
            super(itemView);
            gif = itemView.findViewById(R.id.gif);
            name = itemView.findViewById(R.id.nameLabel);
        }
    }

    interface OnExerciseClickListener {
        public void onExerciseClicked(Exercise exercise);
    }
}
