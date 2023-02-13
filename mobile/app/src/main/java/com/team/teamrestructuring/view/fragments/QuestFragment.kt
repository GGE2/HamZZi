package com.team.teamrestructuring.view.fragments

import android.graphics.Color
import android.graphics.Paint
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.replace
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.UiSettings
import com.naver.maps.map.util.FusedLocationSource
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.FragmentQuestBinding
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.view.adapters.DailyQuestAdapter
import java.lang.Math.*
import java.util.jar.Manifest
import kotlin.math.pow

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "QuestFragment_지훈"


/**
 * A simple [Fragment] subclass.
 * Use the [GuildFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuestFragment : Fragment(),OnMapReadyCallback{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var locationSource:FusedLocationSource
    private lateinit var binding:FragmentQuestBinding
    private lateinit var naverMap: NaverMap
    private lateinit var mapView:MapView
    private lateinit var tabLayout : TabLayout
    private lateinit var questAdapter: DailyQuestAdapter



    companion object {

        var distance : Int = 0

        private val PERMISSIONS:Array<String> =
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION)
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        private const val RR = 6372.8 * 1000

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GuildFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults
            )){
            if(!locationSource.isActivated){ //권한이 거부 됬을 경우
                Log.d(TAG, "onRequestPermissionsResult: false")
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }else{
                Log.d(TAG, "onRequestPermissionsResult: true")
                naverMap.locationTrackingMode = LocationTrackingMode.Follow
            }
            return
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentQuestBinding.inflate(inflater,container,false)

        tabLayout = binding.tablayoutQuestTap
        setTabLayout() //Tab layout 설정

        mapView = binding.naverMap
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        locationSource = FusedLocationSource(this,LOCATION_PERMISSION_REQUEST_CODE)
        return binding.root
    }
    private fun setTabLayout(){

        tabLayout.setTabTextColors(Color.parseColor("#000000"),Color.parseColor("#FFFFFF"))
        tabLayout.addTab(tabLayout.newTab().setText("DAILY"))
        tabLayout.addTab(tabLayout.newTab().setText("WEEKLY"))

        childFragmentManager.beginTransaction().replace(R.id.fragment_quest_container,DailyFragment()).commit()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0-> childFragmentManager.beginTransaction().replace(R.id.fragment_quest_container,DailyFragment()).commit()
                    1-> childFragmentManager.beginTransaction().replace(R.id.fragment_quest_container,WeeklyFragment()).commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d(TAG, "onTabUnselected: ")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d(TAG, "onTabReselected: ")
                when(tab!!.position){
                    0-> childFragmentManager.beginTransaction().replace(R.id.fragment_quest_container,DailyFragment()).commit()
                    1-> childFragmentManager.beginTransaction().replace(R.id.fragment_quest_container,WeeklyFragment()).commit()
                }

            }

        })
        setTabItemMargin(tabLayout,10)
    }

    // TabLayout Tab 사이 간격 부여
    private fun setTabItemMargin(tabLayout: TabLayout, marginEnd: Int = 20) {
        for(i in 0 until 2) {
            val tabs = tabLayout.getChildAt(0) as ViewGroup
            for(i in 0 until tabs.childCount) {
                val tab = tabs.getChildAt(i)
                val lp = tab.layoutParams as LinearLayout.LayoutParams
                lp.marginEnd = marginEnd
                tab.layoutParams = lp
                tabLayout.requestLayout()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onMapReady(naverMap: NaverMap) {
        Log.d(TAG, "onMapReady: ")
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        requestPermissions(PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)
        //val uiSettings = naverMap.uiSettings
        naverMap.addOnLocationChangeListener {location->
            val cameraUpdate = CameraUpdate.scrollTo(LatLng(location.latitude,location.longitude))
            naverMap.moveCamera(cameraUpdate)
            //Log.d(TAG, "onMapReady: ${location.longitude},$${location.latitude}")
            distance = getDistance(ApplicationClass.currentUser.userProfile.latitude
                ,ApplicationClass.currentUser.userProfile.longitude,location.latitude,location.longitude)



        }

    }
    fun getDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Int {
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2.0) + sin(dLon / 2).pow(2.0) * cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2))
        val c = 2 * asin(sqrt(a))
        return (RR * c).toInt()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}