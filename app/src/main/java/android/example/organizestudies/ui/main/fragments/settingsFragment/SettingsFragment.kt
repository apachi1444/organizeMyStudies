package android.example.organizestudies.ui.main.fragments.settingsFragment

import android.example.organizestudies.R
import android.example.organizestudies.databinding.FragmentSettingsBinding
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.utils.consts.ConstKeys
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        settingsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        username = Utils.readingFromSharedPreferences(requireContext(), ConstKeys.USERNAME)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toggleImageSoundWhenClicked()
        initialUpdateProfile()
    }


    private fun toggleImageSoundWhenClicked() {
        binding.soundImage.setOnClickListener {
            binding.soundImage.setImageResource(R.drawable.sound_off_img)
        }
    }

    private fun initialUpdateProfile() {
        val user = settingsViewModel.getUser(username)
        binding.nameInput.hint = user.username
        binding.fieldInput.hint = user.grade
        binding.levelInput.hint = user.levelStudy
        binding.passwordInput.hint = user.password
        binding.confirmationInput.hint = user.password
    }

    private fun updateUser(){
//        settingsViewModel.updateUser(user)
    }
}