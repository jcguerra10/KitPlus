package kitplus.project.app.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitplus.project.app.R
import kitplus.project.app.databinding.FragmentOverviewBinding
import kitplus.project.app.model.User

class OverviewFragment : Fragment() {

    private lateinit var user: User
    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        user = (activity as ControllerActivity).user

        binding.constraintExcersice.setOnClickListener {

            println(">>>>>>>>>>>> " + user.userid)
        }

        binding.constraintWater.setOnClickListener {

        }

        var progress : Int = binding.progressBar.progress
        binding.progressIndicator.text = progress.toString()

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = OverviewFragment()
    }
}