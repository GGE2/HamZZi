package com.team.teamrestructuring.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.FragmentWeeklyBinding
import com.team.teamrestructuring.dto.QuestEnum
import com.team.teamrestructuring.dto.WeeklyQuest
import com.team.teamrestructuring.service.QuestService
import com.team.teamrestructuring.service.StepService
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.util.CreateQuestResultDialog
import com.team.teamrestructuring.view.activities.HomeActivity
import com.team.teamrestructuring.view.adapters.DailyQuestAdapter
import com.team.teamrestructuring.view.adapters.WeeklyQuestAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "WeeklyFragment_지훈"


/**
 * A simple [Fragment] subclass.
 * Use the [WeeklyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeeklyFragment : Fragment(),CreateQuestResultDialog.CreateResultListener{

    companion object {

        lateinit var weeklyAdapter : WeeklyQuestAdapter
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WeeklyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WeeklyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentWeeklyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWeeklyBinding.inflate(layoutInflater,container,false)
        weeklyAdapter = WeeklyQuestAdapter(emptyList<WeeklyQuest>())
        binding.recyclerviewWeekly.apply {
            adapter = weeklyAdapter
        }
        weeklyAdapter.setOnQuestClickListener(object:WeeklyQuestAdapter.WeeklyQuestClickListener{
            override fun onClick(view: View, position: Int, data: WeeklyQuest) {
                when(position){
                    0->{
                        val walk_count = StepService.mStepCounter - HomeActivity.sCount
                        if(walk_count>=50000){
                            val dialog = CreateQuestResultDialog(this@WeeklyFragment,"퀘스트를 성공하셨습니다",
                                QuestEnum.TRUE,data)
                            dialog.isCancelable = false
                            dialog.show(activity!!.supportFragmentManager,"성공 알람")
                        }else{
                            val dialog = CreateQuestResultDialog(this@WeeklyFragment,
                                "오늘 총 걸음수는 ${walk_count}보 입니다.\n" +
                                        "퀘스트를 완료하기 위해선 ${50000-walk_count}보만 더 걸어보세요.", QuestEnum.NOT_YET,data)
                            dialog.isCancelable = false
                            dialog.show(activity!!.supportFragmentManager,"미성공")
                        }
                    }
                }
            }

        })
        getWeeklyQuest()
        return binding.root
    }

    private fun getWeeklyQuest(){
        val service = ApplicationClass.retrofit.create(QuestService::class.java)
            .getWeeklyQuestList(ApplicationClass.currentUser.userProfile.nickname).enqueue(object:Callback<List<WeeklyQuest>>{
                override fun onResponse(
                    call: Call<List<WeeklyQuest>>,
                    response: Response<List<WeeklyQuest>>
                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()!!}")
                        weeklyAdapter.datas = response.body()!!
                        weeklyAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<List<WeeklyQuest>>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }



    override fun onConfirmButtonClick(id: Int) {
        when(id){
            2->{
                parentFragmentManager.beginTransaction().replace(R.id.fragment_quest_container,DailyFragment()).commit()
                parentFragmentManager.beginTransaction().replace(R.id.fragment_quest_container,WeeklyFragment()).commit()
            }
        }
    }


}