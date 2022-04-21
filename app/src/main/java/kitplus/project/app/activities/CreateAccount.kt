package kitplus.project.app.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kitplus.project.app.databinding.ActivityCreateaccountBinding
import kitplus.project.app.model.Profile
import java.util.*


class CreateAccount : AppCompatActivity() {

    private var auth: Boolean = false
    private lateinit var binding: ActivityCreateaccountBinding

    private val db = Firebase.firestore

    var idOfUser: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateaccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.createAccountBtn.setOnClickListener {
            val username = binding.usernameTxt.text.toString()

            if (binding.passwordFirst.text.toString().equals(binding.passwordSecond.text.toString())) {
                val password = binding.passwordFirst.text.toString()
                authenticator(username)
                if (!auth) {
                    idOfUser = UUID.randomUUID().toString()
                    createProfile(Profile(username, password, idOfUser))

                    val intent = Intent(this, CreateUser::class.java)
                    intent.putExtra("idOfUser", idOfUser)
                    startActivity(intent)
                } else {
                    binding.usernameTxt.setText("")
                    Toast.makeText(applicationContext, "Username Already Exist", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Password don´t match", Toast.LENGTH_SHORT).show()
            }
        }

        binding.passwordSecond.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) { }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (binding.passwordFirst.length() != 0 && binding.passwordSecond.length() != 0) {
                    if (binding.passwordFirst.text.toString().equals(binding.passwordSecond.text.toString())) {
                        Toast.makeText(applicationContext, "Passwords match", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun createProfile(newProfile: Profile) {
        db.collection("profiles").document(newProfile.username).set(newProfile)
    }

    private fun authenticator(profileUsername: String) {
        auth = false
        val query = db.collection( "profiles"). whereEqualTo( "username", profileUsername)
        query.get().addOnCompleteListener {
            for (document in it.result!!) {
                val profile = document.toObject(Profile::class.java)
                if (profile.username == profileUsername) {
                    auth = true
                    break
                }
            }
        }
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