package android.example.organizestudies.welcome

import android.content.Intent
import android.example.organizestudies.R
import android.example.organizestudies.databinding.FragmentLoginBinding
import android.example.organizestudies.entities.User
import android.example.organizestudies.main.MainActivity
import android.example.organizestudies.repository.UserDb
import android.example.organizestudies.repository.UserDbDao
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment


class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentLoginBinding
    private lateinit var dao: UserDbDao

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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        val application = requireNotNull(this.activity).application


//        dao = UserDb.getInstance(application).userDbDao

        goToMainActivity(binding)

        printInputNames()

        return binding.root
    }

    private fun goToMainActivity(binding: FragmentLoginBinding) {
        val intent = Intent(context, MainActivity::class.java)
        username = binding.userNameInput.text
        password = binding.passwordInput.text
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
        val user = User(1, username.toString(), password.toString())
        dao.insert(user)
    }

}