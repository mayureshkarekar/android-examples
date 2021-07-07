package com.example.recyclerviewdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewdemo.R
import com.example.recyclerviewdemo.model.Image

class GridImageAdapter(private val context: Context, private val images: List<Image>) :
    RecyclerView.Adapter<GridImageAdapter.GridImageViewHolder>() {

    inner class GridImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.ivImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

        init {
            ivImage.setOnClickListener {
                Toast.makeText(
                    context,
                    images[adapterPosition].imageUrl,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_grid_image_list_item, parent, false)
        return GridImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridImageViewHolder, position: Int) {
        holder.tvTitle.text = context.getText(images[position].title)
        Glide.with(context).load(images[position].imageUrl).into(holder.ivImage);
    }

    override fun getItemCount() = images.size
}