package com.team.teamrestructuring.view.fragments

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.FragmentMyPageBinding
import com.team.teamrestructuring.dto.PetInfo
import com.team.teamrestructuring.service.MyPageService
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.util.CreateRegisterTimeDialog
import com.team.teamrestructuring.view.activities.RegisterPlaceActivity
import com.team.teamrestructuring.view.viewmodels.HomeViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

private const val TAG = "MyPageFragment_지훈"
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPageFragment : Fragment() {

    private val mainViewModel by activityViewModels<HomeViewModel>()

    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyPageBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    private fun init(){
        setListener()
        setUserData()
        setLevelIcon()
        getgraduate()

        mainViewModel.userData.observe(viewLifecycleOwner,{
            setUserData()
        })
    }

    private fun setLevelIcon(){
        when(mainViewModel.petData?.value?.pet?.level!!){
            1-> binding.imageviewMyPageIconLevel.setImageResource(R.drawable.lv_logo_1)
            2->binding.imageviewMyPageIconLevel.setImageResource(R.drawable.lv_logo_2)
            3->binding.imageviewMyPageIconLevel.setImageResource(R.drawable.lv_logo_3)
            else->{
                binding.imageviewMyPageIconLevel.setImageResource(R.drawable.lv_logo_4)
            }
        }
    }
    private fun getgraduate() {
        val nickName = mainViewModel.userData.value!!.userProfile.nickname
        val service = ApplicationClass.retrofit.create(MyPageService::class.java)
        service.getTrophy(nickName).enqueue(object : Callback<List<PetInfo>> {
            override fun onResponse(
                call: Call<List<PetInfo>>,
                response: Response<List<PetInfo>>
            ) {
                if (response.isSuccessful) {
                    val petList = response.body()
                    if (!petList.isNullOrEmpty()) {
                        for (petInfo in petList) {
                            val type = petInfo.type
                            binding.apply {
                                val left = binding.graduatePet01
                                val right = binding.graduatePet02
                                if (type == 1) {
                                    left.setImageResource(R.drawable.ham5100)
                                } else if (type == 2) {
                                    right.setImageResource(R.drawable.ham5200)
                                }
                            }
                        }
                    } else {
                        Log.d(TAG, "No graduated pets found")
                    }
                } else {
                    Log.d(TAG, "Error retrieving graduated pets: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<PetInfo>>, t: Throwable) {
                Log.d(TAG, "Error retrieving graduated pets: ${t.message}")
            }
        })
    }


    private fun setUserData(){
        binding.textviewMyPageContentLevel.text = mainViewModel.userData.value!!.userProfile.nickname
        binding.textviewMyPageContentCoin.text = mainViewModel.userData.value!!.userProfile.point.toString()
        if(mainViewModel.userData.value!!.userProfile.latitude!=0.0) {
            Log.d(TAG, "setUserData: ")
            binding.textviewMyPageContentPlace.text =
                mainViewModel.userData.value!!.userProfile.location
        }
    }

    override fun onResume() {
        super.onResume()
        if(mainViewModel.userData.value!!.userProfile.location!=null) {
            var location = binding.textviewMyPageContentPlace.text
            if(location.length>12){
                location = location.substring(0,12)+"..."
                binding.textviewMyPageContentPlace.text = location
            }
        }
    }

    private fun setListener(){
        binding.buttonMypageRegitserPlace.setOnClickListener{
            val intent = Intent(requireActivity(),RegisterPlaceActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.horizon_exit,R.anim.none)
        }
        binding.buttonMypageRegitserTime.setOnClickListener{
            val dialog = CreateRegisterTimeDialog()
            dialog.isCancelable
            dialog.show(activity?.supportFragmentManager!!,"CreateRegisterTime")
        }
    }

    companion object {
        lateinit var binding : FragmentMyPageBinding
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

