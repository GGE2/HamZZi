package com.team.teamrestructuring.view.fragments

import android.app.AlertDialog
import android.app.Application
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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.FragmentHomeBinding
import com.team.teamrestructuring.dto.PetData
import com.team.teamrestructuring.dto.User
import com.team.teamrestructuring.service.LoginService
import com.team.teamrestructuring.service.PetService
import com.team.teamrestructuring.util.*
import com.team.teamrestructuring.view.activities.GuildActivity
import com.team.teamrestructuring.view.activities.HomeActivity
import com.team.teamrestructuring.view.viewmodels.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "HomeFragment_지훈"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 *
 */
class HomeFragment : Fragment(),CreateFriendDialog.CreateFriendDialogInterface,CreatePetStatDialog.CreatePetDialogInterface,CreatePetStatUpdateDialog.CreateStatUpdateInterface{

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

    private lateinit var createFriendDialog: CreateFriendDialog
    private lateinit var binding : FragmentHomeBinding
    private lateinit var petName : String
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


        /*binding.buttonHomeFind.setOnClickListener {
            val dialog = CreateFriendDialog(this@HomeFragment)
            dialog.isCancelable = false
            dialog.show(activity?.supportFragmentManager!!,"CreateFriendDialog")
        }*/

        binding.buttonHomeStat.setOnClickListener {
            val dialog = CreatePetStatDialog(this@HomeFragment)
            dialog.isCancelable = false
            dialog.show(activity?.supportFragmentManager!!,"CreatePetStatDialog")
        }
        binding.buttonHomeChangeStat.setOnClickListener {
            val dialog = CreatePetStatUpdateDialog(this@HomeFragment)
            dialog.isCancelable = false
            dialog.show(activity?.supportFragmentManager!!,"CreatePetStatUpdateDialog")
        }
        init()


    }

    private fun setViewModel(){
        mainViewModel.userData.observe(viewLifecycleOwner, Observer {
            setPetStat()
        })
        mainViewModel.petData.observe(viewLifecycleOwner, {
            Log.d(TAG, "setViewModel: ${it}")
        })
    }


    private fun init(){
        createIntent()
        getCurrentUserInfo()
        setViewModel()
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
                        mainViewModel.updateUser(data)
                        Log.d(TAG, "onResponse: ${ApplicationClass.currentUser}")
                        Log.d(TAG, "viewModel res: ${ApplicationClass.currentUser}")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }
            })
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
        getPetInfo()
        setPetStat()
    }


    private fun getPetInfo(){
        val service = ApplicationClass.retrofit.create(PetService::class.java)
            .getPetInfo(ApplicationClass.currentUser.userProfile.nickname).enqueue(object:Callback<PetData>{
                override fun onResponse(call: Call<PetData>, response: Response<PetData>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()!!}")
                        ApplicationClass.petData = response.body()!!
                    }
                }
                override fun onFailure(call: Call<PetData>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })


    }
    
     fun setPetStat(){
        binding.textviewHomeName.text = ApplicationClass.petData?.pet?.pet_name
        binding.buttonHomeLevel.text = ApplicationClass.petData?.pet?.level.toString()
        binding.progressbarMainStat.progress = exp
        when(ApplicationClass.petData?.pet?.level){
            1->{
                binding.progressbarMainStat.max = 14
                Glide.with(this).load(R.raw.pet_lv1).into(binding.imageviewMainPet)
                binding.textviewMainStat.text = "${ApplicationClass.petData?.pet?.exp}/14"
            }
            2->{
                binding.progressbarMainStat.max = 30
                Glide.with(this).load(R.raw.pet_lv2).into(binding.imageviewMainPet)
                binding.textviewMainStat.text = "${ApplicationClass.petData?.pet?.exp}/30"
            }
            3->{
                binding.progressbarMainStat.max = 60
                Glide.with(this).load(R.raw.pet_lv3).into(binding.imageviewMainPet)
                binding.textviewMainStat.text = "${ApplicationClass.petData?.pet?.exp}/60"
            }
            4->{
                binding.progressbarMainStat.max = 66
                Glide.with(this).load(R.raw.pet_lv42).into(binding.imageviewMainPet)
                binding.textviewMainStat.text = "${ApplicationClass.petData?.pet?.exp}/66"
            }
            5->{
                binding.progressbarMainStat.max = 0
                Glide.with(this).load(R.raw.ham5200).into(binding.imageviewMainPet)
                binding.textviewMainStat.text = "${ApplicationClass.petData?.pet?.exp}/66"
            }
        }

    }

    /**
     * Intent 생성 모음
     */
    private fun createIntent(){

        /**
         * Guild 버튼 클릭시 GuildActivity로 이동
         */
        /*binding.buttonHomeGuild.setOnClickListener {
            val intent = Intent(activity,GuildActivity::class.java)
            startActivity(intent)
        }*/

    }



    companion object {
        var exp = 0
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