/*
------------------------------------------- Dagger -------------------------------------------
Inversion of Control: IoC is a design principle used to invert different kinds of controls in
                      object-oriented design to achieve loose coupling. Here, controls refer to any
                      additional responsibilities a class has, other than its main responsibility.
                      This include control over the flow of an application, and control over the
                      flow of an object creation or dependent object creation and binding.

Dependency Injection: Dependency Injection is a design pattern used to implement IoC. It allows the
                      creation of dependent objects outside of a class and provides those objects to
                      a class through different ways. Using DI, we move the creation and binding of
                      the dependent objects outside of the class that depends on them.

The Dependency Injection pattern involves 3 types of classes.
1. Client Class/Consumer    : The client class (dependent class) is a class which depends on the
                              service class. @Inject annotation is used in this types of class to
                              consume the dependency. This annotation is also used to provide
                              dependency.

2. Service Class/Provider  : The service class (dependency) is a class that provides service to the
                             client class. @Module, @Provides and @Binds annotations are used to
                             provide the dependency.

3. Injector Class/Connector: The injector class injects the service class object into the client
                             class. @Component annotation is used to define the connector.

Types of Dependency Injection.
1. Constructor Injection: In the constructor injection, the injector supplies the service
                          (dependency) through the client class constructor.

2. Property Injection   : In the property injection (aka the Setter Injection), the injector
                          supplies the dependency through a public property of the client class.

3. Method Injection     : In this type of injection, the client class implements an interface which
                          declares the method(s) to supply the dependency and the injector uses this
                          interface to supply the dependency to the client class.

*/

package com.example.daggerdemo.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerdemo.DaggerApplication
import com.example.daggerdemo.R
import com.example.daggerdemo.remote.RegistrationService
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var registrationService: RegistrationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            val appComponent = (application as DaggerApplication).appComponent
            // Using Factory Pattern.
            val registrationComponent = appComponent.getRegistrationComponentFactory().create(3)

            // Using Builder Pattern.
            //val registrationComponent = appComponent.getRegistrationComponentBuilder().retryCount(3).build()

            registrationComponent.inject(this)

            registrationService.registerUser(email, password)
            Toast.makeText(this, R.string.registration_successful, Toast.LENGTH_SHORT).show()
        }
    }
}