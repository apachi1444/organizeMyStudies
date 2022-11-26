package android.example.organizestudies.adapters

import android.example.organizestudies.R
import android.example.organizestudies.data.entities.Module
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StarredModuleRowInStarredFragmentAdapter(private val onModuleListener: OnModuleListener) :
    RecyclerView.Adapter<StarredModuleRowInStarredFragmentAdapter.ViewHolder>() {
    var dataSet = listOf<Module>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View, private val onModuleListener: OnModuleListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        var moduleName: TextView
        var categoryModuleName: TextView

        init {
            // Define click listener for the ViewHolder's View.
            moduleName = view.findViewById(R.id.module_name)
            categoryModuleName = view.findViewById(R.id.category_module_name)
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
        holder.categoryModuleName.text =
            dataSet[position].grade + " " + dataSet[position].levelStudy
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_starred_module_row_starred_fragment, parent, false)
        return ViewHolder(view, onModuleListener)
    }
}
