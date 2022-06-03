package kitplus.project.app.activities

import android.R
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kitplus.project.app.adapters.ExerciseAdapter
import kitplus.project.app.controller.ExerciseController
import kitplus.project.app.databinding.ActivityExerciseHomeBinding
import kitplus.project.app.model.ExerciseRegistry
import java.util.*


class ActivityExerciseHome : AppCompatActivity()  {

    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var exerciseController: ExerciseController

    private var adapter = ExerciseAdapter()

    private lateinit var binding: ActivityExerciseHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseHomeBinding.inflate(layoutInflater)

        val exerciseRecycler = binding.exercisesSumary
        exerciseRecycler.setHasFixedSize(true)
        exerciseRecycler.layoutManager = LinearLayoutManager(this)
        exerciseRecycler.adapter = adapter


        setContentView(binding.root)

        val arraySpinner = arrayOf(
            "1", "2", "3", "4", "5", "6", "7"
        )

        val adapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        binding.register.setOnClickListener {


            val c = Calendar.getInstance()

            val hour = c.get(Calendar.HOUR)
            val minute = c.get(Calendar.MINUTE)

            val currentTime = "$hour/$minute"
            Exercice e

            exerciseController.addExercise(ExerciseRegistry("", "", "", ))
        }

    }


}