package com.example.navigationcomponentdemo.fragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.navigationcomponentdemo.R
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lvNotifications.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, getNotifications())
    }

    private fun getNotifications(): List<String> {
        val notifications = mutableListOf<String>()

        for (i in 1..50) {
            notifications.add("Notification $i")
        }

        return notifications
    }
}