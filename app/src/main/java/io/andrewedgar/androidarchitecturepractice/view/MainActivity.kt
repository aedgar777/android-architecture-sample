package io.andrewedgar.androidarchitecturepractice.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import io.andrewedgar.androidarchitecturepractice.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        navController = Navigation.findNavController(this, R.id.fragment)

        //Links action bar to navController
        NavigationUI.setupActionBarWithNavController(this, navController)


    }


    override fun onSupportNavigateUp(): Boolean {

        //Manages back button with reference to the back stack that is being managed by the navController
        return NavigationUI.navigateUp(navController,null)
    }
}
