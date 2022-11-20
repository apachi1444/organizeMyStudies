package android.example.organizestudies.welcome.fragments

import android.example.organizestudies.R
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.viewmodels.UserViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SignupFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var items = arrayListOf("GI", "GE", "GRT", "GIL")

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
        val password = binding.passwordInput.editText?.text.toString()
        val grade = binding.gradeInput.text.toString()
        val levelStudy = binding.levelStudyInput.text.toString()
        val confirmPassword = binding.passwordInputConfirm.editText?.text.toString()

        if (!Utils.checkInputsEmptyOrNot(username, password, confirmPassword, levelStudy, grade)) {
            Utils.showToast(requireContext(), "Please make sure you are filling all fields ")
        } else {
            if (!Utils.checkPasswordAndConfirmPassword(password, confirmPassword)) {
                Utils.showToast(requireContext(), "Please make sure both passwords match !")
            } else {
                if (!checkUserNotExistingInOurDb(username)) {
                    val user =
                        User(UUID.randomUUID().toString(), username, password, grade, levelStudy)
                    myUserViewModel.addUser(user)
                    Utils.showToast(requireContext(), "Successfully added !")
                    // navigate back
                    findNavController().navigateUp()
                } else {
                    Utils.showToast(requireContext(), "Username already exists !")
                }
            }
        }


    }

    private fun checkUserNotExistingInOurDb(
        username: String,
    ): Boolean {
        var boolean = true
        runBlocking {
            val user = async { myUserViewModel.getUserByUsername(username) }
            if (user == null) boolean = false
        }
        return boolean
    }

}