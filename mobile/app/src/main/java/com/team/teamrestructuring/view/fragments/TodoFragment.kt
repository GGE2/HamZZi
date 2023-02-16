package com.team.teamrestructuring.view.fragments
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.team.teamrestructuring.databinding.FragmentTodoBinding
import com.team.teamrestructuring.dto.Todo
import com.team.teamrestructuring.service.TodoService
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.view.adapters.TodoAdapter
import com.team.teamrestructuring.util.TodoBottomSheet
import com.team.teamrestructuring.view.viewmodels.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val TAG = "TodoFragment_지훈"
class TodoFragment : Fragment(),TodoBottomSheet.SetOnModifyButtonInterface{

    companion object {
        lateinit var todoAdapter : TodoAdapter
        // Todo 정보 저장
        var todoList: MutableList<Todo> = mutableListOf()
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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentTodoBinding
    private val mainViewModel by activityViewModels<HomeViewModel>()

    //현재 시간
    private val calendar = Calendar.getInstance()
    private val todayCalendar = Calendar.getInstance()
    private val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val now = format.format(calendar.time)

    // 날짜 저장
    var dateStr = now

    val nickName = ApplicationClass.currentUser.userProfile.nickname


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ${nickName}")
        todoList = mutableListOf()
        initRecyclerView()
        initDate()
        callService(nickName, dateStr)

        mainViewModel.todoData.observe(viewLifecycleOwner,{
            Log.d(TAG, "onViewCreated: ${it}")
        })


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTodoBinding.inflate(layoutInflater)
        return binding.root!!
    }

    override fun onResume() {
        super.onResume()
        callService(nickName,dateStr)

    }

    // Todo를 가져오는 서비스
    private fun callService(nickName: String, dateTime: String) {
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .getTodo(nickName, dateTime).enqueue(object : Callback<MutableList<Todo>> {
                override fun onResponse(
                    call: Call<MutableList<Todo>>,
                    response: Response<MutableList<Todo>>
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "Call onResponse: ${response.body()}")
                        mainViewModel.updateTodo(response.body()!!)
                        todoAdapter.items = response.body()!!
                        todoAdapter.notifyDataSetChanged()
                    } else {
                        Log.d(TAG, "Call onResponse error: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<MutableList<Todo>>, t: Throwable) {
                    Log.d(TAG, "Call onFailure: ${t.message}")
                }
            })

    }

    //투두를 체크(완료했는 지)하는 서비스
    private fun checkTodo(id: Long, nickName: String){
        Log.d(TAG, "checkTodo:")
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .checkTodo(id, nickName).enqueue(object : Callback<String>{
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

                Log.i("시간", "지금 시간${now}  달력시간${dateStr}")
                callService(ApplicationClass.currentUser.userProfile.nickname,dateStr)
            }
        }
    }


    // 리사이클러뷰 클릭 투두 체크 수정 삭제
    private fun initRecyclerView() {

        todoAdapter = TodoAdapter(todoList)
        todoAdapter.setOnTodoClickListener(object : TodoAdapter.TodoItemClickListener{
            override fun onClick(view: View, position: Int, data: Todo) {
                Log.d(TAG, "onClick: ")
                checkTodo(data.todo_id,nickName)
            }
        })

        binding.recyclerviewTodoList.adapter = todoAdapter

        /* todoAdapter.itemClick = object: TodoAdapter.ItemClick {
             // 체크 눌렀을 때
             override fun onClick(view: View, position: Int) {
                 if(todoList[position].todo_id == null){
//                        Toast.makeText(context, "투두 체크를 위해선 잠깐만 고민을 해주세요.", Toast.LENGTH_LONG).show()
                     // 체크가 안된 투두리스트만 checkTodo 보내개
                 } else if (todoList[position].is_check == false){
                     Log.w(TAG, todoList[position].toString())
                     checkTodo(todoList[position].todo_id!!, nickName)
                 }
             }
         }*/
        // 체크 박스 눌렀을 때
        /*todoAdapter.boxClick = object: TodoAdapter.BoxClick {
            override fun onClick(view: View, position: Int) {
                binding.apply {

                    // 날짜객체 데이 기준으로 비교
                    Log.d("하이하이", "투데이${now}")
                    Log.i("하이하이", "날짜${dateStr}")


                    if (todayCalendar == null) {
                        return
                    }
                    if (now != dateStr){
                        return
                    }
                }




                Log.i("체크박스", position.toString())
//                    Toast.makeText(context, position, Toast.LENGTH_SHORT).show()kk
                if (todoList[position].todo_id == null) {
//                        Toast.makeText(context, "투두 체크를 위해선 잠깐만 고민을 해주세요.", Toast.LENGTH_SHORT).show()
                } else if (todoList[position].is_check == false){
                    Log.w("체크박스", todoList[position].toString())
                    Log.w(TAG, todoList[position].toString())
                    if (now == dateStr){
                        Log.w("시간", "${now} ${dateStr} ${todoList}")
                        checkTodo(todoList[position].todo_id!!, nickName)
                    }
                }
            }
        }
        recyclerviewTodoList.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        }*/
    }

}

