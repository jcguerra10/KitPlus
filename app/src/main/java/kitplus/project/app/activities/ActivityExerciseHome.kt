package kitplus.project.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kitplus.project.app.R
import kitplus.project.app.adapters.ExerciseAdapter
import kitplus.project.app.controller.ExerciseController
import kitplus.project.app.databinding.ActivityExerciseHomeBinding

class ActivityExerciseHome : Fragment() {

    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var exerciseController: ExerciseController

    private lateinit var exerciseAdapter: ExerciseAdapter


    private lateinit var binding: ActivityExerciseHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityExerciseHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        binding.exercisesSumary.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = exerciseAdapter
            exerciseAdapter.setExercises(exerciseController)
        }
    }
}