package com.team.teamrestructuring.view.activities

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.ActivityRegisterPlaceBinding
import com.team.teamrestructuring.dto.Place
import com.team.teamrestructuring.dto.ResultSearchPlace
import com.team.teamrestructuring.service.LoginService
import com.team.teamrestructuring.service.MyPageService
import com.team.teamrestructuring.util.CreateConfirmDialog
import com.team.teamrestructuring.view.adapters.RegisterPlaceAdapter
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "Register_지훈"
class RegisterPlaceActivity : AppCompatActivity() , CreateConfirmDialog.CreateConfirmListener{
    private lateinit var binding:ActivityRegisterPlaceBinding
    private lateinit var radapter:RegisterPlaceAdapter

    companion object{
        const val BASE_URL = "https://dapi.kakao.com/"
        const val API_KEY ="KakaoAK ee31ee0a2e88cca397f0fded9f02b392"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        radapter = RegisterPlaceAdapter(emptyList())
        radapter.setOnRoomClickListener(object : RegisterPlaceAdapter.OnRegisterClickListener{
            override fun onRegisterClickListener(view: View, position: Int, data: Place) {
                val dialog = CreateConfirmDialog(this@RegisterPlaceActivity,data)
                dialog?.show(supportFragmentManager,"ConfirmDialog")
            }

        })
        init()
        binding.buttonRegisterPlace.setOnClickListener {
            searchPlace(binding.edittextRegisterPlace.text.toString())
        }
    }

    private fun init(){
        setAdapter()
    }
    private fun setAdapter(){
        binding.recyclerviewRegisterPlace.apply{
            adapter = radapter
            layoutManager = LinearLayoutManager(this@RegisterPlaceActivity,LinearLayoutManager.VERTICAL,false)
            addItemDecoration(VerticalSpaceItemDecoration(20))
        }
    }
    //RecyclerView 간격 조절
    inner class VerticalSpaceItemDecoration(private val verticalSpaceHeight:Int):
        RecyclerView.ItemDecoration(){
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.bottom = verticalSpaceHeight
        }
    }

    private fun searchPlace(place:String){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MyPageService::class.java)
            .getSearchKeyword(API_KEY,place).enqueue(object:Callback<ResultSearchPlace>{
                override fun onResponse(
                    call: Call<ResultSearchPlace>,
                    response: Response<ResultSearchPlace>
                ) {
                   if(response.isSuccessful){
                       Log.d(TAG, "onResponse: ${response.body()!!}")
                       radapter.datas = response.body()!!.documents
                       radapter.notifyDataSetChanged()
                   }
                }

                override fun onFailure(call: Call<ResultSearchPlace>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })
    }

    override fun onYesButtonClick() {
        finish()
    }

}