package android.example.organizestudies.ui.welcome.fragments.loginFragment

import android.example.organizestudies.R
import android.example.organizestudies.databinding.FragmentLoginBinding
import android.example.organizestudies.ui.main.activities.MainActivity
import android.example.organizestudies.utils.Errors
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.viewmodels.UserViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var myUserViewModel: UserViewModel
    private var username: String = ""
    private var password: String = ""

    private lateinit var usernameInputText: EditText
    private lateinit var passwordInputText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        configurationDbAndViewModel()

        goToMainActivity(binding)

        goToSignUpPage()

//        myUserViewModel.getAllUsers().observe(viewLifecycleOwner, Observer {
//            Utils.showToast(requireContext(), "haha")
//        })

        return binding.root
    }

    private fun configurationDbAndViewModel() {
        setHasOptionsMenu(true)
        myUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }


    private fun goToMainActivity(binding: FragmentLoginBinding) {

        usernameInputText = binding.userNameInput
        passwordInputText = binding.passwordInput.editText!!

        usernameInputText.addTextChangedListener {
            username = it.toString()
        }

        passwordInputText.addTextChangedListener {
            password = it.toString()
        }
        binding.goToHomeActivity.setOnClickListener {
            checkUserExistsInOurDb()
        }
    }

//    private suspend fun displayData(users: List<User>) {
//        withContext(Dispatchers.Main) {
//        }
//    }
//
//    private suspend fun doLoginAfterCheckingUserInputs() {
//        withContext(Dispatchers.Main) {
//        }
//    }

//    private fun readData() {
//        lateinit var users: List<User>
//        GlobalScope.launch {
//            users = myUserViewModel.getAllUsers()
//            println(users)
//            displayData(users)
//        }
//    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun checkUserExistsInOurDb() {
        GlobalScope.launch(Dispatchers.Main) {
            if (!Utils.checkInputsEmptyOrNot(username, password)) {
                Log.i(
                    "haha",
                    Utils.readingFromSharedPreferences(requireContext(), "username").toString()
                )
                requireActivity().runOnUiThread {
                    Utils.showToast(requireContext(), Errors.FILL_ALL_FIELDS_ERRORS)
                }
            } else {
                val user = myUserViewModel.getUserByUsernameAndPassword(
                    username,
                    password
                )
                if (user == null) {
                    requireActivity().runOnUiThread {
                        Utils.showToast(requireContext(), "Make sure your coordinates are valid !")
                    }
                } else {
                    requireActivity().runOnUiThread {
                        Utils.insertingKeyIntoSharedPreferences(
                            requireContext(),
                            "username",
                            user.username
                        )
                        Utils.insertingKeyIntoSharedPreferences(
                            requireContext(),
                            "id",
                            user.userId
                        )
                        makeLoginAndPasswordInputsEmpty()
                        Utils.startActivity(requireContext(), MainActivity::class.java)
                    }
                }
            }

        }
    }


    private fun goToSignUpPage() {
        binding.signUpButton.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_loginFragment_to_signupFragment2)
        }
    }

    private fun makeLoginAndPasswordInputsEmpty() {
        binding.userNameInput.setText("")
        binding.passwordInput.editText?.setText("")
        username = ""
        password = ""
    }


}