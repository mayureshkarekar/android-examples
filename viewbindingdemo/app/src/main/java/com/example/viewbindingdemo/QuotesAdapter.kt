package com.example.viewbindingdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viewbindingdemo.databinding.LayoutQuoteListItemBinding

/* RecyclerView Adapter using View Binding. */
class QuotesAdapter(private val quotes: List<Quote>) :
    RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>() {
    // Instead of View object modify ViewHolder constructor to accept generated LayoutBinding object.
    inner class QuoteViewHolder(val binding: LayoutQuoteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.ivImage.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    quotes[adapterPosition].imageUrl,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        // Inflating view using generated binding class.
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = LayoutQuoteListItemBinding.inflate(layoutInflater, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        // Binding the view using view binding.
        holder.binding.apply {
            tvQuoteText.text = quotes[position].quoteText
            tvQuoteAuthor.text = quotes[position].author
            Glide.with(this.ivImage).load(quotes[position].imageUrl).into(this.ivImage)
        }
    }

    override fun getItemCount() = quotes.size
}