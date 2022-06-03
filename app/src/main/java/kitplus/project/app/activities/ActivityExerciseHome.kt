package kitplus.project.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kitplus.project.app.R
import kitplus.project.app.adapters.ExerciseAdapter
import kitplus.project.app.controller.ExerciseController
import kitplus.project.app.databinding.ActivityControllerBinding
import kitplus.project.app.databinding.ActivityExerciseHomeBinding
import kitplus.project.app.model.User

class ActivityExerciseHome : AppCompatActivity()  {

    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var exerciseController: ExerciseController

    private lateinit var exerciseAdapter: ExerciseAdapter


    private lateinit var binding: ActivityExerciseHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseHomeBinding.inflate(layoutInflater)

        val exerciseRecycler = binding.exercisesSumary
        exerciseRecycler.setHasFixedSize(true)
        exerciseRecycler.layoutManager = LinearLayoutManager(this)
        exerciseRecycler.adapter = exerciseAdapter
        setContentView(binding.root)


    }
}