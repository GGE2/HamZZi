package com.team.teamrestructuring.view.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.teamrestructuring.databinding.FragmentTodoBinding
import com.team.teamrestructuring.util.TodoAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val TAG = "SSAFY_TodoFragment"
class TodoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding : FragmentTodoBinding

    // 테스트용 데이터
    lateinit var oddList: MutableList<String>
    lateinit var evenList: MutableList<String>

    //  리사이클러뷰에서 쓸 녀석, 전역 해야지
    lateinit var stringList: MutableList<String>

    private lateinit var todoAdapter: TodoAdapter
    lateinit var nowDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentTodoBinding.inflate(layoutInflater)
        val calendar: Calendar = Calendar.getInstance()

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stringList = mutableListOf<String>()
        oddList = mutableListOf<String>()
        evenList = mutableListOf<String>()

        oddList.apply {
            add("a")
            add("b")
        }

        evenList.apply {
            add("c")
            add("d")
        }

        initRecyclerView()
        initDate()
        initInput()
    }

    private fun initDate() {
        val dateFormat = SimpleDateFormat("yyyyMMdd")

        binding.apply {
            // 처음 초기 리스트 생성하기 위한 로직
            nowDate = Date(calender.date)
            var nowDateDay = nowDate.day
            if (nowDateDay % 2  == 0) {
                stringList.clear()
                stringList.addAll(evenList)

                todoAdapter.items = stringList
                todoAdapter.notifyDataSetChanged()
            } else {
                stringList.clear()
                stringList.addAll(oddList)

                todoAdapter.items = stringList
                todoAdapter.notifyDataSetChanged()
            }


            // 클릭할때마다 날짜 바뀌면 리스트 다시 받아오는 로직
            calender.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                if (dayOfMonth % 2  == 0) {
                    stringList.clear()
                    stringList.addAll(evenList)

                    todoAdapter.items = stringList
                    todoAdapter.notifyDataSetChanged()
                } else {
                    stringList.clear()
                    stringList.addAll(oddList)

                    todoAdapter.items = stringList
                    todoAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initInput() {
        binding.apply {
            CreateTodoButton.apply {
                setOnClickListener {
                    val nowInput = calenderText.text.toString()
                    if (nowInput.trim().isEmpty()) {
                        Toast.makeText(requireContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    stringList.add(nowInput.trim())
                    calenderText.setText("")

                    todoAdapter.items = stringList
                    todoAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private  fun initRecyclerView() {
        binding.apply {
            todoAdapter = TodoAdapter(stringList)
            todoAdapter.itemClick = object: TodoAdapter.ItemClick {
                override fun onClick(view: View, position: Int) {
                    // 클릭시 완료처리


                    Toast.makeText(requireContext(), stringList.get(position), Toast.LENGTH_SHORT).show()
                }
            }

            recyclerviewTodoList.apply {
                adapter = todoAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTodoBinding.inflate(layoutInflater)
        return binding.root!!
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TodoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
