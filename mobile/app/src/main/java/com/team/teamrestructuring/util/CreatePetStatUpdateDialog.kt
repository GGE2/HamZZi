package com.team.teamrestructuring.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import com.team.teamrestructuring.databinding.DialogCreatePetBinding
import com.team.teamrestructuring.databinding.DialogCreateUpdateStatBinding
import com.team.teamrestructuring.dto.CreatePet
import com.team.teamrestructuring.service.HomeService
import com.team.teamrestructuring.service.PetService
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

    init{
        this.createStatUpdateInterface= createStatUpdateInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCreateUpdateStatBinding.inflate(inflater,container,false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setFullScreen()

        


        /*binding.buttonPetCreate.setOnClickListener {
            val pet = CreatePet(binding.edittextDialogFind.text.toString(),ApplicationClass.currentUser.userProfile.nickname)
            sendToServerPetNickname(pet)
            createPetDialogInterface?.onYesButtonClick()
        }*/

        return view
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





}