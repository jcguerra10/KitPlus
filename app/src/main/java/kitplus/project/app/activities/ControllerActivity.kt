package kitplus.project.app.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kitplus.project.app.R
import kitplus.project.app.databinding.ActivityControllerBinding
import kitplus.project.app.model.Profile
import kitplus.project.app.model.User

class ControllerActivity : AppCompatActivity() {

    lateinit var user: User
    private lateinit var binding: ActivityControllerBinding

    private lateinit var overviewFragment : OverviewFragment
    private lateinit var productivityFragment : ProductivityFragment

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

        overviewFragment = OverviewFragment.newInstance()
        productivityFragment = ProductivityFragment.newInstance()

        showFragment(overviewFragment)


        binding.navigator.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.overview){
                showFragment(overviewFragment)
            }else if(menuItem.itemId == R.id.productivity){
                showFragment(productivityFragment)
            }
            true
        }

        binding.profileImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            val jsonString = Gson().toJson(user)
            intent.putExtra("user", jsonString)
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

    private fun showFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}