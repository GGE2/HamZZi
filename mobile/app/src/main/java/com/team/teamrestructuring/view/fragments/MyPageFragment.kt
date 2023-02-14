package com.team.teamrestructuring.view.fragments

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.FragmentMyPageBinding
import com.team.teamrestructuring.service.MyPageService
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.util.CreateRegisterTimeDialog
import com.team.teamrestructuring.view.activities.RegisterPlaceActivity
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
    // TODO: Rename and change types of parameters
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
    }

    private fun setLevelIcon(){
        when(ApplicationClass.petData?.pet?.level!!){
            1-> binding.imageviewMyPageIconLevel.setImageResource(R.drawable.lv_logo_1)
            2->binding.imageviewMyPageIconLevel.setImageResource(R.drawable.lv_logo_2)
            3->binding.imageviewMyPageIconLevel.setImageResource(R.drawable.lv_logo_3)
            4->binding.imageviewMyPageIconLevel.setImageResource(R.drawable.lvlogo_4)
        }
    }
    private fun getgraduate(){
        val nickName = ApplicationClass.currentUser.userProfile.nickname.toString()
        val service = ApplicationClass.retrofit.create(MyPageService::class.java)
            .getTrophy(nickName).enqueue(object : Callback<MutableList<String>> {
                override fun onResponse(
                    call: Call<MutableList<String>>,
                    response: Response<MutableList<String>>
                ) {
                    if (response.isSuccessful){
                        val petlist = response.body()
                        Log.d("졸업", petlist.toString())
//                        for (pet in petlist){
//                            val type = pet["type"]
//                            binding.apply {
//                                val left = binding.graduatePet01
//                                val right = binding.graduatePet02
//                                if (type == 1){
//                                    left.setImageResource(R.drawable.ham5100)
//                                } else if (type == 2){
//                                    right.setImageResource(R.drawable.ham5200)
//                                }
//                            }
//                        }
                    }
                }

                override fun onFailure(call: Call<MutableList<String>>, t: Throwable) {
                    Log.d("졸업", t.toString())
                }
            })
    }

    private fun setUserData(){
        binding.textviewMyPageContentLevel.text = ApplicationClass.currentUser.userProfile.nickname
        binding.textviewMyPageContentCoin.text = ApplicationClass.currentUser.userProfile.point.toString()
        if(ApplicationClass.currentUser.userProfile.location!=null)
            binding.textviewMyPageContentPlace.text = ApplicationClass.currentUser.userProfile.location
    }

    override fun onResume() {
        super.onResume()
        if(ApplicationClass.currentUser.userProfile.location!=null) {
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
