package com.team.teamrestructuring.view.fragments
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.google.android.material.snackbar.Snackbar
import com.team.teamrestructuring.databinding.FragmentTodoBinding
import com.team.teamrestructuring.dto.Todo
import com.team.teamrestructuring.service.TodoService
import com.team.teamrestructuring.util.ApplicationClass
import com.team.teamrestructuring.util.TodoAdapter
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

    //  리사이클러뷰에서 쓸 녀석, 전역 해야지
    lateinit var stringList: MutableList<String>
    var todo_id : Long = -1

    // Todo 정보 저장
    var todoList: MutableList<Todo> = mutableListOf()
    // 클릭한 날짜
    private var nowDate = Date()

    // 더미 데이터
    val nickName = ApplicationClass.currentUser.userProfile.nickname.toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stringList = mutableListOf<String>()
        initRecyclerView()
        callService(nickName, dateStr)
        todoAdapter.notifyDataSetChanged()
        initDate()
        initInput()


    }

    // Todo를 가져오는 서비스
    private fun callService(nickName: String, dateTime: String){
//        Log.d(TAG, "확인")
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .getTodo(nickName, dateTime).enqueue(object : Callback<MutableList<Todo>>{
                override fun onResponse(
                    call: Call<MutableList<Todo>>,
                    response: Response<MutableList<Todo>>
                ) {
                    Log.d(TAG, "${response}")
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
    private fun createService(todo: Todo){
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .createTodo(todo).enqueue(object: Callback<Todo>{
                override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                    if(response.isSuccessful){
                        Log.d(TAG, "정상적으로 입력된 투두 입니다 ${todo}")
//                        Toast.makeText(context, "입력되었습니다.", Toast.LENGTH_SHORT).show()
//                        Snackbar.make(binding.todoXml, "입력되었습니다.", Snackbar.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<Todo>, t: Throwable) {
                    Log.d(TAG, "입력 실패 ${t.message}")
                    Log.d(TAG, "${todo}")
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
        binding.apply {
//            // 처음 초기 리스트 생성하기 위한 로직
            nowDate = Date(calender.date)
//            Toast.makeText(requireContext(), nowDate.toString(), Toast.LENGTH_LONG).show()
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
                callService(nickName, dateStr)
            }
        }
    }
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
                    val todo = Todo(nowInput, dateStr, nickName)
                    // 바에 있는 값 초기화
                    calenderText.text.clear()
                    createService(todo)
                    // 로컬 리스트에 받아오고 어차피 받아오는 와중에 비동기 통신이 가니깐
                    // 로컬에서 처리하니깐 리소스가 들든다 like 레트로핏, 코루틴 라이브데이터
                    todoList.add(todo)
                    todoAdapter.items = todoList
                    todoAdapter.notifyDataSetChanged()
//                    val fragmentTransaction = fragmentManager.beginTransaction()
//                    fragmentTransaction.detach(TodoFragment).attach(TodoFragment).commit()

                }
            }
        }
    }

    // 투두 리싸이클러뷰를 클릭
    private fun initRecyclerView() {
        binding.apply {
            todoAdapter = TodoAdapter(mutableListOf())
            todoAdapter.menuClick = object : TodoAdapter.MemuClick {
                // 점 세개 눌렀을 때
                override fun onClick(view: View, position: Int) {
                    if (todoList[position].todo_id == null){

                        Toast.makeText(context, "투두 수정을 위해선 잠깐만 고민을 해주세요.", Toast.LENGTH_LONG).show()
//                        Snackbar.make(view, "투두 수정을 위해선 잠깐 고민을 해주세요", Snackbar.LENGTH_SHORT).show()
                    } else{
                        // 투두 삭제 그리고 즉시 반영 코드
//                        todo_id = todoList[position].todo_id!!
//                        deleteTodoService(todo_id)
//                        todoList.removeAt(position)
//                        todoAdapter.items = todoList
//                        todoAdapter.notifyDataSetChanged()
//


                        // 투두 수정 코드
//                        todo_id = todoList[position].todo_id!!
//                        val dumi = Todo("더미데이터입니다", dateStr, nickName)
//                        modifyTodoService(todo_id, dumi)
//                        todoList[position] = dumi
//                        todoAdapter.items = todoList
//                        todoAdapter.notifyDataSetChanged()

//                        todo_id = todoList[position].todo_id!!
//                        checkTodo(todo_id)
                        // 체크처리 어떻게 하지

                        todo_id = todoList[position].todo_id!!
                        Log.d(TAG, todoList[position].toString())
                        // 다이어로그 코드
                        val bottomSheet = TodoBottomSheet(this@TodoFragment, todoList[position], dateStr)
                        bottomSheet.show(activity!!.supportFragmentManager, bottomSheet.tag)

                    }

                }
            }
            todoAdapter.itemClick = object: TodoAdapter.ItemClick {
                // 체크 눌렀을 때
                override fun onClick(view: View, position: Int) {
                    Toast.makeText(requireContext(), "${position}을 눌렀습니다", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(view.context, itemPosition.toString(), Toast.LENGTH_LONG).show()
//                    Log.d("리", t`odoList[position].todo_id.toString())

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


