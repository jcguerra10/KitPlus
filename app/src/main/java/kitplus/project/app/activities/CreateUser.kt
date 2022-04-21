package kitplus.project.app.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kitplus.project.app.databinding.ActivityCreateuserBinding
import kitplus.project.app.model.User
import kitplus.project.app.units.Constants
import kitplus.project.app.units.WebUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateUser : AppCompatActivity() {

    private var idOfUser: String? = null
    private lateinit var binding: ActivityCreateuserBinding
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateuserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        idOfUser = bundle?.getString("idOfUser")


        binding.saveDataBtn.setOnClickListener {
            val nameTxt = binding.nameTxt.text.toString()
            val weightTxt = binding.weightTxt.text.toString()
            val heightTxt = binding.heightTxt.text.toString()
            val birthdayDp = "${binding.birthDp.dayOfMonth}/${binding.birthDp.month}/${binding.birthDp.year}"

            if (idOfUser != null) {
                val newUser =
                    idOfUser?.let { it1 -> User(it1, nameTxt, weightTxt, heightTxt, birthdayDp) }

                if (newUser != null) {
                    createUser(newUser)
                    val intent = Intent(this, ControllerActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun createUser(newUser: User) {
        db.collection("users").document(newUser.userid).set(newUser)
    }

    //touch and hide the keyboard
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = this!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this!!.currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}