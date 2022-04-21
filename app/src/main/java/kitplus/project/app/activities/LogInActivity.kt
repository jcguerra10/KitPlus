package kitplus.project.app.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kitplus.project.app.databinding.ActivityLoginBinding
import kitplus.project.app.model.Profile

class LogInActivity : AppCompatActivity() {

    private var auth: Boolean = false
    private var profileLogIn: String = ""
    private lateinit var binding: ActivityLoginBinding

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.logInBtn.setOnClickListener {
            Log.e(">>>", "")
            val username = binding.editTextTextPersonName.text.toString()
            val pass = binding.editTextTextPassword.text.toString()


            authenticator(username, pass)

        }

        binding.newAccountBtn.setOnClickListener {
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }

    }

    private fun authenticator(username: String, password: String): Task<QuerySnapshot> {
        val query = db.collection( "profiles"). whereEqualTo( "username", username)
        val resul = query.get().addOnCompleteListener {
            auth = false
            it.result!!.forEach {

                val profile = it.toObject(Profile::class.java)
                if (profile.username == username && profile.password == password) {
                    auth = true
                    val intent = Intent(this, ControllerActivity::class.java)
                    intent.putExtra("profile", profileLogIn)
                    startActivity(intent)
                }
            }
        }

        return resul
    }

    //touch and hide the keyboard
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}