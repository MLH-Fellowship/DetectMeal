package com.project.detectmeal.onboarding.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.detectmeal.R
import kotlinx.android.synthetic.main.fragment_third_onboarding.view.*

class ThirdOnboarding : Fragment() {
    var myview: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myview = inflater.inflate(R.layout.fragment_third_onboarding, container, false)
        myview!!.ThirdIV.visibility = View.INVISIBLE
        return myview
    }


    override fun onResume() {
        super.onResume()
        myview!!.ThirdIV.visibility = View.VISIBLE
        val anim =
            android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.bounce)
        myview?.ThirdIV?.startAnimation(anim)
        Log.d("FragSec", "resume")

    }

    override fun onPause() {
        super.onPause()
        myview!!.ThirdIV.visibility = View.INVISIBLE
        Log.d("FragSec", "pause")

    }

}