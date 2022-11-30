package android.example.organizestudies.ui.main.fragments.homeFragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.example.organizestudies.BuildConfig
import android.example.organizestudies.R
import android.example.organizestudies.adapters.FileAdapter
import android.example.organizestudies.adapters.ModuleAdapter
import android.example.organizestudies.databinding.FragmentHomeBinding
import android.example.organizestudies.ui.welcome.activities.WelcomeActivity
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.utils.consts.ConstKeys
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.*
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File


class HomeFragment : Fragment(), ModuleAdapter.OnModuleListener, FileAdapter.OnFileListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    private var moduleAdapter: ModuleAdapter = ModuleAdapter(this)
    private lateinit var recyclerView: RecyclerView
    private lateinit var recycleViewFiles: RecyclerView
    private var fileAdapter: FileAdapter = FileAdapter(this)
    private val target = Intent(Intent.ACTION_VIEW)

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

        // recycler view of modules
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(
            requireActivity().applicationContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = moduleAdapter

        // recycler view of files
        recycleViewFiles = binding.recyclerViewRecentPdfsOpened
        recycleViewFiles.layoutManager = LinearLayoutManager(
            requireActivity().applicationContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        recycleViewFiles.setHasFixedSize(true)
        recycleViewFiles.adapter = fileAdapter

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        homeViewModel.allModules.observe(viewLifecycleOwner) {
            it.forEach { userModules ->
                moduleAdapter.dataSet = userModules.modules
            }
        }

        homeViewModel.allFiles(
            Utils.readingFromSharedPreferences(
                requireContext(),
                ConstKeys.USERNAME
            )!!
        ).observe(viewLifecycleOwner) {
            fileAdapter.dataSet = it
        }

        updatingHelloText()
        goToProfile()
        goToStarredFragment()
        goToModulesDetails()
    }

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
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settingsMenu -> {
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

    }

    override fun onModuleClick(position: Int) {
        moduleAdapter.dataSet[position]
        val navDirections: NavDirections =
            HomeFragmentDirections.actionHomeFragmentToModuleDetailsFragment(moduleAdapter.dataSet[position].moduleName)
        findNavController().navigate(navDirections)
    }

    override fun onFileClick(position: Int) {
        val filename = fileAdapter.dataSet[position].filename
        open(filename)
    }

    private fun open(filename: String) {
        val file =
            File(Environment.getExternalStorageDirectory().absolutePath + "/$filename")
        val uri: Uri =
            FileProvider.getUriForFile(
                requireContext(),
                BuildConfig.APPLICATION_ID,
                file
            )
        target.setDataAndType(uri, "application/pdf")
        target.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        target.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        target.flags = Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY

        try {
            // validate the device can open your file !
            startActivity(target)
        } catch (e: ActivityNotFoundException) {
            // Instruct the user to install a PDF reader here, or something
        }
    }

}