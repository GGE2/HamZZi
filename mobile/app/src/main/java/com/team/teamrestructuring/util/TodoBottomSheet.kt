package com.team.teamrestructuring.util
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.team.teamrestructuring.R
import com.team.teamrestructuring.dto.Todo
import com.team.teamrestructuring.service.TodoService
import com.team.teamrestructuring.view.fragments.TodoFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

private const val TAG = "청천"
private val nickName = ApplicationClass.currentUser.userProfile.nickname.toString()
class TodoBottomSheet(
    callbackInterface : SetOnModifyButtonInterface?,
    todo: Todo?,
    dateStr: String?,
    position: Int?,
    todoList: MutableList<Todo>,

): BottomSheetDialogFragment() {

    private var callbackInterface : SetOnModifyButtonInterface? = null
    private var todo:Todo?=null
    private var dateStr: String?= null
    private var position: Int?=null
    private var todoList: MutableList<Todo> = mutableListOf()
    init{
        this.callbackInterface = callbackInterface
        this.todo = todo
        this.dateStr = dateStr
        this.position = position
        this.todoList = todoList
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.todobottomdialog, container, false)
    }

    // 비동기 동기화 맞추기 위해 로컬 동기화 하는 함수
    private fun localChange(){
        TodoFragment.todoAdapter.items = todoList
        TodoFragment.todoAdapter.notifyDataSetChanged()
    }

    //  함수
    // 콜백 함수를 만들어 처리해야한다
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 수정 버튼
        view?.findViewById<Button>(R.id.modifyBottom)?.setOnClickListener {
            Log.d(TAG, "함수 오나")
            callbackInterface?.onButtonClick()
            todo?.content = view?.findViewById<EditText>(R.id.modifyTextBottom)?.text.toString()
            todo?.content = todo?.content.toString().trim()
            if (todo?.content!!.isEmpty()){
                Toast.makeText(requireContext(), "수정 할 투두를 입력 해 주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            modifyTodoService(todo?.todo_id!!, todo!!)
            todoList[position!!] = todo!!
            localChange()
            dismiss()

        }
        // 삭제 버튼
        view?.findViewById<Button>(R.id.deleteBottomButtom)?.setOnClickListener {
            callbackInterface?.onButtonClick()
            deleteTodoService(todo?.todo_id!!)
            todoList.removeAt(position!!)
            localChange()
            dismiss()
        }

    }
    interface SetOnModifyButtonInterface{
        fun onButtonClick()
    }


    // 투두를 수정하는
    private fun modifyTodoService(id: Int, Todo: Todo){
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .modifyTodo(todo?.todo_id!!,todo!!).enqueue(object:Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                    Log.i(TAG, "수정에 성공했습니다. ${response.body()}")

                    }

                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.w(TAG, "onFailure: ${t.message}")
                }
            })
    }


    // 투두를 삭제하는
    private fun deleteTodoService(id: Int){
        val service = ApplicationClass.retrofit.create(TodoService:: class.java)
            .deleteTodo(id).enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful){
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                }
            })
    }
}