package com.example.viewbindingdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.viewbindingdemo.databinding.FragmentViewBindingBinding

class ViewBindingFragment : Fragment() {
    /* Fragment's binding member variable must handled correctly as Fragment view has different
    lifecycle than Fragment object and it may be created and destroyed multiple times. */
    private var _binding: FragmentViewBindingBinding? = null

    /* Kotlin's backing property to avoid null checking everywhere. Returns non-null reference of
    binding object. */
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflating the layout using View Binding.
        _binding = FragmentViewBindingBinding.inflate(inflater, container, false)

        // Updating the layout using View Binding.
        /* View Binding will generate the Binding classes for all the layouts (unless ignored using
        tools:viewBindingIgnore = "true" in layout file). It will automatically convert the ids of
        layout to camel case (i.e removes underscore and convert next character to uppercase) */
        binding.tvQuote.text = "Start where you are. Use what you have. Do what you can."
        binding.tvAuthor.text = "Arthur Ashe"

        // Accessing views of included layout.
        Glide.with(this).load("https://picsum.photos/200/200.jpg")
            .into(binding.includeLayoutImage.ivImage)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Setting binding variable to null when the fragment view is destroyed.
        _binding = null
    }
}