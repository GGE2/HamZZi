package com.team.teamrestructuring.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import com.team.teamrestructuring.databinding.DialogConfirmPlaceBinding
import com.team.teamrestructuring.databinding.DialogCreatePetBinding
import com.team.teamrestructuring.databinding.DialogCreateQuestResultBinding
import com.team.teamrestructuring.dto.DailyQuest
import com.team.teamrestructuring.dto.Place
import com.team.teamrestructuring.dto.QuestEnum
import com.team.teamrestructuring.dto.User
import com.team.teamrestructuring.service.HomeService
import com.team.teamrestructuring.service.LoginService
import com.team.teamrestructuring.service.PlaceService
import com.team.teamrestructuring.service.QuestService
import com.team.teamrestructuring.view.fragments.DailyFragment
import com.team.teamrestructuring.view.fragments.QuestFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG="CreateQuestResult_지훈"
class CreateQuestResultDialog(
    createResultListener:CreateResultListener,
    text:String,
    enum: QuestEnum,
    quest:DailyQuest
) : DialogFragment(){

    private var _binding: DialogCreateQuestResultBinding? = null
    private val binding get() = _binding!!
    private var createResultListener : CreateResultListener? = null
    private var text:String? = null
    private var enum : QuestEnum? = null
    private var quest:DailyQuest? = null
    init{
        this.createResultListener= createResultListener
        this.text = text
        this.enum = enum
        this.quest = quest
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCreateQuestResultBinding.inflate(inflater,container,false)
        val view = binding.root

        setFullScreen()

        binding.textviewDialogQuestResultTitle.text = text

        binding.buttonDialogQuestResult.setOnClickListener {
            this.createResultListener?.onConfirmButtonClick()
            when(enum){
                QuestEnum.TRUE->{
                    Log.d(TAG, "onCreateView: ${true}")
                    sendToServerResult()
                    getQuestData(ApplicationClass.currentUser.userProfile.nickname)
                }
                QuestEnum.FALSE->{
                    Log.d(TAG, "onCreateView: ${false}")
                    sendToServerResult()
                    getQuestData(ApplicationClass.currentUser.userProfile.nickname)
                }
                else -> {
                    Log.d(TAG, "onCreateView:x` ")
                    getQuestData(ApplicationClass.currentUser.userProfile.nickname)
                }
            }
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    interface CreateResultListener{
        fun onConfirmButtonClick()
    }

    private fun getQuestData(nickname:String){
        val service = ApplicationClass.retrofit.create(QuestService::class.java)
            .getQuestList(nickname).enqueue(object:Callback<List<DailyQuest>>{
                override fun onResponse(
                    call: Call<List<DailyQuest>>,
                    response: Response<List<DailyQuest>>
                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()}")
                        DailyFragment.questAdapter.datas = response.body()!!
                        DailyFragment.questAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<DailyQuest>>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }


   fun sendToServerResult(){
       val service = ApplicationClass.retrofit.create(QuestService::class.java)
           .updateQuestResult(quest!!.nickname,quest!!.q_user_id.toInt(),quest!!.quest.quest_id.toInt()).enqueue(object:Callback<String>{
               override fun onResponse(call: Call<String>, response: Response<String>) {
                   if(response.isSuccessful){
                       Log.d(TAG, "onResponse: ${response.body()!!}")
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