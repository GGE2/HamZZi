package com.team.teamrestructuring.view.fragments
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.teamrestructuring.databinding.FragmentTodoBinding
import com.team.teamrestructuring.dto.Todo
import com.team.teamrestructuring.service.TodoService
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.util.TodoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    //초기 시간 받아와야함 지금은 임의로 넣어줌


    // 날짜 저장
    lateinit var dateStr: String

    //  리사이클러뷰에서 쓸 녀석, 전역 해야지
    lateinit var stringList: MutableList<String>

    private lateinit var todoAdapter: TodoAdapter
    // 클릭한 날짜
    private var nowDate = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentTodoBinding.inflate(layoutInflater)
        val calendar: Calendar = Calendar.getInstance()
        Log.d("제발", "${calendar.timeInMillis}")

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stringList = mutableListOf<String>()
        initRecyclerView()
        initDate()
        initInput()
    }
    //
    interface TodoListner{

    }
    // Todo를 비동기로 가져오는 것 인자를 안넣어서 미완성 날짜를 넘겨줘야함함
    private fun callService(){
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .getTodo().enqueue(object : Callback<MutableList<Todo>>{
                override fun onResponse(
                    call: Call<MutableList<Todo>>,
                    response: Response<MutableList<Todo>>

                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()}")
                        val todoList = response.body() ?: mutableListOf()
                        binding.apply {
                            todoAdapter.items = todoList
                            todoAdapter.notifyDataSetChanged()
                        }



                        Log.w(TAG, "${todoList}")
                    }
                }

                override fun onFailure(call: Call<MutableList<Todo>>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })

    }
    // 투두를 만드는 서비스
    private fun createService(Todo: Todo){
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .createTodo(Todo).enqueue(object: Callback<Todo>{
                override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "입력된 투두 입니다 ${Todo}")
                    }
                }

                override fun onFailure(call: Call<Todo>, t: Throwable) {
                    Log.d(TAG, "입력 실패 ${t.message}")
                }

            })
    }
    private fun deleteTodoService(){
        /*val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .deletetodo().enqueue(object:Callback<String>)*/

    }

    private fun initDate() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")


        binding.apply {
//            // 처음 초기 리스트 생성하기 위한 로직
            nowDate = Date(calender.date)
            Toast.makeText(requireContext(), nowDate.toString(), Toast.LENGTH_LONG).show()
            var nowDateDay = nowDate.day
//            if (nowDateDay % 2  == 0) {
//                stringList.clear()
//                stringList.addAll(evenList)
//
//                todoAdapter.items = stringList
//                todoAdapter.notifyDataSetChanged()
//            } else {
//                stringList.clear()
//                stringList.addAll(oddList)
//
//                todoAdapter.items = stringList
//                todoAdapter.notifyDataSetChanged()
//            }

            // 초기 날짜 설정하는 날짜
            if (Calendar.MONTH + 1 < 10 && Calendar.DAY_OF_MONTH < 10){
                dateStr = "${Calendar.YEAR+2022}-0${Calendar.MONTH+1}-0${Calendar.DAY_OF_MONTH}"
            } else if (Calendar.MONTH + 1 < 10){
                dateStr = "${Calendar.YEAR+2022}-0${Calendar.MONTH+1}-${Calendar.DAY_OF_MONTH}"
            } else if (Calendar.DAY_OF_MONTH < 10) {
                dateStr = "${Calendar.YEAR+2022}-${Calendar.MONTH+1}-0${Calendar.DAY_OF_MONTH}"
            } else{
                dateStr = "${Calendar.YEAR}-${Calendar.MONTH+1}-${Calendar.DAY_OF_MONTH}"
            }

            // 클릭할때마다 날짜 바뀌면 리스트 다시 받아오는 로직
            calender.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                // 달력 표준으로 변환
                if (month.toInt() + 1 < 10 && dayOfMonth.toInt() < 10){
                    dateStr =  "${year}-0${month+1}-0${dayOfMonth}"
                } else if (month.toInt() + 1 < 10){
                    dateStr =  "${year}-0${month+1}-${dayOfMonth}"
                } else if (dayOfMonth.toInt() < 10) {
                    dateStr =  "${year}-${month+1}-0${dayOfMonth}"
                }   else{
                    dateStr =  "${year}-${month+1}-${dayOfMonth}"
                }
                Log.i(TAG, dateStr)

                // 클릭시 마다 post 요청을 보내서 받아온다
                callService()
            }
        }
    }
    // 옵저버 뷰 예시예시
    private val todoObserver = Observer<Todo> { todo ->
        // update UI with the todo data
        Log.d(TAG, "initInput: ${todo.toString()}")
        createService(todo)
        callService()
    }
    // 캘린더뷰
    // 투두리스트 작성
    private fun initInput() {
        binding.apply {
            CreateTodoButton.apply {
                setOnClickListener {
                    val nowInput = calenderText.text.toString()
                    Log.d(TAG, "initInput: ${nowInput}")
                    // 입력 값이 없을 때
                    if (nowInput.trim().isEmpty()) {
                        Toast.makeText(requireContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    Log.d(TAG, "initInput22222: ${nowInput}")
                    val todo = Todo(nowInput, dateStr)
                    createService(todo)
                    callService()

                }
            }
        }
    }

    private  fun initRecyclerView() {
        binding.apply {
            todoAdapter = TodoAdapter(mutableListOf())
            todoAdapter.itemClick = object: TodoAdapter.ItemClick {
                override fun onClick(view: View, position: Int) {
                    // 리사이클러뷰 개별 리스트의 위치 정보를 담는 변수
                    val itemPosition = position
                    Toast.makeText(view.context, itemPosition.toString(), Toast.LENGTH_LONG).show()
                }
            }
            recyclerviewTodoList.apply {
                adapter = todoAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            }
        }
        callService()
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


