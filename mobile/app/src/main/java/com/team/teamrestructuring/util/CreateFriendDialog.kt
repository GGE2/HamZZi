package com.team.teamrestructuring.util

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.WindowManager.LayoutParams
import androidx.fragment.app.DialogFragment
import com.team.teamrestructuring.databinding.DialogCreateFindFriendBinding
import com.team.teamrestructuring.databinding.DialogCreatePetBinding
import com.team.teamrestructuring.service.HomeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG="ConfirmDialog_지훈"
class CreateFriendDialog(
    creatFriendDialogInterface: CreateFriendDialogInterface
) : DialogFragment(){

    private var _binding: DialogCreateFindFriendBinding? = null
    private val binding get() = _binding!!
    private var creatFriendDialogInterface : CreateFriendDialogInterface? = null

    init{
        this.creatFriendDialogInterface= creatFriendDialogInterface
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@CreateFriendDialog,0.9f,0.25f)
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCreateFindFriendBinding.inflate(inflater,container,false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setFullScreen()

        binding.buttonDialogCancle.setOnClickListener {
            this.creatFriendDialogInterface?.onClick()
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    interface CreateFriendDialogInterface{
        fun onClick()
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