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
import com.team.teamrestructuring.dto.*
import com.team.teamrestructuring.service.*
import com.team.teamrestructuring.view.activities.HomeActivity
import com.team.teamrestructuring.view.fragments.DailyFragment
import com.team.teamrestructuring.view.fragments.HomeFragment
import com.team.teamrestructuring.view.fragments.QuestFragment
import com.team.teamrestructuring.view.fragments.WeeklyFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG="CreateQuestResult_지훈"
class CreateQuestResultDialog(
    createResultListener:CreateResultListener,
    text:String,
    enum: QuestEnum
) : DialogFragment(){

    private var _binding: DialogCreateQuestResultBinding? = null
    private val binding get() = _binding!!
    private var createResultListener : CreateResultListener? = null
    private var text:String? = null
    private var enum : QuestEnum? = null
    private var quest:DailyQuest? = null
    private var weeklyQ:WeeklyQuest? = null
    init{
        this.createResultListener= createResultListener
        this.text = text
        this.enum = enum
    }
    constructor(createResultListener:CreateResultListener,
                text:String,
                enum: QuestEnum,
                quest:DailyQuest) : this(createResultListener, text, enum){
                    this.quest = quest
                }
    constructor(createResultListener:CreateResultListener,
                text:String,
                enum: QuestEnum,weeklyQ:WeeklyQuest) : this(createResultListener, text, enum){
                    this.weeklyQ = weeklyQ
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
                    if(quest!=null) {
                        sendToServerResult()
                        //updateExp()
                        getQuestData(ApplicationClass.currentUser.userProfile.nickname)
                    }else{
                        sendToServerWeeklyResult()
                        //updateExp()
                        getWeeklyQuestData()
                    }
                    HomeActivity.viewPagerAdapter.refreshFragment(2,QuestFragment())
                }
                QuestEnum.FALSE->{
                    if(quest!=null){
                    sendToServerResult()
                    getQuestData(ApplicationClass.currentUser.userProfile.nickname)
                    }else{
                        sendToServerWeeklyResult()
                        getWeeklyQuestData()
                    }
                    HomeActivity.viewPagerAdapter.refreshFragment(2,QuestFragment())
                }
                else -> {
                    if(quest!=null) {
                        getQuestData(ApplicationClass.currentUser.userProfile.nickname)
                        if(quest!!.quest.quest_id.toInt()==1&&ApplicationClass.currentUser.userProfile.latitude==0.0)
                            HomeActivity.binding.viewpagerMainPager.currentItem = 3
                    }else{
                        getWeeklyQuestData()
                    }
                    dismiss()
                }
            }
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

    private fun getCurrentUserInfo(){
        val service = ApplicationClass.retrofit.create(LoginService::class.java)
            .getUserInfo(ApplicationClass.currentUser.uid).enqueue(object:Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if(response.isSuccessful){
                        val data = response.body()!!
                        ApplicationClass.currentUser = data
                        Log.d(TAG, "onResponse: ${ApplicationClass.currentUser}")
                        dismiss()

                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }



    private fun getWeeklyQuestData(){
        val service = ApplicationClass.retrofit.create(QuestService::class.java)
            .getWeeklyQuestList(ApplicationClass.currentUser.userProfile.nickname).enqueue(object:Callback<List<WeeklyQuest>>{
                override fun onResponse(
                    call: Call<List<WeeklyQuest>>,
                    response: Response<List<WeeklyQuest>>
                ) {
                    if(response.isSuccessful){
                        WeeklyFragment.weeklyAdapter.datas = response.body()!!
                        WeeklyFragment.weeklyAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<WeeklyQuest>>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
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
    private fun sendToServerWeeklyResult(){
        val service = ApplicationClass.retrofit.create(QuestService::class.java)
            .updateWeeklyQuestResult(weeklyQ!!.nickname,weeklyQ!!.questWeekly_id.toInt(),weeklyQ!!.quest.quest_id.toInt())
            .enqueue(object:Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "Weekly Result: ${response.body()!!}")
                        getCurrentUserInfo()
                        HomeFragment().setPetStat()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }

   fun sendToServerResult(){
       val service = ApplicationClass.retrofit.create(QuestService::class.java)
           .updateQuestResult(quest!!.nickname,quest!!.questDaily_id.toInt(),quest!!.quest.quest_id.toInt()).enqueue(object:Callback<String>{
               override fun onResponse(call: Call<String>, response: Response<String>) {
                   if(response.isSuccessful){
                       Log.d(TAG, "Daily Result: ${response.body()!!}")
                       getCurrentUserInfo()
                   }
               }

               override fun onFailure(call: Call<String>, t: Throwable) {
                   Log.d(TAG, "onFailure: ${t.message}")
               }

           })
   }

    private fun getPetInfo(){
        /*val service = ApplicationClass.retrofit.create(PetService::class.java)
            .getPetInfo(ApplicationClass.currentUser.userProfile.nickname).enqueue(object:Callback<PetData>{
                override fun onResponse(call: Call<PetData>, response: Response<PetData>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()!!}")
                        ApplicationClass.petData = response.body()!!
                        HomeFragment.exp = response.body()!!.pet.exp
                    }
                }
                override fun onFailure(call: Call<PetData>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
*/

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