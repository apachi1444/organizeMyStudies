package android.example.organizestudies.adapters

import android.example.organizestudies.R
import android.example.organizestudies.data.entities.Module
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ModuleAdapter(private val onModuleListener: OnModuleListener) :
    RecyclerView.Adapter<ModuleAdapter.ViewHolder>() {
    var dataSet = listOf<Module>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View, private val onModuleListener: OnModuleListener) :
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
            onModuleListener.onModuleClick(adapterPosition)
        }
    }

    fun getModuleAtPosition(position: Int): Module {
        return dataSet[position]
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.single_module_home_page_layout_2, viewGroup, false)
        return ViewHolder(view, onModuleListener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.imageView.setImageResource(dataSet[position].imageModule)
        viewHolder.textView.text = dataSet[position].moduleName
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnModuleListener {
        fun onModuleClick(position: Int)
    }
}
