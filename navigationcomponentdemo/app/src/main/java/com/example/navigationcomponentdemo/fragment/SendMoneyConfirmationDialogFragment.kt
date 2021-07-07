package com.example.navigationcomponentdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponentdemo.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_send_money_confirmation.*

class SendMoneyConfirmationDialogFragment : BottomSheetDialogFragment() {
    private val args: SendMoneyConfirmationDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_send_money_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvSendMoneyMessage.text =
            getString(
                R.string.do_you_want_send_rs_value_to_value,
                args.amount.toString(),
                args.recipientName
            )

        btnNo.setOnClickListener { dismiss() }
        btnYes.setOnClickListener {
            Toast.makeText(
                context,
                getString(R.string.money_sent_to_value, args.recipientName),
                Toast.LENGTH_SHORT
            ).show()
            dismiss()
        }
    }
}