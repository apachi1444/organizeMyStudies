package android.example.organizestudies.ui.main.fragments.homeFragment

import android.example.organizestudies.R
import android.example.organizestudies.databinding.FragmentHomeBinding
import android.example.organizestudies.ui.welcome.activities.WelcomeActivity
import android.example.organizestudies.utils.Utils
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var homeViewModel: HomeViewModel


    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val username = Utils.readingFromSharedPreferences(requireContext(), "username")
//        val id = Utils.readingFromSharedPreferences(requireContext(), "id")
//
//        Log.i("bool", username.toString())
//        Log.i("bool", id.toString())

        updatingHelloText()
        goToProfile()
        goToStarredFragment()
//        showModulesUser()
        goToModulesDetails()

    }

//    private fun showModulesUser() {
//        val list = homeViewModel.getModulesUser(lifecycleScope, requireContext())
//        list.forEach { it ->
//            it.modules.forEach {
//                Log.i("module ${it.grade}", it.toString())
//            }
//        }
//    }


    private fun updatingHelloText() {
        val usernameFromSharedPreferences =
            Utils.readingFromSharedPreferences(requireContext(), "username")
        binding.helloText.text = "Hello $usernameFromSharedPreferences"
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
        Utils.startActivity(requireContext(), WelcomeActivity::class.java)
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