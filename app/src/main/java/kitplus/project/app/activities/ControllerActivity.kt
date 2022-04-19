package kitplus.project.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kitplus.project.app.databinding.ActivityControllerBinding

class ControllerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityControllerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControllerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}