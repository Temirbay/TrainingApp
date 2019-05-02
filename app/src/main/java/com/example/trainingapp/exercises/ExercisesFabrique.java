package com.example.trainingapp.exercises;

import com.example.trainingapp.core.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExercisesFabrique {

    public static List<Exercise> generateExercises() {
        List<Exercise> list = new ArrayList<>();
        list.add(new Exercise(
           "Планка с подъемом руки и ноги",
           "Прими упор лежа, спину держи прямой.\n" +
                   "Вытяни правую руку вперед и подними левую ногу. Вернись в исходное положение и повтори то же самое левой рукой и правой ногой."
        ));
        list.add(new Exercise(
                "Скалолаз",
                "Прими упор лежа, спину держи прямой.\n" +
                        "Подними правую ногу и подтяни ее колено к груди. Сразу же верни обратно и повтори то же левой – продолжай, быстро чередуя ноги."
        ));

        return list;
    }

}
