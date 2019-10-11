package com.sandeep.mindgame.puzzle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sandeep.mindgame.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(){

    private val logtag = "MainFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main,container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val mainActivity = activity as MainActivity
        button_two.setOnClickListener {
            mainActivity.showPuzzleFragment(2)
        }
        button_three.setOnClickListener {
            mainActivity.showPuzzleFragment(3)
        }
        button_four.setOnClickListener {
            mainActivity.showPuzzleFragment(4)
        }
        button_five.setOnClickListener {
            mainActivity.showPuzzleFragment(5)
        }
    }
}