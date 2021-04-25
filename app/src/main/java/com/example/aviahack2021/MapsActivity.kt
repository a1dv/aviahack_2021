package com.example.aviahack2021

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object {
        const val KEY = "key"
    }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    open fun count(str: String, target: String): Int {
        return (str.length - str.replace(target, "").length) / target.length
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val str = intent.getStringExtra(KEY)

        val latti = str?.substringBefore(",")!!.toDouble()
        val longi = str?.substringAfter(",")!!.toDouble()

        val clientlocation = LatLng(latti, longi)
        mMap.addMarker(MarkerOptions().position(clientlocation).title("Marker at Client's Location"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clientlocation, 20f))
        //val myToast = Toast.makeText(this, "${response}", Toast.LENGTH_LONG).show()
        val text:String
        val queue = Volley.newRequestQueue(this)
        val url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${latti},${longi}&radius=100&type=restaurant&fields=results&key=AIzaSyBF4bc2G81-2mBOPbu0xF8oIHyxtPXck7A"
        val stringReq = StringRequest(com.android.volley.Request.Method.GET, url, Response.Listener<String> { response ->

            var tmp = "ss"
            tmp = response
            var text = response


            while (tmp.isNotEmpty()) {

                tmp = tmp.substringAfter("\"name\" : \"")
                var ts: String = "We highly recommend you visit ${tmp.substringBefore("\"")}"
                val myToast = Toast.makeText(this, "${ts}", Toast.LENGTH_LONG).show()
                text = tmp.substringBefore("\"name\" : \"")
            }
        }, Response.ErrorListener {})
        queue.add(stringReq)

    }
}