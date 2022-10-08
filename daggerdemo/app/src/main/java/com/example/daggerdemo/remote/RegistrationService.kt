package com.example.daggerdemo.remote

import com.example.daggerdemo.di.MessageServiceQualifier
import com.example.daggerdemo.repository.NotificationService
import com.example.daggerdemo.repository.UserRepository
import javax.inject.Inject

class RegistrationService @Inject constructor(
    private val userRepository: UserRepository,
    @MessageServiceQualifier private val notificationService: NotificationService
) {
    fun registerUser(email: String, password: String) {
        userRepository.saveUser(email, password)
        notificationService.send(email, "Registration successful.")
    }
}