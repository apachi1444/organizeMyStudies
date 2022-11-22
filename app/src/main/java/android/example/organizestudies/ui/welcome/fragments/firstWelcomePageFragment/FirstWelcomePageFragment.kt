package android.example.organizestudies.ui.welcome.fragments.firstWelcomePageFragment

import android.example.organizestudies.R
import android.example.organizestudies.databinding.FragmentFirstWelcomePageBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

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

        goToSecondPage(binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        goToNextPage()
    }

    private fun goToSecondPage(binding: FragmentFirstWelcomePageBinding) {
        binding.startGameButton.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_firstWelcomePageFragment_to_secondWelcomePageFragment2)
        }
    }

//    private fun goToNextPage() {
//        binding.startGameButton.setOnClickListener {
//            parentFragmentManager.commit {
//                setReorderingAllowed(true).replace<SecondWelcomePageFragment>(R.id.)
//            }
//        }
//    }
}