package android.example.organizestudies.adapters

import android.example.organizestudies.R
import android.example.organizestudies.data.entities.Module
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FileAdapter(private val onModuleListener: OnFileListener) :
    RecyclerView.Adapter<FileAdapter.ViewHolder>() {
    private var dataSet = listOf<Module>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View, private val onModuleListener: OnFileListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.textModule)
            imageView = view.findViewById(R.id.imageModule)
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onModuleListener.onFileClick(adapterPosition)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnFileListener {
        fun onFileClick(position: Int)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileAdapter.ViewHolder {
        TODO("Not yet implemented")
    }
}

