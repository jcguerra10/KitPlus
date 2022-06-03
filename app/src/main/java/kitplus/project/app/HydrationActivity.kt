package kitplus.project.app

import android.R
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kitplus.project.app.activities.ControllerActivity
import kitplus.project.app.databinding.ActivityControllerBinding
import kitplus.project.app.databinding.ActivityHydrationBinding
import kitplus.project.app.model.User
import kitplus.project.app.model.WaterRegistry

class HydrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHydrationBinding

    private lateinit var user: User

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHydrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addWaterBtn.setOnClickListener {
            var counterwater = Integer.parseInt(binding.counterWater.text.toString())
            counterwater += 1
            binding.counterWater.text = counterwater.toString()
        }

        binding.subWaterBtn.setOnClickListener {
            var counterwater = Integer.parseInt(binding.counterWater.text.toString())
            counterwater -= 1
            binding.counterWater.text = counterwater.toString()
        }

        binding.subWaterBtn.setOnLongClickListener {
            binding.counterWater.text = 0.toString()
            true
        }
    }
    
}