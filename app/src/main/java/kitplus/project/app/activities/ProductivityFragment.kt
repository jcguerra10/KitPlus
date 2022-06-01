package kitplus.project.app.activities

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kitplus.project.app.databinding.FragmentProductivityBinding

class ProductivityFragment : Fragment(), SensorEventListener {

    private lateinit var binding: FragmentProductivityBinding

    private var sensorManager : SensorManager? = null
    private var running = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductivityBinding.inflate(layoutInflater)

        loadData()
        resetSteps()
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProductivityFragment()
    }

    override fun onResume() {
        super.onResume()
        running = true
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor == null){
            Toast.makeText(requireContext(), "No Sensor detected in this Device", Toast.LENGTH_SHORT).show()
        }else{
            sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
        }
    }
    override fun onSensorChanged(p0: SensorEvent?) {
        if(running){
            totalSteps = p0!!.values[0]
            val currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()
            binding.stepsTaken.text = ("$currentSteps")

            binding.circularProgressBar.apply {
                setProgressWithAnimation(currentSteps.toFloat())
            }
        }
    }

    private fun resetSteps(){
        binding.stepsTaken.setOnClickListener {
            Toast.makeText(requireContext(), "Long tap to Reset Steps", Toast.LENGTH_SHORT).show()
        }

        binding.stepsTaken.setOnLongClickListener {
            previousTotalSteps = totalSteps
            binding.stepsTaken.text = 0.toString()
            saveData()
            true
        }
    }

    private fun saveData() {
        val sharedPreferences = requireContext().getSharedPreferences("My prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("key1", previousTotalSteps)
        editor.apply()
    }

    private fun loadData(){
        val sharedPreferences = requireContext().getSharedPreferences("My prefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getFloat("key1", 0f)
        Log.d("Productivity Fragment", "$savedNumber")
        previousTotalSteps = savedNumber
    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }
}