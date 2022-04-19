package kitplus.project.app.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kitplus.project.app.databinding.ActivityLoginBinding
import kitplus.project.app.model.Profile
import kitplus.project.app.units.Constants
import kitplus.project.app.units.WebUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class LogInActivity : AppCompatActivity() {

    private var auth: Boolean = false
    private var profileLogIn: String = ""
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.logInBtn.setOnClickListener {
            var username = binding.editTextTextPersonName.text.toString()
            var pass = binding.editTextTextPassword.text.toString()

            if (username.contains(".")) {
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
            }

            authenticator(username, pass)

            if (auth) {
                val intent = Intent(this, ControllerActivity::class.java)
                intent.putExtra("profile", profileLogIn)
                startActivity(intent)
            }
        }

        binding.newAccountBtn.setOnClickListener {
            val intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }

    }

    private fun authenticator(username: String, password: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val url = "${Constants.BASE_URL}/profiles.json"
            val response = WebUtil().GETRequest(url)
            if (response != null) {
                val jsonObject = JSONObject(response)

                auth = false

                jsonObject.keys().forEach {
                    val itemStr = jsonObject.get(it).toString()
                    val profile = Gson().fromJson(itemStr, Profile::class.java)

                    if (profile.username == username && profile.password == password){
                        profileLogIn = itemStr
                        auth = true
                        return@launch
                    }
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