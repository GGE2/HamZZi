package com.team.teamrestructuring.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import com.team.teamrestructuring.databinding.DialogConfirmPlaceBinding
import com.team.teamrestructuring.databinding.DialogCreatePetBinding
import com.team.teamrestructuring.dto.DailyQuest
import com.team.teamrestructuring.dto.Place
import com.team.teamrestructuring.service.HomeService
import com.team.teamrestructuring.service.PlaceService
import com.team.teamrestructuring.view.activities.HomeActivity
import com.team.teamrestructuring.view.fragments.HomeFragment
import com.team.teamrestructuring.view.fragments.MyPageFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG="CreateConfirmDialog_지훈"
class CreateConfirmDialog(
    createConfirmDialogInterface:CreateConfirmListener,
    place: Place
) : DialogFragment(){

    private var _binding: DialogConfirmPlaceBinding? = null
    private val binding get() = _binding!!
    private var createConfirmDialogInterface : CreateConfirmListener? = null
    private var place:Place? = null

    init{
        this.createConfirmDialogInterface= createConfirmDialogInterface
        this.place = place
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogConfirmPlaceBinding.inflate(inflater,container,false)
        val view = binding.root
        setFullScreen()

        binding.textviewDialogPlace.text = "장소명 : ${place?.place_name}"
        binding.textviewDialogAddress.text = "주소 : ${place?.address_name}"
        binding.buttonConfirmDialogOk.setOnClickListener {
            createConfirmDialogInterface?.onYesButtonClick()
            sendToServerLatData()
            HomeActivity.viewPagerAdapter.refreshFragment(3, MyPageFragment())
            dismiss()
        }
        binding.buttonConfirmDialogCancle.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    interface CreateConfirmListener{
        fun onYesButtonClick()
    }

    private fun sendToServerLatData(){
        ApplicationClass.currentUser.userProfile.latitude = place?.y?.toDouble()!!
        ApplicationClass.currentUser.userProfile.longitude = place?.x?.toDouble()!!
        ApplicationClass.currentUser.userProfile.location = place?.place_name!!
        val service = ApplicationClass.retrofit.create(PlaceService::class.java)
            .registerPlace(ApplicationClass.currentUser.userProfile.nickname,place?.y?.toDouble()!!,place?.x?.toDouble()!!,place?.place_name!!).enqueue(object:Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
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



}