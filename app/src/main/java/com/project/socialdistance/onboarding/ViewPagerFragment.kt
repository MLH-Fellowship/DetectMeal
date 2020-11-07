package com.project.socialdistance.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.socialdistance.R
import com.project.socialdistance.onboarding.screens.FirstOnboarding
import com.project.socialdistance.onboarding.screens.SecondOnboarding
import com.project.socialdistance.onboarding.screens.ThirdOnboarding
import kotlinx.android.synthetic.main.fragment_view_pager.view.*


class ViewPagerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

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


        return view
    }

}