package android.example.organizestudies.welcome.fragments

import android.content.Intent
import android.example.organizestudies.R
import android.example.organizestudies.data.dao.UserDb
import android.example.organizestudies.data.dao.UserDbDao
import android.example.organizestudies.databinding.FragmentLoginBinding
import android.example.organizestudies.main.MainActivity
import android.example.organizestudies.utils.Errors
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.viewmodels.UserViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var dao: UserDbDao
    private lateinit var myUserViewModel: UserViewModel
    private lateinit var username: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        configurationDbAndViewModel()
        goToMainActivity(binding)
        goToSignUpPage()
        return binding.root
    }

    private fun configurationDbAndViewModel() {
        val application = requireNotNull(this.activity).application
        dao = UserDb.getInstance(application).userDbDao()
        setHasOptionsMenu(true)
        myUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }


    private fun goToMainActivity(binding: FragmentLoginBinding) {
        val intent = Intent(context, MainActivity::class.java)
        print(binding.userNameInput.text.toString() + "jdsjfj")
        username = binding.userNameInput.text.toString()
        password = binding.passwordInput.editText?.text.toString()

        Log.i("haha", "$username $password")
        binding.goToHomeActivity.setOnClickListener {
            runBlocking {
                print("$username $password")
                checkUserExistsInOurDb()
            }
//            dbLogicWithUserInputs()
//            startActivity(intent)
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

    private fun checkUserExistsInOurDb() {
        if (!Utils.checkInputsEmptyOrNot(username, password)) {
            Utils.showToast(requireContext(), Errors.FILL_ALL_FIELDS_ERRORS)
        } else {
            runBlocking {
                launch {
                    print("$username $password")
                    val user = myUserViewModel.getUserByUsernameAndPassword(
                        username,
                        password
                    )
                    print(user.toString() + "skdljqfsdf")
                    if (user == null) {
                        Log.i("login", "no")
                        Utils.showToast(
                            requireContext(),
                            "Please check your username and password !"
                        )
                    } else {
                        Log.i("login", "yes")
                    }
                }

            }
        }
    }


    private fun goToSignUpPage() {
        binding.signUpButton.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }


}