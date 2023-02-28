package com.team.teamrestructuring.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import com.team.teamrestructuring.databinding.DialogCreateNicknameBinding
import com.team.teamrestructuring.databinding.DialogCreatePetBinding
import com.team.teamrestructuring.service.*
import kotlinx.coroutines.*
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
            Log.d(TAG, "CreateUserNickname: ${ApplicationClass.currentUser.userProfile.nickname}")
            sendToServerNickname(binding.edittextDialogNickname.text.toString(),ApplicationClass.currentUser.email)
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
        var job : Job? = null
        Log.d(TAG, "sendToServerQuestData: ${nickname}")
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = ApplicationClass.retrofit.create(QuestService::class.java).createQuestUser(nickname)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    Log.d(TAG, "sendToServerQuestData: ${response.body()!!}")
                }else{
                    Log.d(TAG, "sendToServerQuestData: error")
                }
            }
        }

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
        var job : Job? = null

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = ApplicationClass.retrofit.create(HomeService::class.java).createNickName(ApplicationClass.currentUser.userProfile.nickname
            ,ApplicationClass.currentUser.email)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    Log.d(TAG, "sendToServerNickname: ${response.body()!!}")
                    sendToServerQuestData(ApplicationClass.currentUser.userProfile.nickname)
                }else{
                    binding.dialogNicknameTitle.text = "중복된 닉네임입니다"
                    binding.dialogNicknameTitle.setTextColor(Color.RED)
                }
            }
        }

    }



}