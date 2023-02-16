package com.team.teamrestructuring.view.fragments

import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.FragmentHomeBinding
import com.team.teamrestructuring.dto.User
import com.team.teamrestructuring.service.LoginService
import com.team.teamrestructuring.service.PetService
import com.team.teamrestructuring.util.*
import com.team.teamrestructuring.view.activities.HomeActivity
import com.team.teamrestructuring.view.viewmodels.HomeViewModel
import kotlinx.coroutines.*
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
            val dialog = CreatePetStatDialog(this@HomeFragment,mainViewModel.petData.value?.petStat!!)
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
            Log.d(TAG, "Observe UserData: ${it}")
            //setPetStat()
        })
        mainViewModel.petData.observe(viewLifecycleOwner, {
            Log.d(TAG, "Observe PetData: ${it}")
            setPetStat()
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
        //setPetStat()
    }


    private fun getPetInfo(){

        var job: Job? = null

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = ApplicationClass.retrofit.create(PetService::class.java).getPetInfo(ApplicationClass.currentUser.userProfile.nickname)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    mainViewModel.updatePet(response.body()!!)
                    Log.d(TAG, "getPetInfo: ${response.body()!!}")
                }
            }
        }


    }
    
     fun setPetStat(){

         Log.d(TAG, "setPetStat: ${mainViewModel.petData.value}")
         binding.textviewHomeName.text = mainViewModel.petData.value?.pet?.pet_name
         binding.buttonHomeLevel.text = mainViewModel.petData.value?.pet?.level!!.toString()
         binding.progressbarMainStat.progress = mainViewModel.petData.value?.pet?.exp!!
        when(mainViewModel.petData.value?.pet?.level!!){
            1->{
                binding.progressbarMainStat.max = 14
                Glide.with(this).load(R.raw.pet_lv1).into(binding.imageviewMainPet)
                binding.textviewMainStat.text = "${mainViewModel.petData.value?.pet?.exp!!}/14"
            }
            2->{
                binding.progressbarMainStat.max = 30
                Glide.with(this).load(R.raw.pet_lv2).into(binding.imageviewMainPet)
                binding.textviewMainStat.text = "${mainViewModel.petData.value?.pet?.exp!!}/30"
            }
            3->{
                binding.progressbarMainStat.max = 60
                Glide.with(this).load(R.raw.pet_lv3).into(binding.imageviewMainPet)
                binding.textviewMainStat.text = "${mainViewModel.petData.value?.pet?.exp!!}/60"
            }
            4->{
                binding.progressbarMainStat.max = 66
                Glide.with(this).load(R.raw.pet_lv42).into(binding.imageviewMainPet)
                binding.textviewMainStat.text = "${mainViewModel.petData.value?.pet?.exp!!}/66"
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

    override fun onYesButtonClick(emptyStat: Int) {
        mainViewModel.userData.value!!.userProfile.point = emptyStat
    }

    override fun onYesButtonClick() {

    }


}