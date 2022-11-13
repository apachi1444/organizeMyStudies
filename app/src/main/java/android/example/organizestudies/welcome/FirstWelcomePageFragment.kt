package android.example.organizestudies.welcome

import android.example.organizestudies.R
import android.example.organizestudies.databinding.FragmentFirstWelcomePageBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FirstWelcomePageFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentFirstWelcomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first_welcome_page,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        goToNextPage(view)
    }

    private fun goToNextPage(view: View) {
        binding.startGameButton.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true).replace<SecondWelcomePageFragment>(R.id.fragment_container_view)
            }
        }
    }
}