package com.example.mapview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*


class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    companion object {
        private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    }

    lateinit var googleMap: GoogleMap
    lateinit var mapView: MapView
    lateinit var viewPager: ViewPager2
    private var mapViewBundle: Bundle? = null
    lateinit var schoolList: ArrayList<School>
    private var allList = mutableListOf<School>()
    private var markers = arrayListOf<Marker>()
    private var previousMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.mapView)
        viewPager = view.findViewById(R.id.viewPager)
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)

        schoolList = arrayListOf(
            School("國立台灣大學", "1", 25.017625419678666, 121.53968644108197),
            School("國立台灣科技大學", "2", 25.013530515868982, 121.53983133700123),
            School("國立師範大學", "3", 25.025197122610876, 121.52870181814113),
            School("世新大學", "4", 24.988770953719953, 121.54383379412823),
            School("中國科技大學", "5", 24.99863550635995, 121.55502229782816)
        )

        val mapAdapter = MapAdapter()
        mapAdapter.list = schoolList

        viewPager.apply {
            setPageTransformer(MarginPageTransformer(8.dp))
            offscreenPageLimit = 1
            val recyclerView = getChildAt(0) as RecyclerView
            recyclerView.apply {
                setPadding(24.dp, 0, 24.dp, 0)
                clipToPadding = false
                clipChildren = false
                overScrollMode = View.OVER_SCROLL_NEVER
            }
            adapter = mapAdapter
        }

        //viewPager移動或標記點選時，找出點選位置並將標記替換
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                allList.elementAtOrNull(position)?.let { school ->
                    val cameraPosition = CameraPosition.Builder()
                        .target(LatLng(school.lat, school.lng))
                        .zoom(13f)
                        .build()
                    val update = CameraUpdateFactory.newCameraPosition(cameraPosition)
                    previousMarker?.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_blue))
                    markers.find { it.snippet == school.no }?.let {
                        it.setIcon(getBitmapFromVectorDrawable(R.drawable.marker_red, school.no))
                        previousMarker = it
                    }
                    googleMap.animateCamera(update)
                }
            }
        })


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.apply {
            uiSettings.isMapToolbarEnabled = false
            uiSettings.isZoomControlsEnabled = false
            setOnMarkerClickListener(this@MapFragment)
        }

        googleMap.clear()
        markers.clear()
        allList.clear()
        previousMarker = null
        allList.addAll(schoolList)

        schoolList.forEach { school ->
            val LatLng = LatLng(school.lat, school.lng)
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .position(LatLng)
                    .snippet(school.no))

            if (school.no == "1") {
                //客製化標記
                marker?.setIcon(getBitmapFromVectorDrawable(R.drawable.marker_red, school.no))
            } else {
                //只有標記圖
                marker?.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_blue))
            }
            markers.add(marker!!)
        }

        val highLightSchool = schoolList.first()
        if (schoolList.isNotEmpty()) {
            //鏡頭(畫面)所指向位置
            val cameraPosition = CameraPosition.Builder()
                .target(LatLng(highLightSchool.lat, highLightSchool.lng))
                //畫面遠近
                .zoom(13f)
                .build()
            val update = CameraUpdateFactory.newCameraPosition(cameraPosition)
            //鏡頭(畫面)移向當前位置
            googleMap.moveCamera(update)
        }

        (viewPager.adapter as MapAdapter).list = schoolList
        viewPager.adapter?.notifyDataSetChanged()
        //設置初始化 當前所指向的位置
        viewPager.currentItem = allList.indexOf(highLightSchool)

    }
    //點選標記
    override fun onMarkerClick(marker: Marker): Boolean {
        marker.snippet ?: return true
        if (marker.snippet != previousMarker?.snippet) {
            marker.setIcon(getBitmapFromVectorDrawable(R.drawable.marker_red, marker.snippet ?: ""))
            previousMarker?.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_blue))
            previousMarker = marker
            viewPager.currentItem = markers.indexOf(marker)
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
            ?: Bundle().apply { putBundle(MAPVIEW_BUNDLE_KEY, this) }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }


    private fun getBitmapFromVectorDrawable(@DrawableRes drawable: Int, text: String): BitmapDescriptor {
        val vectorDrawable = AppCompatResources.getDrawable(requireContext(), drawable)

        if (vectorDrawable == null) {
            //Timber.e("Requested vector resource was not found")
            return BitmapDescriptorFactory.defaultMarker()
        }
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.textSize = 35f
        paint.color = Color.WHITE
        paint.textAlign = Paint.Align.CENTER
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        canvas.drawText(text, (bitmap.width / 2).toFloat(), (bitmap.height / 2).toFloat(), paint)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}