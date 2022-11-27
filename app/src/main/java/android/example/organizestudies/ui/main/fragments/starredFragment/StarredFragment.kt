package android.example.organizestudies.ui.main.fragments.starredFragment

import android.example.organizestudies.R
import android.example.organizestudies.adapters.StarredModuleRowInStarredFragmentAdapter
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

class StarredFragment : Fragment(), StarredModuleRowInStarredFragmentAdapter.OnModuleListener {

    private lateinit var recyclerViewModules: RecyclerView
    private lateinit var starredViewModel: StarredViewModel

    private lateinit var fragmentStarredBinding: FragmentStarredBinding
    private var adapter: StarredModuleRowInStarredFragmentAdapter =
        StarredModuleRowInStarredFragmentAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentStarredBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_starred, container, false)

        starredViewModel = ViewModelProvider(this)[StarredViewModel::class.java]

        recyclerViewModules = fragmentStarredBinding.recyclerViewStarredModules
        recyclerViewModules.setHasFixedSize(true)
        recyclerViewModules.layoutManager = LinearLayoutManager(
            requireActivity().applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )

        recyclerViewModules.adapter = adapter
        return fragmentStarredBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        starredViewModel.modules(
            Utils.readingFromSharedPreferences(
                requireContext(),
                ConstKeys.USERNAME
            )!!
        ).observe(viewLifecycleOwner) { it ->
            it.forEach {
                adapter.dataSet = it.modules
            }
        }
    }

    override fun onFileClick(position: Int) {
        TODO("Not yet implemented")
    }
}