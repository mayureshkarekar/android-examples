package com.example.navigationcomponentdemo.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponentdemo.utils.DataHolder
import com.example.navigationcomponentdemo.R
import kotlinx.android.synthetic.main.fragment_send_money_to_recipient.*

class SendMoneyToRecipientFragment : Fragment(R.layout.fragment_send_money_to_recipient) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()


        // Parsing the received arguments.
        // val recipientName = arguments?.getString("recipientName")


        // Parsing the received arguments using SafeArgs. (Recommended way as it is type safe)
        val arguments: SendMoneyToRecipientFragmentArgs by navArgs()
        val recipientName = arguments.recipientName

        tvSendMoneyLabelToRecipient.text = getString(R.string.send_money_to_value, recipientName)
        etAmount.setText(DataHolder.defaultTransferAmount.value.toString())
        DataHolder.defaultTransferAmount.observe(viewLifecycleOwner) {
            // Updating the default amount when defaultTransferAmount value changes.
            etAmount.setText(it.toString())
        }

        btnNext.setOnClickListener {
            val amount = etAmount.text.toString()

            if (amount.isEmpty()) {
                Toast.makeText(context, R.string.enter_amount, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            // Navigate to dialog. (NOTE: As the destination is dialog, animations will not work if defined.)
            val action =
                SendMoneyToRecipientFragmentDirections
                    .actionDestSendMoneyToRecipientFragmentToSendMoneyConfirmationDialogFragment(
                        recipientName,
                        amount.toFloat()
                    )
            navController.navigate(action)
        }

        btnFinish.setOnClickListener {
            /* Navigating to existing HomeFragment (Recommended way). i.e. When back is pressed
            screen will directly jump to existing HomeFragment instead of previous screen. In simple
            words, BackStack entries will be popped out until HomeFragments. Check popUpTo and
            popUpToInclusive attributes of action in navigation graph. */
            val action =
                SendMoneyToRecipientFragmentDirections.actionDestSendMoneyToRecipientFragmentToDestHomeFragment()
            navController.navigate(action)
        }

        btnCancel.setOnClickListener {
            /* Navigating to existing HomeFragment using destination. This performs the same
            operation as btnFinish but programmatically. */
            navController.popBackStack(R.id.destHomeFragment, true)
        }
    }
}