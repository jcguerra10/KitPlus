package kitplus.project.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.core.text.set
import com.google.gson.Gson
import kitplus.project.app.R
import kitplus.project.app.databinding.ActivityProfileBinding
import kitplus.project.app.model.User

class ProfileActivity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val json = bundle?.getString("user")
        user = Gson().fromJson(json, User::class.java)

        binding.nameView.text = user.name
        binding.birthdayView.text = user.birthday
        binding.weightEdit.setText(user.weightUser)
        binding.heightEditText.setText(user.heightUser)

    }
}