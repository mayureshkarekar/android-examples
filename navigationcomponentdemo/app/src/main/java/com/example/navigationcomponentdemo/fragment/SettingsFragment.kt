package com.example.navigationcomponentdemo.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentdemo.NavMainDirections
import com.example.navigationcomponentdemo.utils.DataHolder
import com.example.navigationcomponentdemo.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etDefaultAmount.setText(DataHolder.defaultTransferAmount.value.toString())

        btnSave.setOnClickListener {
            val amount = etDefaultAmount.text.toString()

            if (amount.isEmpty()) {
                Toast.makeText(context, R.string.enter_amount, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            DataHolder.defaultTransferAmount.value = amount.toFloat()

            Toast.makeText(context, R.string.default_amount_saved, Toast.LENGTH_SHORT).show()
        }

        btnAbout.setOnClickListener {
            // Navigate using global action.
            val action = NavMainDirections.actionGlobalDestAboutFragment()
            findNavController().navigate(action)
        }
    }
}