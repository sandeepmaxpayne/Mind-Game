package com.sandeep.mindgame.puzzle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.sandeep.mindgame.R

open class MainActivity : AppCompatActivity() {

    val tag = "MainFragment"
    val fragmentManager: FragmentManager = supportFragmentManager
    var n = 3 // size of the puzzle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMainFragment()

    }
    private fun showMainFragment(){
        val transaction = fragmentManager.beginTransaction()
        val fragment = MainFragment()
        transaction.replace(R.id.frame_holder, fragment)
        transaction.commit()

    }
    open fun showPuzzleFragment(newGrid: Int){
        n = newGrid
        val transaction = fragmentManager.beginTransaction()
        val fragment = PuzzleFragment()
        transaction.replace(R.id.frame_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
