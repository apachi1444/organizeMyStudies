package android.example.organizestudies.ui.main.fragments.homeFragment

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
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
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import java.io.File


class HomeFragment : Fragment(), ModuleAdapter.OnModuleListener, FileAdapter.OnFileListener {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    //    private lateinit var bindingSingleModuleHomePageLayout2Binding: SingleModuleHomePageLayout2Binding
    private var moduleAdapter: ModuleAdapter = ModuleAdapter(this)
    private lateinit var recyclerView: RecyclerView
    private lateinit var recycleViewFiles: RecyclerView
    private var fileAdapter: FileAdapter = FileAdapter(this)

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

    }

    override fun onModuleClick(position: Int) {
        moduleAdapter.dataSet[position]
        val navDirections: NavDirections =
            HomeFragmentDirections.actionHomeFragmentToModuleDetailsFragment(moduleAdapter.dataSet[position].moduleName)
        findNavController().navigate(navDirections)
    }

    override fun onFileClick(position: Int) {
//        openPDF(fileAdapter.dataSet[position].filename)
    }

    private fun openPDF(url: String) {

        // Get the File location and file name.
        val file = File(Environment.getExternalStorageDirectory(), url)
        Log.d("pdfFIle", "" + file)

        // Get the URI Path of file.
        val uriPdfPath: Uri = FileProvider.getUriForFile(
            requireContext(),
            ApplicationProvider.getApplicationContext<Context>().packageName + ".provider",
            file
        )
        Log.d("pdfPath", "" + uriPdfPath)

        // Start Intent to View PDF from the Installed Applications.
        val pdfOpenIntent = Intent(Intent.ACTION_VIEW)
        pdfOpenIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        pdfOpenIntent.clipData = ClipData.newRawUri("", uriPdfPath)
        pdfOpenIntent.setDataAndType(uriPdfPath, "application/pdf")
        pdfOpenIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        try {
            startActivity(pdfOpenIntent)
        } catch (activityNotFoundException: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                "There is no app to load corresponding PDF",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    private fun openPdf(){

    }
}