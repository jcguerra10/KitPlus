package kitplus.project.app.activities

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kitplus.project.app.databinding.ActivityStepsBinding
import kitplus.project.app.model.User

class StepsActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var binding: ActivityStepsBinding

    private lateinit var user: User

    private var sensorManager : SensorManager? = null
    private var running = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStepsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val json = bundle?.getString("user")
        user = Gson().fromJson(json, User::class.java)

        loadData()
        resetSteps()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        binding.returnBtn.setOnClickListener {
            val intent = Intent(this, StepsActivity::class.java)

        }
    }

    override fun onResume() {
        super.onResume()
        running = true
        val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor == null){
            Toast.makeText(this, "No Sensor detected in this Device", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "Long tap to Reset Steps", Toast.LENGTH_SHORT).show()
        }

        binding.stepsTaken.setOnLongClickListener {
            previousTotalSteps = totalSteps
            binding.stepsTaken.text = 0.toString()
            saveData()
            true
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("My prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("key1", previousTotalSteps)
        editor.apply()
    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("My prefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getFloat("key1", 0f)
        Log.d("Productivity Fragment", "$savedNumber")
        previousTotalSteps = savedNumber
    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
    }

    private fun saveStepsToFirestore(steps: Float) {
        val userDB = db.collection("users").document(user.userid)

        userDB.update("steps", steps).addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

    }

    private fun getStepsToFirestore(): Float {
        val userDB = db.collection("users").document("${user.userid}")

        return userDB.get().result.get("steps") as Float
    }

}