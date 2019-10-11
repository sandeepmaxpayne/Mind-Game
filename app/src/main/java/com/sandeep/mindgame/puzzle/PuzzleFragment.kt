package com.sandeep.mindgame.puzzle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sandeep.mindgame.R
import kotlinx.android.synthetic.main.fragment_puzzle.*

class PuzzleFragment: Fragment() {
    private val puzzTag = "PuzzleFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_puzzle, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mainActivity = activity as MainActivity
        val puzzleBoardView = PuzzleBoardView(context, mainActivity.n)
        puzzle_container.addView(puzzleBoardView)

        new_game.setOnClickListener {
            puzzleBoardView.initGame()
            puzzleBoardView.invalidate()
        }
        solve.setOnClickListener {
           puzzleBoardView.solution()
            puzzleBoardView.invalidate()
        }

    }

}
