package com.vimal.pixabywallpaper.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vimal.pixabywallpaper.R
import com.vimal.pixabywallpaper.activity.CategoryActivity
import com.vimal.pixabywallpaper.databinding.ItemCategoryBinding
import com.vimal.pixabywallpaper.model.CategoryModel
import java.lang.Exception

class CategoryAdapter(private val mList: List<CategoryModel>, val context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(view, parent, false)
        return ViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int,) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        Picasso.get()
            .load(ItemsViewModel.img)
            .into(holder.imageView, object : Callback {
                override fun onSuccess() {
                    holder.binding.csSuccItem.visibility= View.VISIBLE
                    holder.binding.csErrorItem.visibility= View.GONE
                    holder.binding.csLoadItem.visibility= View.GONE
                }

                override fun onError(e: Exception?) {
                    holder.binding.csSuccItem.visibility= View.GONE
                    holder.binding.csErrorItem.visibility= View.VISIBLE
                    holder.binding.csLoadItem.visibility= View.GONE
                }

            })
        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.ru_name

        holder.itemView.setOnClickListener {
            val intetn=Intent(context, CategoryActivity::class.java)
            intetn.putExtra("category",ItemsViewModel.category)
            intetn.putExtra("ru_name",ItemsViewModel.ru_name)
            context.startActivity(intetn)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = itemView.findViewById(R.id.img_category)
        val textView: TextView = itemView.findViewById(R.id.txt_category_name)
    }
}