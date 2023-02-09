package com.team.teamrestructuring.util

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import com.team.teamrestructuring.databinding.DialogConfirmPlaceBinding
import com.team.teamrestructuring.databinding.DialogCreatePetBinding
import com.team.teamrestructuring.databinding.DialogCreateTimepickerBinding
import com.team.teamrestructuring.dto.DailyQuest
import com.team.teamrestructuring.dto.Place
import com.team.teamrestructuring.service.HomeService
import com.team.teamrestructuring.service.PlaceService
import com.team.teamrestructuring.service.QuestService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG="CreateRegisterTime_지훈"
class CreateRegisterTimeDialog(

) : DialogFragment(){

    private var _binding: DialogCreateTimepickerBinding? = null
    private val binding get() = _binding!!
    var registHour = 0
    var registerMinute = 0



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCreateTimepickerBinding.inflate(inflater,container,false)
        val view = binding.root

        setFullScreen()

        binding.buttonDialogCreateTimeCancle.setOnClickListener {
            dismiss()
        }
        binding.buttonDialogCreateTimeOk.setOnClickListener {
            setFinishTime(ApplicationClass.currentUser.userProfile.nickname,
            60*registHour+registerMinute)
            ApplicationClass.currentUser.userProfile.time = 60*registHour+registerMinute
            dismiss()
        }
        binding.timepickerDialogCreateTime.setOnTimeChangedListener { timePicker, hour, minute ->
            registHour = hour
            registerMinute = minute
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@CreateRegisterTimeDialog,1f,0.36f)
    }


    fun setFinishTime(nickname:String,time:Int){
        val service = ApplicationClass.retrofit.create(QuestService::class.java)
            .setFinishTime(nickname,time).enqueue(object : Callback<String>{
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