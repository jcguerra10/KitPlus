package kitplus.project.app.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kitplus.project.app.R
import kitplus.project.app.model.ExerciseRegistry

interface OnDeleteExercise {
    fun onDelete(exercise: ExerciseRegistry?)
}

class ExerciseView (itemView: View): RecyclerView.ViewHolder(itemView) {

    var listener: OnDeleteExercise? = null
    var exercise: ExerciseRegistry? = null

    val imagePrfView: ImageView = itemView.findViewById(R.id.imagePro)
    val nameTxt: TextView = itemView.findViewById(R.id.nameTxt)
    val kCalTxt: TextView = itemView.findViewById(R.id.kCalTxt)
    val dateTxt: TextView = itemView.findViewById(R.id.dateTxt)
}
