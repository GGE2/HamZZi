package com.team.teamrestructuring.view.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.getSystemService
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.FragmentHomeBinding
import com.team.teamrestructuring.dto.User
import com.team.teamrestructuring.service.LoginService
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.util.CreateFriendDialog
import com.team.teamrestructuring.util.CreatePetDialog
import com.team.teamrestructuring.util.CreatePetStatDialog
import com.team.teamrestructuring.view.activities.GuildActivity
import com.team.teamrestructuring.view.activities.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "HomeFragment_지"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 *훈
 */
class HomeFragment : Fragment(),CreateFriendDialog.CreateFriendDialogInterface,CreatePetStatDialog.CreatePetDialogInterface{
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
    private lateinit var createFriendDialog: CreateFriendDialog
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onClick() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonHomeFind.setOnClickListener {
            val dialog = CreateFriendDialog(this@HomeFragment)
            dialog.isCancelable = false
            dialog.show(activity?.supportFragmentManager!!,"CreateFriendDialog")
        }

        binding.buttonHomeStat.setOnClickListener {
            val dialog = CreatePetStatDialog(this@HomeFragment)
            dialog.isCancelable = false
            dialog.show(activity?.supportFragmentManager!!,"CreatePetStatDialog")
        }
        init()
    }


    private fun init(){
        createIntent()
        getCurrentUserInfo()
    }

    /**
     * 현재 접속한 유저의 정보를 저장
     */

    private fun getCurrentUserInfo(){
        val service = ApplicationClass.retrofit.create(LoginService::class.java)
            .getUserInfo(ApplicationClass.currentUser.uid).enqueue(object:Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful){
                        val data = response.body()!!
                        ApplicationClass.currentUser = data
                        setPetStat()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }
    
    private fun setPetStat(){
        binding.textviewHomeName.text = ApplicationClass.currentUser.userProfile.user_nickname
    }

    /**
     * Intent 생성 모음
     */
    private fun createIntent(){

        /**
         * Guild 버튼 클릭시 GuildActivity로 이동
         */
        binding.buttonHomeGuild.setOnClickListener {
            val intent = Intent(activity,GuildActivity::class.java)
            startActivity(intent)
        }
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onYesButtonClick() {

    }


}