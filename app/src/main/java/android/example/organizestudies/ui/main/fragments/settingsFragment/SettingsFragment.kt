package android.example.organizestudies.ui.main.fragments.settingsFragment

import android.app.Dialog
import android.example.organizestudies.R
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.databinding.CustomPopupChangeLanguageBinding
import android.example.organizestudies.databinding.FragmentSettingsBinding
import android.example.organizestudies.utils.StringsUtils
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.utils.consts.ConstKeys
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class SettingsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var customPopupChangeLanguageBinding: CustomPopupChangeLanguageBinding
    private lateinit var spinner: Spinner
    private lateinit var dialog: Dialog
    private lateinit var nameInput: EditText
    private lateinit var fieldInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmationInput: EditText
    private lateinit var levelInput: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        settingsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        // assigning the inputs of the user !
        nameInput = binding.nameInput
        passwordInput = binding.passwordInput
        confirmationInput = binding.confirmationInput
        levelInput = binding.levelInput
        fieldInput = binding.fieldInput
        dialog = Dialog(requireActivity().applicationContext)
        customPopupChangeLanguageBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.custom_popup_change_language,
            container,
            false
        )
        spinner = customPopupChangeLanguageBinding.listLanguagesSpinner
        binding.langImage.setOnClickListener { showDialogAddFile() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toggleImageSoundWhenClicked()
        initialUpdateProfile()
        binding.btnDone.setOnClickListener {
            if (!Utils.checkPasswordAndConfirmPassword(
                    passwordInput.text.toString(),
                    confirmationInput.text.toString()
                )
            ) {
                Utils.showToast(requireContext(), "Check Passwords Again !")
            } else {
                val user = User(
                    nameInput.text.toString(),
                    passwordInput.text.toString(),
                    fieldInput.text.toString(),
                    levelInput.text.toString()
                )
                Utils.showToast(requireContext(), "Profile Has Been Updated !")
                settingsViewModel.updateUser(user)
            }
        }
    }
    private fun toggleImageSoundWhenClicked() {
        binding.soundImage.setOnClickListener {
            binding.soundImage.setImageResource(R.drawable.sound_off_img)
        }
    }
    private fun initialUpdateProfile() {
        val username = Utils.readingFromSharedPreferences(requireContext(), ConstKeys.USERNAME)
        val user = settingsViewModel.getUser(username!!)
        binding.usernameText.text = username
        appendEditText(nameInput, user.username)
        appendEditText(fieldInput, user.grade)
        appendEditText(levelInput, user.levelStudy)
        appendEditText(passwordInput, user.password)
        appendEditText(confirmationInput, user.password)
    }
    private fun appendEditText(editText: EditText, value: String) {
        editText.text.append(value)
    }

    private fun showDialogAddFile() {
        configurationSpinnerModules()
        dialog.show()
        closeDialog(dialog)
    }
    private fun configurationSpinnerModules() {

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            StringsUtils.getLanguages()
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }
    private fun closeDialog(dialog: Dialog) {
        customPopupChangeLanguageBinding.btnOk.setOnClickListener {
            dialog.dismiss()
        }
    }
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }
    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}