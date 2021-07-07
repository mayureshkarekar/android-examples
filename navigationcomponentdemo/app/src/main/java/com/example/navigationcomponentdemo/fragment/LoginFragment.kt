package com.example.navigationcomponentdemo.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentdemo.R
import com.example.navigationcomponentdemo.utils.DataHolder
import com.example.navigationcomponentdemo.utils.User
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {
    companion object {
        const val DID_LOGIN = "DID_LOGIN"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        // Saving data to previous backstack entry (Profile).
        val profileSavedStateHandle = navController.previousBackStackEntry!!.savedStateHandle
        profileSavedStateHandle.set(
            DID_LOGIN,
            false
        ) // Indicating user cancelled operation by pressing back button.

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, R.string.enter_username_password, Toast.LENGTH_SHORT).show()
            } else {
                DataHolder.user = User(username)

                // Saving data to previous backstack entry (Profile) to indicate user performed successful login.
                profileSavedStateHandle.set(DID_LOGIN, true)

                // Navigating back to profile fragment.
                navController.popBackStack()

                Toast.makeText(context, R.string.login_successful, Toast.LENGTH_SHORT).show()
            }
        }
    }
}