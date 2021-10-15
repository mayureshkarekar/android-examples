package com.example.databindingdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.databindingdemo.databinding.DialogDataBindingBinding
import com.example.databindingdemo.databinding.FragmentDataBindingBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class FragmentDataBindingFragment : Fragment() {
    /* Fragment's binding member variable must handled correctly as Fragment view has different
    lifecycle than Fragment object and it may be created and destroyed multiple times. */
    private var _binding: FragmentDataBindingBinding? = null

    /* Kotlin's backing property to avoid null checking everywhere. Returns non-null reference of
    binding object. */
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflating the layout using Data Binding.
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_data_binding, container, false)

        // Updating the layout using Data Binding.
        binding.quote = Quote(
            "Arthur Ashe",
            "Start where you are. Use what you have. Do what you can.",
        )

        binding.btnOpenDialog.setOnClickListener {
            // Inflating the dialog using Data Binding.
            val quoteDialog = BottomSheetDialog(requireContext())
            val dialogBinding: DialogDataBindingBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.dialog_data_binding,
                null,
                false
            )

            // Updating the layout using Data Binding.
            dialogBinding.quote = Quote(
                "Ernest Hemingway",
                "In order to write about life first you must live it.",
            )
            dialogBinding.ivCloseDialog.setOnClickListener { quoteDialog.dismiss() }

            quoteDialog.setContentView(dialogBinding.root)
            quoteDialog.show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Setting binding variable to null when the fragment view is destroyed.
        _binding = null
    }
}