package kitplus.project.app.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kitplus.project.app.R
import kitplus.project.app.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root

        var progress : Int = binding.progressBar.progress
        binding.progressIndicator.text = progress.toString()
    }

    companion object {

        @JvmStatic
        fun newInstance() = OverviewFragment()
    }
}