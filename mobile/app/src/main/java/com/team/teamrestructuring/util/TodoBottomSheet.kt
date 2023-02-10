package com.team.teamrestructuring.util
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
private const val nickName = "고틀린"
class TodoBottomSheet(
    callbackInterface : SetOnModifyButtonInterface?,
    todo: Todo?,
    dateStr: String?

): BottomSheetDialogFragment() {

    private var callbackInterface : SetOnModifyButtonInterface? = null
    private var todo:Todo?=null
    private var dateStr: String?= null
    init{
        this.callbackInterface = callbackInterface
        this.todo = todo
        this.dateStr = dateStr
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

//        view?.findViewById<Button>(R.id.modifyBottom)?.setOnClickListener{
//            Log.d(TAG, "함수 오나")
//            callbackInterface?.onButtonClick()
//            todo?.content = view?.findViewById<EditText>(R.id.modifyTextBottom)?.text.toString()
//            modifyTodoService(todo?.todo_id!!,todo!!)
//            dismiss()
//        }
        // 삭제 버튼
        view?.findViewById<AppCompatButton>(R.id.deleteBottomButtom)?.setOnClickListener {

            dismiss()
        }


        return inflater.inflate(R.layout.todobottomdialog, container, false)
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

            modifyTodoService(todo?.todo_id!!,todo!!)

            dismiss()

        }
        // 삭제 버튼
        view?.findViewById<Button>(R.id.deleteBottomButtom)?.setOnClickListener {
            callbackInterface?.onButtonClick()
            deleteTodoService(todo?.todo_id!!)
            dismiss()
        }

//        // 확인 버튼
//        view?.findViewById<Button>(R.id.checkBottombuttom)?.setOnClickListener {
//            dismiss()
//        }
    }
    interface SetOnModifyButtonInterface{
        fun onButtonClick()
    }
    // 투두를 불러오는
    private fun callService(nickName: String, dateTime: String) {
//        Log.d(TAG, "확인")
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .getTodo(nickName, dateTime).enqueue(object : Callback<MutableList<Todo>>{
                override fun onResponse(
                    call: Call<MutableList<Todo>>,
                    response: Response<MutableList<Todo>>
                ) {
                    if(response.isSuccessful){
                        TodoFragment.todoAdapter.items = response.body()!!
                    }
                }

                override fun onFailure(call: Call<MutableList<Todo>>, t: Throwable) {
                    Log.d(TAG, "${t.message}")
                }

            })
    }
    // 투두를 수정하는
    private fun modifyTodoService(id: Long, Todo: Todo){
        Log.d(TAG, todo.toString())
        val service = ApplicationClass.retrofit.create(TodoService::class.java)
            .modifyTodo(todo?.todo_id!!,todo!!).enqueue(object:Callback<Todo>{
                override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                    if(response.isSuccessful){
                        callService(nickName, dateStr!!)
                        TodoFragment.todoAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<Todo>, t: Throwable) {
                    Log.w(TAG, "onFailure: ${t.message}")
                }


            })
    }


    // 투두를 삭제하는
    private fun deleteTodoService(id: Long){
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