package android.example.organizestudies.ui.main.fragments.profileFragment

import android.example.organizestudies.R
import android.example.organizestudies.adapters.FileBoxProfileFragmentAdapter
import android.example.organizestudies.data.entities.User
import android.example.organizestudies.databinding.FragmentProfileBinding
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.utils.consts.ConstKeys
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ProfileFragment : Fragment(), FileBoxProfileFragmentAdapter.OnFileListener {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var username: String
    private lateinit var user: User
    private lateinit var recyclerView: RecyclerView
    private lateinit var fileBoxProfileFragmentAdapter: FileBoxProfileFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        username = Utils.readingFromSharedPreferences(
            requireContext(),
            ConstKeys.USERNAME
        )!!
        user = profileViewModel.getUserByUsername(username)
        fileBoxProfileFragmentAdapter =
            FileBoxProfileFragmentAdapter(requireActivity().application, this)
        recyclerView = binding.recyclerViewFiles
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(
            requireActivity().applicationContext,
            3
        )
        recyclerView.adapter = fileBoxProfileFragmentAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.getAllFiles(username).observe(viewLifecycleOwner) {
            fileBoxProfileFragmentAdapter.dataSet = it
        }
        profileViewModel.countUserFiles(username).observe(viewLifecycleOwner) {
            numberFiles(it)
        }
        updateUi()
    }

    private fun updateUi() {
        binding.textName.text = username
        binding.textAbout.text = "${user.grade} ${user.levelStudy}"
        binding.numberModules.text = profileViewModel.countUserModules(username)
    }

    private fun numberFiles(number: Int) {
        binding.numberFiles.text = number.toString()
    }

    override fun onFileClick(position: Int) {
    }

}