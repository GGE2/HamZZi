package com.team.teamrestructuring.view.fragments
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.teamrestructuring.databinding.FragmentTodoBinding
import com.team.teamrestructuring.dto.Todo
import com.team.teamrestructuring.service.TodoService
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.view.adapters.TodoAdapter
import com.team.teamrestructuring.util.TodoBottomSheet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val TAG = "SSAFY_TodoFragment"
class TodoFragment : Fragment(),TodoBottomSheet.SetOnModifyButtonInterface{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding : FragmentTodoBinding

    //현재 시간
    private val calendar = Calendar.getInstance()
    private val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val now = format.format(calendar.time)
    // 날짜 저장
    var dateStr = now
    var todo_id : Int = -1

    // Todo 정보 저장
    var todoList: MutableList<Todo> = mutableListOf()
    val nickName = ApplicationClass.currentUser.userProfile.nickname.toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callService(nickName, dateStr)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 여기
        todoList = mutableListOf()
        initDate()
        callService(nickName, dateStr)
        initRecyclerView()
        initInput()
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
                    }
                }
                override fun onFailure(call: Call<MutableList<Todo>>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }
            })

    }
    // 투두를 만드는 서비스
    private fun createService(todo: Todo){
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .createTodo(todo).enqueue(object: Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "정상적으로 입력된 투두 입니다 ${response.body()}")
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d(TAG, "입력 실패 ${t.message}")
                }
            })
    }

    //투두를 체크(완료했는 지)하는 서비스
    private fun checkTodo(id: Int, nickName: String,todo: Todo){
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .checkTodo(id, nickName ,todo).enqueue(object : Callback<String>{
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

// 날짜 옮길때 마다 데이트가 체크가 됨
    private fun initDate() {
    Log.i("시간", "지금 시간${now}  달력시간${dateStr}")
        binding.apply {
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

                todoAdapter.items = mutableListOf()
                Log.i("시간", "지금 시간${now}  달력시간${dateStr}")
                callService(nickName, dateStr)
            }
        }
    }
    private fun initInput() {
        binding.apply {
            CreateTodoButton.apply {
                setOnClickListener {
                    var nowInput = calenderText.text.toString().trim()
                    // 입력 값이 없을 때
                    if (nowInput.trim().isEmpty()) {
                        Toast.makeText(requireContext(), "값을 입력해주세요", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    val todo = Todo(nowInput, dateStr, nickName)
                    // 바에 있는 값 초기화
                    calenderText.text.clear()
                    createService(todo)
                    // 로컬 리스트에 받아오고 어차피 받아오는 와중에 비동기 통신이 가니깐
                    // 로컬에서 처리하니깐 리소스가 들든다 like 레트로핏, 코루틴 라이브데이터
                    todoList.add(todo)
                    todoAdapter.items = todoList
                    todoAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    // 리사이클러뷰 클릭 투두 체크 수정 삭제
    private fun initRecyclerView() {
        binding.apply {
            todoAdapter = TodoAdapter(mutableListOf())
            todoAdapter.menuClick = object : TodoAdapter.MemuClick {
                // 점 세개 눌렀을 때
                override fun onClick(view: View, position: Int) {
                    Log.i("투두", todoList[position].toString())
                    if (todoList[position].todo_id == null){
                        Toast.makeText(context, "투두 수정을 위해선 잠깐만 고민을 해주세요.", Toast.LENGTH_SHORT).show()
                    } else{
                        todo_id = todoList[position].todo_id!!
                        // 다이어로그 코드
                        val bottomSheet = TodoBottomSheet(this@TodoFragment, todoList[position], dateStr, position, todoList)
                        bottomSheet.show(activity!!.supportFragmentManager, bottomSheet.tag)
                    }
                }
            }

            todoAdapter.itemClick = object: TodoAdapter.ItemClick {
                // 체크 눌렀을 때
                override fun onClick(view: View, position: Int) {
                    if(todoList[position].todo_id == null){
//                        Toast.makeText(context, "투두 체크를 위해선 잠깐만 고민을 해주세요.", Toast.LENGTH_LONG).show()
                        // 체크가 안된 투두리스트만 checkTodo 보내개
                    } else if (todoList[position].is_check == false){
                        Log.w(TAG, todoList[position].toString())
                        checkTodo(todoList[position].todo_id!!, nickName,todoList[position])
                    }
                }
            }
            // 체크 박스 눌렀을 때
            todoAdapter.boxClick = object: TodoAdapter.BoxClick {
                override fun onClick(view: View, position: Int) {
                    Log.i("체크박스", position.toString())
//                    Toast.makeText(context, position, Toast.LENGTH_SHORT).show()kk
                    if (todoList[position].todo_id == null) {
//                        Toast.makeText(context, "투두 체크를 위해선 잠깐만 고민을 해주세요.", Toast.LENGTH_SHORT).show()
                    } else if (todoList[position].is_check == false){
                        Log.w("체크박스", todoList[position].toString())
                            Log.w(TAG, todoList[position].toString())
                        if (now == dateStr){
                            Log.w("시간", "${now} ${dateStr} ${todoList}")
                            checkTodo(todoList[position].todo_id!!, nickName,todoList[position])
                        }
                    }
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
        lateinit var todoAdapter: TodoAdapter
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

    override fun onButtonClick() {
    }
}


