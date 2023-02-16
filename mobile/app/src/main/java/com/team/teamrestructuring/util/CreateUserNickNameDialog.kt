package com.team.teamrestructuring.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import com.team.teamrestructuring.databinding.DialogCreateNicknameBinding
import com.team.teamrestructuring.databinding.DialogCreatePetBinding
import com.team.teamrestructuring.service.HomeService
import com.team.teamrestructuring.service.QuestService
import com.team.teamrestructuring.service.TodoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG="CreateUserog_지훈"
class CreateUserNickNameDialog(
    createuserNickNameDialogInterface: CreateUserNickNameDialog
) : DialogFragment(){

    private var _binding: DialogCreateNicknameBinding? = null
    private val binding get() = _binding!!
    private var createuserNickNameDialogInterface : CreateUserNickNameDialog? = null

    init{
        this.createuserNickNameDialogInterface= createuserNickNameDialogInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCreateNicknameBinding.inflate(inflater,container,false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setFullScreen()


        binding.buttonPetNickname.setOnClickListener {
            createuserNickNameDialogInterface?.onClick()
            ApplicationClass.currentUser.userProfile.nickname = binding.edittextDialogNickname.text.toString()
            sendToServerNickname(binding.edittextDialogNickname.text.toString(),ApplicationClass.currentUser.email)
            sendToServerQuestData(binding.edittextDialogNickname.text.toString())
            sendToServerCount(binding.edittextDialogNickname.text.toString())
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    interface CreateUserNickNameDialog{
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

    private fun sendToServerQuestData(nickname: String){
        Log.d(TAG, "sendToServerQuestData: ${nickname}")
        val service = ApplicationClass.retrofit.create(QuestService::class.java)
            .createQuestUser(nickname).enqueue(object : Callback<String>{
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

    private fun sendToServerCount(nickname: String){
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .createCount(nickname).enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "Count Response: ${response.body()!!}")
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }
            })

    }



    private fun sendToServerNickname(nickname:String,email:String){
        val service = ApplicationClass.retrofit.create(HomeService::class.java)
            .createNickName(nickname,email).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "유저 닉네임 서버 전송 완료")
                        dismiss()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })

    }



}