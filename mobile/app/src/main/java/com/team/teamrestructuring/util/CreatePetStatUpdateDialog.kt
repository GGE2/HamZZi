package com.team.teamrestructuring.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import com.team.teamrestructuring.databinding.DialogCreatePetBinding
import com.team.teamrestructuring.databinding.DialogCreateUpdateStatBinding
import com.team.teamrestructuring.dto.CreatePet
import com.team.teamrestructuring.dto.PetData
import com.team.teamrestructuring.dto.UpdatePetStat
import com.team.teamrestructuring.service.HomeService
import com.team.teamrestructuring.service.PetService
import com.team.teamrestructuring.view.activities.HomeActivity
import com.team.teamrestructuring.view.fragments.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG="CreatePetUpdateDialog_지훈"
class CreatePetStatUpdateDialog(
    createStatUpdateInterface: CreateStatUpdateInterface
) : DialogFragment(){

    private var _binding: DialogCreateUpdateStatBinding? = null
    private val binding get() = _binding!!
    private var createStatUpdateInterface : CreateStatUpdateInterface? = null
    private var count:Int = 0
    private var initCount  = 0

    init{
        this.createStatUpdateInterface= createStatUpdateInterface
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@CreatePetStatUpdateDialog,0.8f,0.45f)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCreateUpdateStatBinding.inflate(inflater,container,false)
        val view = binding.root
        count = ApplicationClass.currentUser.userProfile.point
         initCount = ApplicationClass.currentUser.userProfile.point
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setFullScreen()

        binding.textviewStatCount.text = "잔여 스탯 : ${count}"
        binding.buttonAddArtistic.setOnClickListener {
            if(count>0){
            count--
            binding.textviewStatCount.text = "잔여 스탯 : ${count}"
            val data = UpdatePetStat(1,0,0,0,0,
            ApplicationClass.petData?.pet?.pet_id!!,0)
            updateStat(data)
            }
        }
        binding.buttonAddEnergetic.setOnClickListener {
            if(count>0) {
                count--
                binding.textviewStatCount.text = "잔여 스탯 : ${count}"
                val data = UpdatePetStat(
                    0, 1, 0, 0, 0,
                    ApplicationClass.petData?.pet?.pet_id!!, 0
                )
                updateStat(data)
            }
        }
        binding.buttonAddInactive.setOnClickListener {
            if(count>0) {
                count--
                binding.textviewStatCount.text = "잔여 스탯 : ${count}"
                val data = UpdatePetStat(
                    0, 0, 0, 1, 0,
                    ApplicationClass.petData?.pet?.pet_id!!, 0
                )
                updateStat(data)
            }
        }
        binding.buttonAddIntelligent.setOnClickListener {
            if(count>0){
            count--
            binding.textviewStatCount.text = "잔여 스탯 : ${count}"
            val data = UpdatePetStat(0,0,0,0,1,
                ApplicationClass.petData?.pet?.pet_id!!,0)
            updateStat(data)
            }
        }
        binding.buttonAddPhysical.setOnClickListener {
            if(count>0){
            count--
            binding.textviewStatCount.text = "잔여 스탯 : ${count}"
            val data = UpdatePetStat(0,0,0,0,0,
                ApplicationClass.petData?.pet?.pet_id!!,1)
            updateStat(data)
            }
        }
        binding.buttonAddEtc.setOnClickListener {
            if(count>0){
                count--
                binding.textviewStatCount.text = "잔여 스탯 : ${count}"
                val data = UpdatePetStat(0,0,1,0,0,
                    ApplicationClass.petData?.pet?.pet_id!!,1)
                updateStat(data)
            }
        }

        binding.buttonStatCancle.setOnClickListener {
            dismiss()
        }
        binding.buttonStatOk.setOnClickListener {
            createStatUpdateInterface!!.onYesButtonClick()
            ApplicationClass.currentUser.userProfile.point = count
            updateExp()
            HomeActivity.viewPagerAdapter.refreshFragment(0,HomeFragment())
            dismiss()
        }
        /*binding.buttonPetCreate.setOnClickListener {
            val pet = CreatePet(binding.edittextDialogFind.text.toString(),ApplicationClass.currentUser.userProfile.nickname)
            sendToServerPetNickname(pet)
            createPetDialogInterface?.onYesButtonClick()
        }*/

        return view
    }


    private fun updateStat(data : UpdatePetStat){
        val service = ApplicationClass.retrofit.create(PetService::class.java)
            .updatePetStat(ApplicationClass.currentUser.userProfile.nickname,data).enqueue(object:Callback<String>{
                @SuppressLint("LongLogTag")
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()!!}")
                    }
                }

                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }

    /**
     * 펫 경험치 증가
     */
    private fun updateExp(){
        val service = ApplicationClass.retrofit.create(PetService::class.java)
            .petUpdatePetExp(ApplicationClass.petData!!.pet.pet_id,(initCount-count)*3,ApplicationClass.currentUser.userProfile.nickname)
            .enqueue(object:Callback<String>{
                @SuppressLint("LongLogTag")
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()!!}")
                    }
                }

                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    interface CreateStatUpdateInterface{
        fun onYesButtonClick()
    }

    /**
     * Dialog 생성시 하단 네비게이션 뷰 및 상태바 생성x
     */
    fun setFullScreen(){
        //dialog?.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        //dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        dialog?.window?.decorView?.systemUiVisibility = flag

        //dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)

    }
    fun Context.dialogFragmentResize(dialogFragment: DialogFragment, width:Float, height:Float){
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if(Build.VERSION.SDK_INT<30){
            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialogFragment.dialog?.window

            val x = (size.x*width).toInt()
            val y = (size.y*height).toInt()
            window?.setLayout(x,y)
        }else{
            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialogFragment.dialog?.window

            val x = (rect.width() * width).toInt()
            val y = (rect.height() * height).toInt()

            window?.setLayout(x,y)

        }
    }





}