package android.example.organizestudies.adapters

import android.example.organizestudies.R
import android.example.organizestudies.data.entities.File
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FileAdapter(private val onFileListener: OnFileListener) :
    RecyclerView.Adapter<FileAdapter.ViewHolder>() {
    var dataSet = listOf<File>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View, private val onFileListener: OnFileListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.fileName)
            imageView = view.findViewById(R.id.moduleImage)
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
        holder.imageView.setImageResource(dataSet[position].moduleImage)
        holder.textView.text = dataSet[position].filename
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recent_pdf_layout, parent, false)
        return ViewHolder(view, onFileListener)
    }
}

