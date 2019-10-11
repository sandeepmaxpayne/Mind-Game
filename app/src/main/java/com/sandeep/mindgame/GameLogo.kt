package com.sandeep.mindgame

import android.animation.AnimatorInflater
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.sandeep.mindgame.puzzle.MainActivity
import kotlinx.android.synthetic.main.activity_game_logo.*
import java.lang.Exception
import java.lang.reflect.Executable

class GameLogo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_game_logo)
        supportActionBar?.hide()
        val timeThread = object : Thread(){
            override fun run() {
                try {
                    sleep(4000)
                    val start = Intent(this@GameLogo, MainActivity::class.java)
                    startActivity(start)
                    finish()
                }catch (ex: Exception){
                    ex.printStackTrace()
                }
            }
        }
        timeThread.start()
        logoAnim()
    }
    private fun logoAnim(){
        val rotate = AnimatorInflater.loadAnimator(this@GameLogo, R.animator.rotate_anim)
        rotate.apply {
            setTarget(gameLogo)
            start()
        }
    }
}
