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
import retrofit2.create
import java.text.SimpleDateFormat
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val TAG = "SSAFY_TodoFragment"
class TodoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding : FragmentTodoBinding

    //현재 시간
    val calendar = Calendar.getInstance()
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val now = format.format(calendar.time)


    // 날짜 저장
    var dateStr = now

    //  리사이클러뷰에서 쓸 녀석, 전역 해야지
    lateinit var stringList: MutableList<String>
    var todo_id : Long = -1
    private lateinit var todoAdapter: TodoAdapter
    // Todo 정보 저장
    lateinit var todoList: MutableList<Todo>

    // 클릭한 날짜
    private var nowDate = Date()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentTodoBinding.inflate(layoutInflater)
        val calendar: Calendar = Calendar.getInstance()
        Log.d("제발", "${now}")

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
    // Todo를 가져오는 서비스
    private fun callService(nickName: String, dateTime: String){
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .getTodo(nickName, dateTime).enqueue(object : Callback<MutableList<Todo>>{
                override fun onResponse(
                    call: Call<MutableList<Todo>>,
                    response: Response<MutableList<Todo>>

                ) {
                    if(response.isSuccessful){
                        Log.d(TAG, "onResponse: ${response.body()}")
                        todoList = response.body() ?: mutableListOf()
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

    // 투두를 지우는 서비스
    private fun deleteTodoService(id: Long){
        val service = ApplicationClass.retrofit.create(TodoService:: class.java)
            .deleteTodo(id).enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "성공적으로 삭제 되었습니다.")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "삭제에 실패하였습니다.")
                }
            })

    }

    //투두를 수정하는 서비스
    private fun modifyTodoService(id: Long, Todo: Todo){
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .modifyTodo(id, Todo).enqueue(object :Callback<Todo>{
                override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "수정된 투두 입니다 ${Todo}")
                    }
                }

                override fun onFailure(call: Call<Todo>, t: Throwable) {
                    Log.d(TAG, "수정 실패 ${t.message}")
                }

            })

    }


    //투두를 체크(완료했는 지)하는 서비스
    private fun checkTodo(id: Long){
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .checkTodo(id).enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful){
                        Log.d(TAG,"투두 완료 되었습니다.")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "투두 완료에 실패했습니다.")
                }
            })
    }


    private fun initDate() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")


        binding.apply {
//            // 처음 초기 리스트 생성하기 위한 로직
            nowDate = Date(calender.date)
            Toast.makeText(requireContext(), nowDate.toString(), Toast.LENGTH_LONG).show()
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
                // 더미 값인 리진성을 넣었다.
                callService(nickName = "리진성", dateStr)
            }
        }
    }
    // 옵저버 뷰 예시예시
//    private val todoObserver = Observer<Todo> { todo ->
//        // update UI with the todo data
//        Log.d(TAG, "initInput: ${todo.toString()}")
//        createService(todo)
//        callService()
//    }
    // 캘린더뷰
    // 투두리스트 작성
    private fun initInput() {
        binding.apply {
            CreateTodoButton.apply {
                setOnClickListener {
                    var nowInput = calenderText.text.toString().trim()
                    Log.d(TAG, "initInput: ${nowInput}")
                    // 입력 값이 없을 때
                    if (nowInput.trim().isEmpty()) {
                        Toast.makeText(requireContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    Log.d(TAG, "initInput2: ${nowInput}")
                    val todo = Todo(nowInput, dateStr)
                    // 바에 있는 값 초기화
                    calenderText.text.clear()
                    createService(todo)
                    // 더미 데이터 리진성을 넣었다.
                    callService(nickName = "리진성", dateStr)

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
                    Log.d("리", todoList[position].todo_id.toString())
                    todo_id = todoList[position].todo_id
                }
            }
            recyclerviewTodoList.apply {
                adapter = todoAdapter
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            }
        }
        // 더미 데이터인 리진성을 넣었다.
        callService(nickName = "리진성", dateStr)
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


