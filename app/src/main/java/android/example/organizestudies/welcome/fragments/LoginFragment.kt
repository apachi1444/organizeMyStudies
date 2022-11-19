package android.example.organizestudies.welcome.fragments

import android.content.Intent
import android.example.organizestudies.R
import android.example.organizestudies.data.dao.UserDb
import android.example.organizestudies.data.dao.UserDbDao
import android.example.organizestudies.databinding.FragmentLoginBinding
import android.example.organizestudies.main.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController


class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentLoginBinding
    private lateinit var dao: UserDbDao

    private lateinit var myDb: UserDb

    private lateinit var username: CharSequence
    private lateinit var password: CharSequence

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // initialize the dao
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

//        val application = requireNotNull(this.activity).application
//        dao = UserDb.getInstance(application).userDbDao

        goToMainActivity(binding)

//        dao = myDb.userDbDao

        binding.userNameInput.addTextChangedListener(

        )

        printInputNames()

        goToSignUpPage()

        return binding.root
    }


    private fun goToMainActivity(binding: FragmentLoginBinding) {
        val intent = Intent(context, MainActivity::class.java)
        username = binding.userNameInput.text
        password = binding.passwordInput.toString()
        binding.goToHomeActivity.setOnClickListener {
            printInputNames()
//            dbLogicWithUserInputs()
            startActivity(intent)
        }
    }

    private fun printInputNames() {
        binding.textView2.text = username
        binding.textView3.text = password
    }

    private fun dbLogicWithUserInputs() {
//        val user = User(1, username.toString(), password.toString())
//        dao.insert(user)
    }

    private fun goToSignUpPage() {
        binding.signUpButton.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }

}