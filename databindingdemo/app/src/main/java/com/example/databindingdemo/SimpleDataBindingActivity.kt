package com.example.databindingdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.databindingdemo.databinding.ActivitySimpleDataBindingBinding

class SimpleDataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySimpleDataBindingBinding
    private lateinit var quotesViewModel: QuotesViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting contentView using DataBinding. (No need to call setContentView()).
        binding = DataBindingUtil.setContentView(this, R.layout.activity_simple_data_binding)

        // Accessing views using DataBinding.
        /*binding.tvQuoteText.text = "The purpose of our lives is to be happy."
        binding.tvQuoteAuthor.text = "- Dalai Lama"*/


        // Using declarative way to bind views. (Also see layout file.)
        /*val quote = Quote(
            "John Lennon",
            "Life is what happens when you’re busy making other plans."
        )
        binding.quote = quote*/


        // Using LiveData and ViewModel for DataBiding.
        quotesViewModel = ViewModelProvider(this).get(QuotesViewModel::class.java)
        /*quotesViewModel.quoteLiveData.observe(this, {
            binding.quote = it
        })*/

        /* Setting button on click listener.
        NOTE: Using onClick attribute in layout is recommended. (Also see layout file.) */
        /*binding.btnNextQuote.setOnClickListener {
            quotesViewModel.nextQuote()
        }*/

        // Setting view model in layout.
        binding.quotesViewModel = quotesViewModel

        // Setting activity as lifecycle owner as we are using declarative way instead of setting observer.
        binding.lifecycleOwner = this
    }
}