package com.team.teamrestructuring.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.FragmentDailyBinding
import com.team.teamrestructuring.dto.DailyQuest
import com.team.teamrestructuring.view.adapters.DailyQuestAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var questAdapter: DailyQuestAdapter
private var datas  = mutableListOf<DailyQuest>(DailyQuest(true,"학교 , 직장 지각하지 않기"),
    DailyQuest(false,"5000천보 이상 걷기"))
private lateinit var binding:FragmentDailyBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DailyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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

        binding = FragmentDailyBinding.inflate(layoutInflater,container,false)

        questAdapter = DailyQuestAdapter(datas)
       /* questAdapter.setOnQuestClickListener(object : DailyQuestAdapter.QuestClickListener{
            override fun onClick(view: View, position: Int, data: DailyQuest) {
                if(data.is_checked){
                    datas[position].is_checked = false
                }else{
                    datas[position].is_checked = true
                }
                questAdapter.notifyDataSetChanged()
            }


        })*/

        binding.recyclerviewDaily.apply {
            adapter = questAdapter
        }

        return binding.root
    }

    companion object {
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
}