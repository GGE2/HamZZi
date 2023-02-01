package com.team.teamrestructuring.view.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.getSystemService
import com.team.teamrestructuring.R
import com.team.teamrestructuring.databinding.FragmentHomeBinding
import com.team.teamrestructuring.view.activities.GuildActivity
import com.team.teamrestructuring.view.activities.HomeActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonHomeFind.setOnClickListener {
            showDialog()
        }
        init()
    }
    private fun init(){
        createIntent()
    }

    /**
     * Intent 생성 모음
     */
    private fun createIntent(){

        /**
         * Guild 버튼 클릭시 GuildActivity로 이동
         */
        binding.buttonHomeGuild.setOnClickListener {
            val intent = Intent(activity,GuildActivity::class.java)
            startActivity(intent)
        }
    }


    /**
     * 친구추가 버튼 클릭시 Dialog 창 생성
     */
    private fun showDialog(){
        var builder = AlertDialog.Builder(context,androidx.appcompat.R.style.AlertDialog_AppCompat)
        var view = LayoutInflater.from(context).inflate(R.layout.dialog_create_find_friend,binding.root.findViewById(R.id.alertdialog_main_findfriend))
        builder.setView(view)

        val alertDialog = builder.create()

        val display = getSystemService(requireContext(),WindowManager::class.java)?.defaultDisplay
        var point = Point()
        display!!.getSize(point)
        var pointWidth = (point.x * 0.9).toInt()
        var pointHeight = (point.y * 0.4).toInt()

        view.findViewById<AppCompatButton>(R.id.button_dialog_cancle).setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        alertDialog.setCancelable(false)
        alertDialog.window!!.attributes.width = pointWidth
        alertDialog.window!!.attributes.height = pointHeight
        alertDialog.show()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}