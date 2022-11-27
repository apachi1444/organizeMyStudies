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

class FileBoxProfileFragmentAdapter(
    application: Application,
    private val onFileListener: OnFileListener
) :
    RecyclerView.Adapter<FileBoxProfileFragmentAdapter.ViewHolder>() {
    private val fileRepository: FileRepository = FileRepository(application)
    var dataSet = listOf<File>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View, private val onFileListener: OnFileListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView
        val imageView: ImageView
        val imageViewTrashIcon: ImageView = view.findViewById(R.id.trash_icon)

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.file_name_file_box)
            imageView = view.findViewById(R.id.star_icon_file_box)
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
        if (!dataSet[position].starred) {
            holder.imageView.setImageResource(R.drawable.ic_star_border)
        } else {
            holder.imageView.setImageResource(R.drawable.ic_star)
        }


        holder.imageView.setOnClickListener {
            fileRepository.toggleStar(dataSet[position].filename)
            if (dataSet[position].starred) {
                holder.imageView.setImageResource(R.drawable.ic_star)
            } else {
                holder.imageView.setImageResource(R.drawable.ic_star_border)
            }
        }

        holder.imageViewTrashIcon.setOnClickListener {
            fileRepository.deleteFileByName(dataSet[position].filename)
        }


        (dataSet[position].filename).also {
            holder.textView.text = it
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_file_box_in_profile_page, parent, false)
        return ViewHolder(view, onFileListener)
    }
}

