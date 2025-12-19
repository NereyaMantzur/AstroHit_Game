package com.dev.nereya.ui_game_project

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dev.nereya.ui_game_project.interfaces.Callback_HighScoreClicked
import com.dev.nereya.ui_game_project.ui.HighScoreFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// 1. Added OnMapReadyCallback interface
class ScoreActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var main_FRAME_list: FrameLayout
    private lateinit var main_FRAME_map: FrameLayout

    private lateinit var highScoreFragment: HighScoreFragment

    // 2. Changed to nullable because the map takes time to load
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        // Ensure R.id.main matches your XML root ID
        val rootView = findViewById<android.view.View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViews()
        initViews()
    }

    private fun findViews() {
        main_FRAME_list = findViewById(R.id.main_FRAME_list)
        main_FRAME_map = findViewById(R.id.main_FRAME_map)
    }

    private fun initViews() {
        // 3. Initialize the official Google SupportMapFragment
        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_FRAME_map, mapFragment)
            .commit()

        // Trigger the map loading process
        mapFragment.getMapAsync(this)

        highScoreFragment = HighScoreFragment()

        // 4. Interface callback to move the map camera
        HighScoreFragment.highScoreItemClicked = object : Callback_HighScoreClicked {
            override fun highScoreItemClicked(lat: Double, lon: Double) {
                zoomOnMap(lat, lon)
            }
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_FRAME_list, highScoreFragment)
            .commit()
    }

    override fun onMapReady(m: GoogleMap) {
        this.googleMap = m
        val defaultLoc = LatLng(31.50, 35.0)
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLoc, 6.5f))
    }

    private fun zoomOnMap(lat: Double, lon: Double) {
        val location = LatLng(lat, lon)
        googleMap?.addMarker(MarkerOptions().position(location).title("Score Location"))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }
}