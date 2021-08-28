package com.example.filmsearcher.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.filmsearcher.R
import com.example.filmsearcher.manager.NetworkManager
import com.example.filmsearcher.model.Movie
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var movieSelected : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val nm = NetworkManager(this.applicationContext)
        if (!nm.isConnectedToInternet!!) {
            toolbar.setTitle(toolbar.title.toString() + " - OFFLINE")
        }
        setSupportActionBar(toolbar)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setSelectedItemId(R.id.searchFragment);
        bottomNavigationView.setItemIconTintList(null);

//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if(destination.id == R.id.loginFragment) {
//                bottomNavigationView.visibility = View.GONE
//            } else {
//                bottomNavigationView.visibility = View.VISIBLE
//            }
//        }
    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun switchContent(id: Int, fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        var rootView = this.findViewById<View>(android.R.id.content)
        ft.replace(rootView.id, fragment, fragment.toString())
        ft.addToBackStack(fragment.toString())
        ft.commit()

        (fragment as MovieDetailFragment).movie = movieSelected
    }
}