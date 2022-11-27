package android.example.organizestudies.ui.main.fragments.moduleDetailsFragment

import android.example.organizestudies.R
import android.example.organizestudies.adapters.FileAdapterSingleModule
import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.entities.Module
import android.example.organizestudies.databinding.FragmentModuleDetailsBinding
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

// TODO: Rename parameter arguments, choose names that match


class ModuleDetailsFragment : Fragment(), FileAdapterSingleModule.OnFileSingleModuleListener {

    private lateinit var fragmentModuleDetailsBinding: FragmentModuleDetailsBinding
    private lateinit var moduleDetailsViewModel: ModuleDetailsViewModel
    private lateinit var moduleName: String
    private lateinit var fileAdapterSingleModule: FileAdapterSingleModule
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentModuleDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_module_details, container, false)

        moduleDetailsViewModel = ViewModelProvider(this)[ModuleDetailsViewModel::class.java]

        moduleName = moduleDetailsViewModel.getModuleNameFromNavigation(requireArguments())

        fileAdapterSingleModule = FileAdapterSingleModule(requireActivity().application, this)

        recyclerView = fragmentModuleDetailsBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(
            requireActivity().applicationContext,
            LinearLayoutManager.VERTICAL, false
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = fileAdapterSingleModule

        return fragmentModuleDetailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val module = moduleDetailsViewModel.getModule(moduleName)
        moduleDetailsViewModel.files(
            module.moduleName, Utils.readingFromSharedPreferences(
                requireContext(),
                ConstKeys.USERNAME
            )!!
        ).observe(viewLifecycleOwner) {
            fileAdapterSingleModule.dataSet = it
        }
        updateUi(module)
    }

    private fun updateUi(module: Module) {
        fragmentModuleDetailsBinding.firstTag.text = module.hashTag
        fragmentModuleDetailsBinding.imageModule.setImageResource(module.imageModule)
        fragmentModuleDetailsBinding.titleModule.text = module.moduleName
        fragmentModuleDetailsBinding.professorName.text = module.professor
        fragmentModuleDetailsBinding.semesterText.text = module.period.toString() + " semester"
        fragmentModuleDetailsBinding.iconStar.setOnClickListener {
            moduleDetailsViewModel.starModule(
                Utils.readingFromSharedPreferences(
                    requireContext(),
                    ConstKeys.USERNAME
                )!!, moduleName
            )
            Utils.showToast(
                requireContext(),
                "$moduleName module is now starred !"
            )
        }
    }

    override fun onFileClick(position: Int) {
        val file: File = fileAdapterSingleModule.dataSet[position]

    }
}