package com.example.navigationcomponentdemo.activity

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.navigationcomponentdemo.NavMainDirections
import com.example.navigationcomponentdemo.R
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tb_main)


        /* If we want the reference of NavController in an activity we cannot directly call
        findNavController() as activities are not embedded in any navigation graph. Following way is
        used to get a reference of NavController in an activity. */
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcm_main) as NavHostFragment
        navController = navHostFragment.navController


        /* AppbarConfiguration for NavigationUI to correctly display appbar up arrow and hamburger
        icon to top-level destinations. */
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.destHomeFragment,
                R.id.destNotificationsFragment,
                R.id.destSettingsFragment,
                R.id.destLoginFragment,
                R.id.destProfileFragment
            ),
            dl_main
        )


        /* Setting up actionbar/toolbar for navigation controller (Recommended way). By calling this
        method, the title in the actionbar/toolbar will automatically be updated according to its
        label defined in navigation graph when the destination changes.
        NOTE: If you use toolbar, use Toolbar.setupWithNavController() extension method. */
        setupActionBarWithNavController(navController, appBarConfiguration)

        /* Setting toolbar for NavController. Using this single method titles and
        up buttons are automatically handled. */
        // tb_main.setupWithNavController(navController)


        /* Setting bottom sheet with NavController. */
        bnv_main.setupWithNavController(navController)


        /* Setting navigation drawer with NavController. */
        nv_main.setupWithNavController(navController)


        // Adding destination changed listener.
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val destinationName: String = try {
                resources.getResourceName(destination.id).let {
                    it.substring(it.lastIndexOf("/") + 1)
                }
            } catch (e: Resources.NotFoundException) {
                destination.id.toString()
            }

            Toast.makeText(
                this,
                "Navigated to $destinationName",
                Toast.LENGTH_SHORT
            ).show()

            Log.d(
                TAG,
                "Controller: $controller, Destination: $destination, Arguments: $arguments"
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handling up options item click on actionbar/toolbar.

        return when (item.itemId) {
            R.id.menu_about -> {
                // Navigate using global action.
                val action = NavMainDirections.actionGlobalDestAboutFragment()
                navController.navigate(action)

                return true
            }
            else -> item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }
    }

    // Handling up arrow click on actionbar/toolbar.
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}