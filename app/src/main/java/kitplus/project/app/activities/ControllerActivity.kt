package kitplus.project.app.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kitplus.project.app.HydrationActivity
import kitplus.project.app.R
import kitplus.project.app.databinding.ActivityControllerBinding
import kitplus.project.app.model.Profile
import kitplus.project.app.model.User

class ControllerActivity : AppCompatActivity() {

    lateinit var user: User
    private lateinit var binding: ActivityControllerBinding

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControllerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val usr = bundle?.getString("profile")
        if (usr != null) {
            setUpUser(usr)
        } else {
            val json = bundle?.getString("user")
            user = Gson().fromJson(json, User::class.java)
        }

        binding.profileImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            val jsonString = Gson().toJson(user)
            intent.putExtra("user", jsonString)
            startActivity(intent)
        }

        binding.exerciseConstraint.setOnClickListener {
            val intent = Intent(this, ActivityExerciseHome::class.java)
            val jsonString = Gson().toJson(user)
            intent.putExtra("user", jsonString)
            startActivity(intent)
        }

        binding.hydrateConstraint.setOnClickListener {
            val intent = Intent(this, HydrationActivity::class.java)
            startActivity(intent)
        }

        binding.stepsConstraint.setOnClickListener {
            val intent = Intent(this, StepsActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setUpUser(userid: String?) {
        val query = db.collection( "users"). whereEqualTo( "userid", userid)
        query.get().addOnCompleteListener {
            it.result!!.forEach {it ->
                user = it.toObject(User::class.java)
                binding.userName.text = user.name
            }
        }
    }
}