package android.example.organizestudies.main.fragments

import android.content.Intent
import android.example.organizestudies.R
import android.example.organizestudies.databinding.FragmentHomeBinding
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.welcome.OnlyOnceWelcomeActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val username = Utils.readingFromSharedPreferences(requireContext(), "username")
        val id = Utils.readingFromSharedPreferences(requireContext(), "id")

        Log.i("bool", username.toString())
        Log.i("bool", id.toString())

        goToProfile()
        goToStarredFragment()
        callChooseFromDevice()
        goToModulesDetails()
    }


    private fun callChooseFromDevice() {
        binding.calendarBox.setOnClickListener {
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "application/*"
            startActivityForResult(intent, Utils.CHOOSE_PDF_FROM_DEVICE)
        }
    }

    private fun goToStarredFragment() {
        binding.starredBox.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_starredFragment)
        }
    }


    private fun goToProfile() {
        binding.homeImageProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
//        binding.homeImageProfile.setOnClickListener {
//            Log.i("image Clicked ", "just clicked ")
//            parentFragmentManager.commit {
//                setReorderingAllowed(true).replace<ProfileFragment>(R.id.fragment_container_view)
//            }
//        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settingsMenu -> {
                Toast.makeText(requireContext(), "clicked on the search icon ", Toast.LENGTH_SHORT)
                    .show()
                goToSettingsFragment()
                true
            }
            R.id.logMenu -> {
                logOutButton()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logOutButton() {
        Utils.deletingInformationFromSharedPreferencesWhenLogOut(requireContext())
        requireActivity().finish()
        Utils.startActivity(requireContext(), OnlyOnceWelcomeActivity::class.java)

    }

    private fun goToSettingsFragment() {
        requireView().findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
    }

    private fun goToModulesDetails() {
        binding.springModule.setOnClickListener {
            requireView().findNavController()
                .navigate(R.id.action_homeFragment_to_moduleDetailsFragment)
        }
    }
}