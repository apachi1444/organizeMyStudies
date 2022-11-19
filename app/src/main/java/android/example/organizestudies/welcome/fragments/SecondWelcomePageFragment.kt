package android.example.organizestudies.welcome.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.example.organizestudies.R
import android.example.organizestudies.databinding.FragmentSecondWelcomePageBinding
import android.example.organizestudies.main.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SecondWelcomePageFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSecondWelcomePageBinding
    private lateinit var sharedPreferences: SharedPreferences
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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_second_welcome_page,
            container,
            false
        )
        binding.startGameButton.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_secondWelcomePageFragment_to_loginFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        sharedPreferences = requireActivity()
//            .getSharedPreferences("mine", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//        editor.putBoolean("start", false)
//        editor.apply()
//        editor.apply()
//        goToMainActivity()
//        goToLoginPage(binding)
    }

    private fun goToMainActivity() {

        binding.startGameButton.setOnClickListener {
            val intent = Intent(requireActivity().applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

    }


}