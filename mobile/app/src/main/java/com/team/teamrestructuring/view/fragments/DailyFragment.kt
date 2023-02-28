package com.team.teamrestructuring.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.FragmentDailyBinding
import com.team.teamrestructuring.dto.DailyQuest
import com.team.teamrestructuring.dto.QuestEnum
import com.team.teamrestructuring.service.QuestService
import com.team.teamrestructuring.service.StepService
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.util.CreateQuestResultDialog
import com.team.teamrestructuring.view.activities.HomeActivity
import com.team.teamrestructuring.view.adapters.DailyQuestAdapter
import com.team.teamrestructuring.view.viewmodels.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding:FragmentDailyBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DailyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailyFragment : Fragment() ,CreateQuestResultDialog.CreateResultListener{

    companion object {

        private const val TAG = "DailyFragment_지훈"
        lateinit var questAdapter: DailyQuestAdapter
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DailyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DailyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val mainViewModel by activityViewModels<HomeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        super.onResume()
        getQuestData(ApplicationClass.currentUser.userProfile.nickname)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDailyBinding.inflate(layoutInflater,container,false)

        mainViewModel.dailyQuestData.observe(viewLifecycleOwner,{
            questAdapter.datas = it
            questAdapter.notifyDataSetChanged()
        })


        questAdapter = DailyQuestAdapter(emptyList<DailyQuest>())
        questAdapter.setOnQuestClickListener(object : DailyQuestAdapter.QuestClickListener{
            override fun onClick(view: View, position: Int, data: DailyQuest) {
                when(position){
                    //1번 퀘스일 경우
                    0->{
                        val currentTime = System.currentTimeMillis()
                        val dData = Date(currentTime)

                        val format = SimpleDateFormat("HH:mm")
                        val res = format.format(dData)
                        var hour = 0
                        var minute = 0

                        if(res.substring(0 until 1).equals("0")){
                            hour = res.substring(1 until 2).toInt()
                        }else{
                            hour = res.substring(0 until 2).toInt()
                        }
                        if(res.substring(3 until 4).equals("0")){
                            minute = res.substring(4 until 5).toInt()
                        }else{
                            minute = res.substring(3 until 5).toInt()
                        }
                        //장소와 시간을 등록한 경우
                        if(ApplicationClass.currentUser.userProfile.time!=0 && ApplicationClass.currentUser.userProfile.latitude!=0.0){
                            if(ApplicationClass.currentUser.userProfile.time>hour*60+minute){
                                if(QuestFragment.distance<70){
                                    val dialog = CreateQuestResultDialog(this@DailyFragment,"퀘스트를 성공하셨습니다",QuestEnum.TRUE,data)
                                    dialog.isCancelable = false
                                    dialog.show(activity!!.supportFragmentManager,"성공 알람")
                                }else{
                                    val dialog = CreateQuestResultDialog(this@DailyFragment,"${ApplicationClass.currentUser.userProfile.location}에 더 접근해주세요\n" +
                                            "현재 목적지까지와의 거리는 ${QuestFragment.distance}m 입니다 더 가까이 가주세요",QuestEnum.NOT_YET,data)
                                    dialog.isCancelable = false
                                    dialog.show(activity!!.supportFragmentManager,"미성공")
                                }
                            }
                            else{
                                val dialog = CreateQuestResultDialog(this@DailyFragment,"오늘은 지각하셨네요.. 내일은 꼭 일찍!!",QuestEnum.FALSE,data)
                                dialog.isCancelable = false
                                dialog.show(activity!!.supportFragmentManager,"실패 알람")
                            }

                        }else{
                            val dialog = CreateQuestResultDialog(this@DailyFragment,"시간과 장소를 등록해주세요",QuestEnum.NOT_YET,data)
                            dialog.isCancelable = false
                            dialog.show(activity!!.supportFragmentManager,"등록 요구 알람")
                        }

                    }
                    //2번일 경우
                    1->{
                        val walk_count = StepService.mStepCounter - HomeActivity.sCount
                        if(walk_count>=20000000000){
                            val dialog = CreateQuestResultDialog(this@DailyFragment,"퀘스트를 성공하셨습니다",QuestEnum.TRUE,data)
                            dialog.isCancelable = false
                            dialog.show(activity!!.supportFragmentManager,"성공 알람")
                        }else{
                            val dialog = CreateQuestResultDialog(this@DailyFragment,
                                    "오늘 총 걸음수는 ${3218}보 입니다.\n" +
                                            "퀘스트를 완료하기 위해선 ${5000-3218}보만 더 걸어보세요.",QuestEnum.NOT_YET,data)
                            dialog.isCancelable = false
                            dialog.show(activity!!.supportFragmentManager,"미성공")
                        }
                    }
                }
            }
        })

        binding.recyclerviewDaily.apply {
            adapter = questAdapter
        }

        return binding.root
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
                        mainViewModel.updateQuest(response.body()!!)
                        questAdapter.datas = response.body()!!
                        questAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<DailyQuest>>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }

    override fun onConfirmButtonClick() {

    }

}