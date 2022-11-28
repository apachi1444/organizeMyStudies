package android.example.organizestudies.adapters

import android.app.Application
import android.example.organizestudies.R
import android.example.organizestudies.data.entities.File
import android.example.organizestudies.data.repo.FileRepository
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StarredFileInStarredFragmentAdapter(
    application: Application,
    private val onFileListener: OnFileListener
) :
    RecyclerView.Adapter<StarredFileInStarredFragmentAdapter.ViewHolder>() {
    var dataSet = listOf<File>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val fileRepository = FileRepository(application)

    class ViewHolder(view: View, private val onFileListener: OnFileListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        var moduleName: TextView
        var categoryModuleName: TextView
        val imageStar: ImageView = view.findViewById(R.id.trash_button)

        init {
            // Define click listener for the ViewHolder's View.
            moduleName = view.findViewById(R.id.module_name)
            categoryModuleName = view.findViewById(R.id.category_name)
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onFileListener.onFileClick(adapterPosition)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnFileListener {
        fun onFileClick(position: Int)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.moduleName.text = dataSet[position].filename
        holder.categoryModuleName.text =
            dataSet[position].moduleName
        holder.imageStar.setOnClickListener {
            fileRepository.toggleStar(dataSet[position].filename)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_starred_file_row_starred_fragment, parent, false)
        return ViewHolder(view, onFileListener)
    }

}
