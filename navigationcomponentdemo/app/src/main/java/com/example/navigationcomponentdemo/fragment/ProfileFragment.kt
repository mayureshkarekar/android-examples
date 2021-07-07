package com.example.navigationcomponentdemo.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentdemo.R
import com.example.navigationcomponentdemo.utils.DataHolder
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController()


        // Retrieving current backstack entry.
        val currentBackStackEntry = navController.currentBackStackEntry


        // Retrieving data from SavedStateHandle of current backstack entry.
        val currentSavedStateHandle = currentBackStackEntry!!.savedStateHandle
        currentSavedStateHandle.getLiveData<Boolean>(LoginFragment.DID_LOGIN)
            .observe(currentBackStackEntry) { didLogin ->
                if (!didLogin) {
                    // User cancelled login operation by pressing back button.
                    val startDestination = navController.graph.startDestination
                    val navOptions = NavOptions.Builder().setPopUpTo(startDestination, true).build()

                    // Navigating to Home and clearing intermediate backstack entries.
                    navController.navigate(startDestination, null, navOptions)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (DataHolder.user == null) {
            // User is not logged in.
            Toast.makeText(context, R.string.please_login_first, Toast.LENGTH_SHORT).show()

            navController.navigate(R.id.destLoginFragment)
        } else {
            // User is logged in.
            tvUsername.text = getString(R.string.welcome_user, DataHolder.user?.username)

            btnLogout.setOnClickListener {
                // Logging out the user.
                DataHolder.user = null

                // Navigating to home.
                navController.popBackStack()
            }
        }
    }
}