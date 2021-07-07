package com.example.navigationcomponentdemo.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentdemo.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        btnSendMoney.setOnClickListener {
            /* Navigate using destination id.
            (NOTE: This method does not support animations defined in navigation graph.
            Requires navOptions in parameters if you want navigation animation.) */
            // navController.navigate(R.id.destSendMoneyFragment)

            // Navigate using action id.
            // navController.navigate(R.id.action_destHomeFragment_to_destSendMoneyFragment)

            // Navigate using directions. (Recommended way)
            val action = HomeFragmentDirections.actionDestHomeFragmentToDestSendMoneyFragment()
            navController.navigate(action)
        }
        btnCheckBalance.setOnClickListener {
            // Navigate using destination id.
            // navController.navigate(R.id.destBalanceFragment)

            // Navigate using action id.
            // navController.navigate(R.id.action_destHomeFragment_to_destBalanceFragment)

            // Navigate using directions. (Recommended way)
            val action = HomeFragmentDirections.actionDestHomeFragmentToDestBalanceFragment()
            navController.navigate(action)
        }
        btnCheckTransactions.setOnClickListener {
            // Navigate using destination id.
            // navController.navigate(R.id.destTransactionsFragment)

            // Navigate using action id.
            // navController.navigate(R.id.action_destHomeFragment_to_destTransactionsFragment)

            // Navigate using directions. (Recommended way)
            val action = HomeFragmentDirections.actionDestHomeFragmentToDestTransactionsFragment()
            navController.navigate(action)
        }
    }
}