package android.example.organizestudies.ui.main.fragments.starredFragment

import android.example.organizestudies.R
import android.example.organizestudies.adapters.StarredFileInStarredFragmentAdapter
import android.example.organizestudies.adapters.StarredModuleInStarredFragmentAdapter
import android.example.organizestudies.databinding.FragmentStarredBinding
import android.example.organizestudies.utils.Utils
import android.example.organizestudies.utils.consts.ConstKeys
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StarredFragment : Fragment(), StarredModuleInStarredFragmentAdapter.OnModuleListener,
    StarredFileInStarredFragmentAdapter.OnFileListener {

    private lateinit var recyclerViewModules: RecyclerView
    private lateinit var recyclerViewFiles: RecyclerView

    private lateinit var starredViewModel: StarredViewModel

    private lateinit var fragmentStarredBinding: FragmentStarredBinding
    private lateinit var adapter: StarredModuleInStarredFragmentAdapter

    private lateinit var adapterStarredFiles: StarredFileInStarredFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentStarredBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_starred, container, false)

        starredViewModel = ViewModelProvider(this)[StarredViewModel::class.java]

        adapter = StarredModuleInStarredFragmentAdapter(requireActivity().application, this)
        adapterStarredFiles =
            StarredFileInStarredFragmentAdapter(requireActivity().application, this)

        recyclerViewModules = fragmentStarredBinding.recyclerViewStarredModules
        recyclerViewModules.setHasFixedSize(true)
        recyclerViewModules.layoutManager = LinearLayoutManager(
            requireActivity().applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerViewModules.adapter = adapter

        recyclerViewFiles = fragmentStarredBinding.recyclerViewStarredFiles
        recyclerViewFiles.setHasFixedSize(true)
        recyclerViewFiles.layoutManager = LinearLayoutManager(
            requireActivity().applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerViewFiles.adapter = adapterStarredFiles

        return fragmentStarredBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        starredViewModel.modules(
            Utils.readingFromSharedPreferences(
                requireContext(),
                ConstKeys.USERNAME
            )!!
        ).observe(viewLifecycleOwner) {
            adapter.dataSet = it
        }

        starredViewModel.files().observe(viewLifecycleOwner) {
            adapterStarredFiles.dataSet = it
        }

    }

    override fun onFileClick(position: Int) {
    }
}