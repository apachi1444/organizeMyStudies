package android.example.organizestudies.ui.welcome.fragments.secondWelcomePageFragment

import android.example.organizestudies.R
import android.example.organizestudies.databinding.FragmentSecondWelcomePageBinding
import android.example.organizestudies.ui.main.activities.MainActivity
import android.example.organizestudies.utils.Utils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController


class SecondWelcomePageFragment : Fragment() {
    private lateinit var binding: FragmentSecondWelcomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_second_welcome_page,
            container,
            false
        )
        goToMainActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    private fun goToMainActivity() {
        binding.startGameButton.setOnClickListener {
            if (Utils.checkUserLoggedIn(requireContext())) {
                Utils.startActivity(requireContext(), MainActivity::class.java)
            } else {
                requireView().findNavController()
                    .navigate(R.id.action_secondWelcomePageFragment_to_loginFragment)
            }
        }
    }
}