package cat.nbacafe.girona.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cat.nbacafe.girona.NbaCafeApp
import cat.nbacafe.girona.R
import cat.nbacafe.girona.databinding.ActivityMainBinding
import cat.nbacafe.girona.shared.SharedViewModel
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    val sharedViewModel: SharedViewModel by viewModels()
    var lastPress = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.firstFragment, R.id.loginFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val headerView: View = navView.getHeaderView(0)
        val navUsername: TextView = headerView.findViewById(R.id.navHeaderText)

        sharedViewModel.loggedUser.observe(this, { loggedUser ->
            navUsername.setText(loggedUser)
        })

        navView.menu.findItem(R.id.firstFragment).setOnMenuItemClickListener {
            NbaCafeApp.preferences.saveName("")
            finish()
            startActivity(intent)
            overridePendingTransition(0, 0);
            true
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->

            if (sharedViewModel.getLoggedUser() == "") {

                navView.menu.findItem(R.id.firstFragment).setVisible(false)
                navView.menu.findItem(R.id.homeFragment).setVisible(false)
                navView.menu.findItem(R.id.sandwichesFragment).setVisible(false)
                navView.menu.findItem(R.id.historyFragment).setVisible(false)
                navView.menu.findItem(R.id.loginFragment).setVisible(true)
                navView.menu.findItem(R.id.registerFragment).setVisible(true)

            } else {

                navView.menu.findItem(R.id.firstFragment).setVisible(true)
                navView.menu.findItem(R.id.homeFragment).setVisible(true)
                navView.menu.findItem(R.id.sandwichesFragment).setVisible(true)
                navView.menu.findItem(R.id.historyFragment).setVisible(true)
                navView.menu.findItem(R.id.loginFragment).setVisible(false)
                navView.menu.findItem(R.id.registerFragment).setVisible(false)

            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastPress > 5000) {
            Toast.makeText(baseContext, "Torna a apretar per sortir", Toast.LENGTH_LONG).show()
            lastPress = currentTime
        } else {
            super.onBackPressed()
        }
    }

}