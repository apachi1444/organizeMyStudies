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

class FileAdapterSingleModule(
    application: Application,
    private val onFileSingleModuleListener: OnFileSingleModuleListener
) :
    RecyclerView.Adapter<FileAdapterSingleModule.ViewHolder>() {
    var dataSet = listOf<File>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val fileRepository: FileRepository = FileRepository(application)

    class ViewHolder(
        view: View,
        private val onFileSingleModuleListener: OnFileSingleModuleListener
    ) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView
        val imageView: ImageView
        val trashIcon :ImageView = view.findViewById(R.id.delete_file_icon)

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.fileName)
            imageView = view.findViewById(R.id.starIcon)
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onFileSingleModuleListener.onFileClick(adapterPosition)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnFileSingleModuleListener {
        fun onFileClick(position: Int)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (!dataSet[position].starred) {
            holder.imageView.setImageResource(R.drawable.ic_star_border)
        } else {
            holder.imageView.setImageResource(R.drawable.ic_star)
        }

        holder.trashIcon.setOnClickListener {
            fileRepository.deleteFileByName(dataSet[position].filename)
        }

        holder.imageView.setOnClickListener {
            fileRepository.toggleStar(dataSet[position].filename)
            if (dataSet[position].starred) {
                holder.imageView.setImageResource(R.drawable.ic_star)
            } else {
                holder.imageView.setImageResource(R.drawable.ic_star_border)
            }
        }

        (dataSet[position].filename + dataSet[position].extension).also {
            holder.textView.text = it
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.file_row_item_in_module_details, parent, false
        )
        return ViewHolder(view, onFileSingleModuleListener)
    }


}