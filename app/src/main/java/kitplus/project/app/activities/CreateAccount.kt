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
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kitplus.project.app.databinding.ActivityCreateaccountBinding
import kitplus.project.app.model.Profile
import kitplus.project.app.units.Constants
import kitplus.project.app.units.WebUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.*


class CreateAccount : AppCompatActivity() {

    private var auth: Boolean = false
    private lateinit var binding: ActivityCreateaccountBinding

    private val db = Firebase.firestore

    var idOfUser: String = ""
    var existProfile: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateaccountBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.createAccountBtn.setOnClickListener {
            var username = binding.usernameTxt.text.toString()
            /*if (username.contains(".")) {
                var spl = username.split(".")
                var resultString = ""
                for (i in spl) {
                    if (i != "") {
                        if (i != spl[spl.size-1]) {
                            resultString += "$i-"
                        } else {
                            resultString += "$i"
                        }

                    }
                }
                username = resultString
            }*/

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

    /*private fun existProfile(username: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val url = "${Constants.BASE_URL}/profiles.json"
            val response = WebUtil().GETRequest(url)
            if (response != null) {
                val jsonObject = JSONObject(response)

                existProfile = false

                jsonObject.keys().forEach {
                    val itemStr = jsonObject.get(it).toString()
                    val profile = Gson().fromJson(itemStr, Profile::class.java)

                    if (profile.username == username){
                        existProfile = true
                        return@launch
                    }
                }
            }
        }
    }*/

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
            val imm = this!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this!!.currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}