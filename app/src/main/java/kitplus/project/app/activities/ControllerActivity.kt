package kitplus.project.app.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kitplus.project.app.R
import kitplus.project.app.databinding.ActivityControllerBinding

class ControllerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityControllerBinding

    private lateinit var overviewFragment : OverviewFragment
    private lateinit var productivityFragment : ProductivityFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControllerBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            startActivity(intent)
        }
    }

    private fun showFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }
}