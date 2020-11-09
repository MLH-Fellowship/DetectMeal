package com.project.detectmeal.onboarding

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.project.detectmeal.R
import com.project.detectmeal.onboarding.screens.FirstOnboarding
import com.project.detectmeal.onboarding.screens.SecondOnboarding
import com.project.detectmeal.onboarding.screens.ThirdOnboarding
import kotlinx.android.synthetic.main.fragment_view_pager.view.*


class ViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onboardingSharedPref(requireContext())
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val tabIndicator = view.findViewById<TabLayout>(R.id.tab_indicator)
        val nextTv = view.findViewById<TextView>(R.id.next_tv)

        val fragmentList = arrayListOf<Fragment>(
            FirstOnboarding(),
            SecondOnboarding(),
            ThirdOnboarding()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = adapter
        tabIndicator.setupWithViewPager(view.viewPager)
        var position = view.viewPager.currentItem

        nextTv.setOnClickListener {
            if (position < fragmentList.size) {
                position++
                view.viewPager.currentItem = position

            }

            if (position == fragmentList.size) {
                findNavController().navigate(R.id.action_viewPagerFragment_to_detectFragment)
            }

        }

        tabIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                position = p0?.position!!
                if (p0.position == fragmentList.size - 1) {
                    nextTv.text = "Get started"
                } else {
                    nextTv.text = "Next"
                }

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })



        return view
    }


}