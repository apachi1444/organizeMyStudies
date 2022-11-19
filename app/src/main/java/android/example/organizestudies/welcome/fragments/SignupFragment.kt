package android.example.organizestudies.welcome.fragments

import android.example.organizestudies.R
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.main.UserViewModel
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SignupFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var items = arrayListOf<String>("GI", "GE", "GRT", "GIL")

    private lateinit var myUserViewModel: UserViewModel
    private lateinit var binding: android.example.organizestudies.databinding.FragmentSignupBinding

//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private lateinit var arrayAdapter: ArrayAdapter<String>

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
    ): View {

        myUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

        goToBackToLoginPage()

        binding.buttonSignUp.setOnClickListener {
            addUserToDB()
        }
        return binding.root
    }

//    private fun logicMultipleChoiceInput(){
//        autoCompleteTextView = binding.autoCompleteText
//
//        arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
//
//        autoCompleteTextView.setAdapter(arrayAdapter)
//
//        autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
//            val item = adapterView.getItemAtPosition(i).toString()
//
//        }
//    }

    private fun goToBackToLoginPage() {
        binding.buttonGoToLogin.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun addUserToDB() {
        val username = binding.userNameInput.text.toString()
        val password = binding.passwordInput.text.toString()
        val grade = binding.gradeInput.text.toString()
        val levelStudy = binding.levelStudyInput.text.toString()
        val confirmPassword = binding.passwordInputConfirm.text.toString()

        if (checkIfAllInputsAreEmpty(username, password, confirmPassword, levelStudy, grade)) {
            Toast.makeText(
                requireContext(),
                "Please make sure you are filling all fields ",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            if (!checkPasswordAndConfirmPassword(password, confirmPassword)) {
                Toast.makeText(
                    requireContext(),
                    "Please make sure both passwords match !",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (inputCheck(username, password, confirmPassword, levelStudy, grade)) {
                    val user = User(1, username, password, grade, levelStudy)

                    myUserViewModel.addUser(user)

                    Toast.makeText(requireContext(), "Successfully added !", Toast.LENGTH_SHORT)
                        .show()

                    // navigate back
                    findNavController().navigateUp()

                } else {
                    Toast.makeText(requireContext(), "Successfully not added !", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }


    }

    private fun inputCheck(
        username: String,
        password: String,
        confirmPassword: String,
        levelStudy: String,
        grade: String
    ): Boolean {
        return !(TextUtils.isEmpty(username) && TextUtils.isEmpty(password) && TextUtils.isEmpty(
            confirmPassword
        ) && TextUtils.isEmpty(levelStudy) && TextUtils.isEmpty(grade))
    }

    private fun checkPasswordAndConfirmPassword(
        password: String,
        confirmPassword: String
    ): Boolean {
        return (password == confirmPassword)
    }

    private fun checkIfAllInputsAreEmpty(
        username: String,
        password: String,
        confirmPassword: String,
        levelStudy: String,
        grade: String
    ): Boolean {

        if (username.isEmpty() && password.isEmpty()
            && confirmPassword.isEmpty()
            && levelStudy.isEmpty()
            && grade.isEmpty()
        ) {
            return true
        }
        return false
    }

}