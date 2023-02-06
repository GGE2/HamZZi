package com.team.teamrestructuring.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.team.teamrestructuring.view.fragments.QuestFragment
import com.team.teamrestructuring.view.fragments.HomeFragment
import com.team.teamrestructuring.view.fragments.MyPageFragment
import com.team.teamrestructuring.view.fragments.TodoFragment

class ViewPagerAdapter(fragment:FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> TodoFragment()
            2 -> QuestFragment()
            else -> MyPageFragment()
        }
    }

}