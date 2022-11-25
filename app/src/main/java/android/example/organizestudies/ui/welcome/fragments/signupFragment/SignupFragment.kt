package android.example.organizestudies.ui.welcome.fragments.signupFragment

import android.example.organizestudies.R
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.viewmodels.UserViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class SignupFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var spinner: Spinner

    private lateinit var myUserViewModel: UserViewModel
    private lateinit var binding: android.example.organizestudies.databinding.FragmentSignupBinding

//    private lateinit var autoCompleteTextView: AutoCompleteTextView
//    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        myUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

        configurationSpinnerGrades()

        goToBackToLoginPage()

        addUserToDbWhenClickingOnSignupButton()

        return binding.root

    }

    private fun addUserToDbWhenClickingOnSignupButton() {
        binding.buttonSignUp.setOnClickListener {
            addUserToDB()
        }
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

    private fun configurationSpinnerGrades() {
        spinner = binding.gradeInput

        var adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gradeStrings,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

    }


    private fun goToBackToLoginPage() {
        binding.buttonGoToLogin.setOnClickListener {
            findNavController().navigateUp()
        }
    }


    private fun addUserToDB() {
        val username = binding.userNameInput.text.toString()
        val password = binding.passwordInput.editText?.text.toString()
        val grade = binding.gradeInput.selectedItem.toString()
        val levelStudy = binding.levelStudyInput.selectedItem.toString()
        val confirmPassword = binding.passwordInputConfirm.editText?.text.toString()

        if (!Utils.checkInputsEmptyOrNot(username, password, confirmPassword, levelStudy, grade)) {
            Utils.showToast(requireContext(), "Please make sure you are filling all fields ")
        } else {
            if (!Utils.checkPasswordAndConfirmPassword(password, confirmPassword)) {
                Utils.showToast(requireContext(), "Please make sure both passwords match !")
            } else {
                if (checkUserNotExistingInOurDb(username)) {

                    val user =
                        User(username, password, grade, levelStudy)

                    myUserViewModel.addUser(user)
                    myUserViewModel.addModules(user)
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

    override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
        var text = p0.getItemAtPosition(p2).toString()
        Utils.showToast(p0.context, text)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}