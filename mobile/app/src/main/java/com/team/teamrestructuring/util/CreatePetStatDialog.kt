package com.team.teamrestructuring.util

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.team.teamrestructuring.databinding.DialogCreatePetBinding
import com.team.teamrestructuring.databinding.DialogStatPetBinding
import com.team.teamrestructuring.service.HomeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG="CreatePetStatDialog_지훈"
class CreatePetStatDialog(
    createPetStatDialogDialogInterface:CreatePetDialogInterface
) : DialogFragment(){

    private var _binding: DialogStatPetBinding? = null
    private val binding get() = _binding!!
    private var createPetStatDialogDialogInterface : CreatePetDialogInterface? = null

    init{
        this.createPetStatDialogDialogInterface= createPetStatDialogDialogInterface
    }
    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@CreatePetStatDialog,0.7f,0.3f)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogStatPetBinding.inflate(inflater,container,false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setFullScreen()
        setChart()

        binding.buttonPetStat.setOnClickListener {
            dismiss()
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    interface CreatePetDialogInterface{
        fun onYesButtonClick()
    }

    private fun setChart(){
        val radarChart = binding.radarchatPetStat

        var datas:MutableList<RadarEntry> = mutableListOf()
        datas.add(RadarEntry(ApplicationClass.petData?.petStat?.physical!!.toFloat()))
        datas.add(RadarEntry(ApplicationClass.petData?.petStat?.artistic!!.toFloat()))
        datas.add(RadarEntry(ApplicationClass.petData?.petStat?.intelligent!!.toFloat()))
        datas.add(RadarEntry(ApplicationClass.petData?.petStat?.inactive!!.toFloat()))
        datas.add(RadarEntry(ApplicationClass.petData?.petStat?.energetic!!.toFloat()))
        datas.add(RadarEntry(ApplicationClass.petData?.petStat?.etc!!.toFloat()))


        val dataSet = RadarDataSet(datas,"스탯")
        dataSet.color = Color.MAGENTA

        var data = RadarData()
        data.addDataSet(dataSet)
        val labes:MutableList<String> = mutableListOf()
        labes.add("육체적")
        labes.add("예술적")
        labes.add("지능적")
        labes.add("비활동적")
        labes.add("활동적")
        labes.add("기타")

        val xAxis = radarChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labes)
        radarChart.data = data



    }

    fun Context.dialogFragmentResize(dialogFragment: DialogFragment, width:Float, height:Float){
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if(Build.VERSION.SDK_INT<30){
            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialogFragment.dialog?.window

            val x = (size.x*width).toInt()
            val y = (size.y*height).toInt()
            window?.setLayout(x,y)
        }else{
            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialogFragment.dialog?.window

            val x = (rect.width() * width).toInt()
            val y = (rect.height() * height).toInt()

            window?.setLayout(x,y)

        }
    }



    /**
     * Dialog 생성시 하단 네비게이션 뷰 및 상태바 생성x
     */
    fun setFullScreen(){
        //dialog?.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
        //dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        dialog?.window?.decorView?.systemUiVisibility = flag

        //dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)

    }



}