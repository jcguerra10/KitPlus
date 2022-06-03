package kitplus.project.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kitplus.project.app.activities.ControllerActivity
import kitplus.project.app.databinding.ActivityControllerBinding
import kitplus.project.app.databinding.ActivityHydrationBinding

class HydrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHydrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHydrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addWaterBtn.setOnClickListener {
            var counter = Integer.parseInt(binding.counterWater.text.toString())
            counter += 1
            binding.counterWater.text = counter.toString()
        }

        binding.subWaterBtn.setOnClickListener {
            var counter = Integer.parseInt(binding.counterWater.text.toString())
            counter -= 1
            binding.counterWater.text = counter.toString()
        }

        binding.subWaterBtn.setOnLongClickListener {
            binding.counterWater.text = 0.toString()
            true
        }

        binding.addCoffeeBtn.setOnClickListener {
            var counter = Integer.parseInt(binding.counterCoffee.text.toString())
            counter += 1
            binding.counterWater.text = counter.toString()
        }

        binding.subCoffeeBtn.setOnClickListener {
            var counter = Integer.parseInt(binding.counterCoffee.text.toString())
            counter -= 1
            binding.counterWater.text = counter.toString()
        }

        binding.subCoffeeBtn.setOnLongClickListener {
            binding.counterCoffee.text = 0.toString()
            true
        }

        binding.addSodaBtn.setOnClickListener {
            var counter = Integer.parseInt(binding.counterSoda.text.toString())
            counter += 1
            binding.counterWater.text = counter.toString()
        }

        binding.subSodaBtn.setOnClickListener {
            var counter = Integer.parseInt(binding.counterSoda.text.toString())
            counter -= 1
            binding.counterWater.text = counter.toString()
        }

        binding.subSodaBtn.setOnLongClickListener {
            binding.counterSoda.text = 0.toString()
            true
        }

        binding.returnButton.setOnClickListener {
            val intent = Intent(this, ControllerActivity::class.java)
            startActivity(intent)
        }
    }
}