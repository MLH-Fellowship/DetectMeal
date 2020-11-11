package com.project.socialdistance.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.socialdistance.R
import kotlinx.android.synthetic.main.fragment_first_onboarding.view.*
import kotlinx.android.synthetic.main.fragment_splash_screen.view.*

class FirstOnboarding : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first_onboarding, container, false)

        val anim =
            android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.bounce)
        view.firstIV.startAnimation(anim)
        // Inflate the layout for this fragment
        return view
    }

}