package android.example.organizestudies.ui.main.fragments.settingsFragment

import android.app.Application
import android.example.organizestudies.data.repo.UserRepository
import androidx.lifecycle.AndroidViewModel

class SettingsViewModel ( application: Application) : AndroidViewModel(application){
    private var userRepository:UserRepository = UserRepository(application)

}