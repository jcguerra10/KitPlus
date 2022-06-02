package kitplus.project.app.controller

import kitplus.project.app.model.ExerciseRegistry

class ExerciseController {

    private var exercises = ArrayList<ExerciseRegistry>()

    fun addExercise(exerciseRegistry: ExerciseRegistry){

        exercises.add(exerciseRegistry);
    }

    fun getExercises(): ArrayList<ExerciseRegistry>{

        return exercises
    }

}