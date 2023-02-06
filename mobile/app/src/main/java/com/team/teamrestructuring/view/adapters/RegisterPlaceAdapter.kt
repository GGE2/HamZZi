package com.team.teamrestructuring.view.adapters

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.team.teamrestructuring.databinding.ItemRegisterPlaceBinding
import com.team.teamrestructuring.dto.Place

class RegisterPlaceAdapter(var datas:List<Place>) : RecyclerView.Adapter<RegisterPlaceAdapter.RegisterPlaceViewHolder>(){
    private lateinit var registerClickListener:OnRegisterClickListener
    inner class RegisterPlaceViewHolder(var binding:ItemRegisterPlaceBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindData(data:Place){
            binding.itemRegisterPlaceName.text= data.place_name
            binding.itemRegisterPlaceAddress.text = data.address_name

            binding.itemRegisterPlaceApply.setOnClickListener {
                registerClickListener.onRegisterClickListener(it,layoutPosition,data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegisterPlaceViewHolder {
        val binding = ItemRegisterPlaceBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RegisterPlaceViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RegisterPlaceViewHolder, position: Int) {

        holder.bindData(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    interface OnRegisterClickListener{
        fun onRegisterClickListener(view: View, position: Int,data: Place)
    }
    fun setOnRoomClickListener(registerClickListener:OnRegisterClickListener){
        this.registerClickListener = registerClickListener
    }

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
}