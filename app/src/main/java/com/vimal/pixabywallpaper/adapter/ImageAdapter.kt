package com.vimal.pixabywallpaper.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.vimal.pixabywallpaper.R
import com.vimal.pixabywallpaper.activity.ImageActivity
import com.vimal.pixabywallpaper.api.model.ImageModel
import com.vimal.pixabywallpaper.databinding.ItemCategoryBinding
import java.lang.Exception

class ImageAdapter(var context: Context, var images: ArrayList<ImageModel>, val ru_name:String) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(view, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val res = images[position].webformatURL
        Picasso.get()
            .load(res)
            .into(holder.images, object : Callback {
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
        holder.name.text = images[position].user

        holder.itemView.setOnClickListener {
            val intetn= Intent(context, ImageActivity::class.java)
            intetn.putExtra("image",images[position].largeImageURL)
            context.startActivity(intetn)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
    inner class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        var images: ImageView

        var name:TextView
        init {
            images = itemView.findViewById<View>(R.id.img_category) as ImageView
            name=itemView.findViewById<View>(R.id.txt_category_name) as TextView
        }
    }
}
