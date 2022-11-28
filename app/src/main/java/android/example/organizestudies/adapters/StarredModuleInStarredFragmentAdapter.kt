package android.example.organizestudies.adapters

import android.app.Application
import android.example.organizestudies.R
import android.example.organizestudies.data.entities.relations.UserModuleCrossRef
import android.example.organizestudies.data.repo.UserModuleCrossRefRepository
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StarredModuleInStarredFragmentAdapter(
    application: Application,
    private val onModuleListener: OnModuleListener
) :
    RecyclerView.Adapter<StarredModuleInStarredFragmentAdapter.ViewHolder>() {
    var dataSet = listOf<UserModuleCrossRef>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val userModuleCrossRefRepository: UserModuleCrossRefRepository =
        UserModuleCrossRefRepository(application)

    class ViewHolder(view: View, private val onModuleListener: OnModuleListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        var moduleName: TextView
        val moduleImage: ImageView = view.findViewById(R.id.image_module)
        val starIcon: ImageView = view.findViewById(R.id.trash_button)

        init {
            // Define click listener for the ViewHolder's View.
            moduleName = view.findViewById(R.id.module_name)
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onModuleListener.onFileClick(adapterPosition)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnModuleListener {
        fun onFileClick(position: Int)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.moduleName.text = dataSet[position].moduleName
        holder.moduleImage.setImageResource(dataSet[position].moduleImage)
        holder.starIcon.setOnClickListener {
            userModuleCrossRefRepository.toggleStar(dataSet[position].moduleName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_starred_module_row_starred_fragment, parent, false)
        return ViewHolder(view, onModuleListener)
    }

}
