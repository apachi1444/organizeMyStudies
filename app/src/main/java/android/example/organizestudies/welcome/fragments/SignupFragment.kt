package android.example.organizestudies.welcome.fragments

import android.example.organizestudies.R
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.main.UserViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SignupFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var myUserViewModel: UserViewModel

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
    ): View? {

        myUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_signup, container, false)


        return view
    }

    private fun addUserToDB() {

    }

}