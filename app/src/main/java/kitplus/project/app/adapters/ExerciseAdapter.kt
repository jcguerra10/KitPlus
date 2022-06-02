package kitplus.project.app.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kitplus.project.app.R
import kitplus.project.app.controller.ExerciseController
import kitplus.project.app.model.ExerciseRegistry

class ExerciseAdapter: RecyclerView.Adapter<ExerciseView>(), OnDeleteExercise {

    private var exercises = ArrayList<ExerciseRegistry>()
    private lateinit var exerciseController: ExerciseController

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseView {
        exercises = exerciseController.getExercises()
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.item_exercise, parent, false)
        val exerciseView = ExerciseView(row)
        return exerciseView
    }

    override fun onBindViewHolder(holder: ExerciseView, position: Int) {
        val exercise = exercises[position]
        holder.listener = this
        holder.exercise = exercise

        var uriImg = Uri.EMPTY

    //    if (exercise.image != null) {
    //        uriProfile = Uri.parse(userController.searchUser(exercise.author.toInt()).profileImage)
    //    }

       val uri = Uri.parse(exercise.image)
        holder.imageEView.setImageURI(uri)
        holder.nameTxt.setText(exercise.exerciseType.name)
        holder.kCalTxt.setText(exercise.kCal)
        holder.dateTxt.setText(exercise.dateRegistry)

    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onDelete(exercise: ExerciseRegistry?) {
        TODO("Not yet implemented")
    }


}