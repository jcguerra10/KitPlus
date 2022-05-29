package kitplus.project.app.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitplus.project.app.R
import kitplus.project.app.databinding.FragmentProductivityBinding

class ProductivityFragment : Fragment() {

    private lateinit var binding: FragmentProductivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductivityBinding.inflate(layoutInflater)



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    companion object {
        @JvmStatic
        fun newInstance() = ProductivityFragment()
    }
}