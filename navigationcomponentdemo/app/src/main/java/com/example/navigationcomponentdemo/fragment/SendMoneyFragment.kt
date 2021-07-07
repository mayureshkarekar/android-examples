package com.example.navigationcomponentdemo.fragment

import android.app.PendingIntent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentdemo.NOTIFICATION_CHANNEL_ID
import com.example.navigationcomponentdemo.R
import kotlinx.android.synthetic.main.fragment_send_money.*

class SendMoneyFragment : Fragment(R.layout.fragment_send_money) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        btnNext.setOnClickListener {
            val recipientName = etRecipient.text.toString()

            if (recipientName.isEmpty()) {
                Toast.makeText(context, R.string.enter_recipient_name, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            // Creating deeplink pending intent using NavigationController.
            val pendingIntent = navController.createDeepLink()
                .setGraph(R.navigation.nav_main)
                .setDestination(R.id.destSendMoneyToRecipientFragment)
                .setArguments(SendMoneyToRecipientFragmentArgs(recipientName).toBundle())
                .createPendingIntent()

            showNotification(recipientName, pendingIntent)


            /* // Preparing the arguments and animation for navigation  using destination id.
            val args = Bundle().apply { putString("recipientName", recipientName) }
            val navOptions = navOptions {
                anim {
                    enter = R.anim.slide_in_right
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left
                    popExit = R.anim.slide_out_right
                }
            }

            // Passing arguments and setting navigation animation.
            navController.navigate(R.id.destSendMoneyToRecipientFragment, args, navOptions) */

            // Passing arguments using directions. (Recommended way as it is type safe)
            val action =
                SendMoneyFragmentDirections.actionDestSendMoneyFragmentToSendMoneyToRecipientFragment(
                    recipientName
                )
            navController.navigate(action)
        }

        btnCancel.setOnClickListener {
            // Navigating back to previous screen.
            navController.popBackStack()
        }
    }

    private fun showNotification(recipientName: String, pendingIntent: PendingIntent) {
        val notification = NotificationCompat.Builder(requireContext(), NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification_24)
            .setContentTitle("New Transaction")
            .setContentText("Sending money to $recipientName")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(requireContext()).notify(1001, notification)
    }
}