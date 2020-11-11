package com.project.socialdistance.onboarding

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.animation.AnimationUtils
import com.project.socialdistance.R
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import kotlinx.android.synthetic.main.fragment_splash_screen.view.*

class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)
        val anim =
            android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.fadein)
        view.splashImage.startAnimation(anim)

        Handler().postDelayed({
            if (restorePref(requireContext())) {
                findNavController().navigate(R.id.action_splashScreen_to_detectFragment)
            } else {
                findNavController().navigate(R.id.action_splashScreen_to_viewPagerFragment)
            }

        }, 3000)
        // Inflate the layout for this fragment


        return view
    }

}