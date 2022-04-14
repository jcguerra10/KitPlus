package kitplus.project.app.activities

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kitplus.project.app.databinding.ActivityCreateaccountBinding


class CreateAccount : AppCompatActivity() {

    private lateinit var binding: ActivityCreateaccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateaccountBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        binding.createAccountBtn.setOnClickListener {
            val username = binding.usernameTxt
            if (binding.passwordFirst.text.toString().equals(binding.passwordSecond.text.toString())) {
                val password = binding.passwordFirst

                //save user

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

    //touch and hide the keyboard
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = this!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this!!.currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}