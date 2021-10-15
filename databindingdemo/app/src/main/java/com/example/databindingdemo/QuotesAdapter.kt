package com.example.databindingdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.databindingdemo.databinding.LayoutQuoteListItemBinding

/* RecyclerView Adapter using data binding. */
class QuotesAdapter(private val quotes: List<Quote>) :
    RecyclerView.Adapter<QuotesAdapter.QuoteViewHolder>() {
    // Instead of View object modify ViewHolder constructor to accept generated LayoutBinding object.
    inner class QuoteViewHolder(val quoteListItemView: LayoutQuoteListItemBinding) :
        RecyclerView.ViewHolder(quoteListItemView.root) {
        init {
            quoteListItemView.ivImage.setOnClickListener {
                Toast.makeText(
                    quoteListItemView.root.context,
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
        // Binding the view using data binding.
        holder.quoteListItemView.quote = quotes[position]
        holder.quoteListItemView.executePendingBindings()
    }

    override fun getItemCount() = quotes.size
}