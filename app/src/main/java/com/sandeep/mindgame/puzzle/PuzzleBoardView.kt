package com.sandeep.mindgame.puzzle

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.FragmentManager

@SuppressLint("ViewConstructor")
class PuzzleBoardView(context: Context?,val n: Int) : View(context) {

    val paint = Paint()
    var containerWidth = 0
    var size = 0
    val mat = Array(n){Array(n){ PuzzleBlock(context!!, 0, 0F, 0F, 0F) } }
    var emptyBlockIndex = Point(n-1, n-1)


    init {
        paint.isAntiAlias = true
    }
    fun initGame(){
        emptyBlockIndex = Point(n-1, n-1)
        size = containerWidth / n
        var x = 0
        var y = 0
        var ID = 1
        for (element in mat){
            for (j in mat[0].indices){
                element[j] = PuzzleBlock(
                    context,
                    ID,
                    x.toFloat(),
                    y.toFloat(),
                    size.toFloat()
                )
                ID ++
                ID %= n * n
                x += size
            }
            x = 0
            y += size
        }
     shuffleMat()
    }
    fun solution(){
        emptyBlockIndex = Point(n-1, n-1)
        size = containerWidth / n
        var x = 0
        var y = 0
        var ID = 1
        for (element in mat){
            for (j in mat[0].indices){
                element[j] = PuzzleBlock(
                    context,
                    ID,
                    x.toFloat(),
                    y.toFloat(),
                    size.toFloat()
                )
                ID ++
                ID %= n * n
                x += size
            }
            x = 0
            y += size
        }
    }
    private fun shuffleMat(){
        val iter = 100
        for (i in 0 until iter){
            val option = mutableListOf<Point>()
            if (emptyBlockIndex.x + 1 < n){
                option.add(Point(emptyBlockIndex.x + 1, emptyBlockIndex.y))
            }
            if (emptyBlockIndex.x - 1 >= 0){
                option.add(Point(emptyBlockIndex.x - 1, emptyBlockIndex.y ))
            }
            if (emptyBlockIndex.y + 1 < n){
                option.add(Point(emptyBlockIndex.x, emptyBlockIndex.y + 1))
            }
            if (emptyBlockIndex.y - 1 >= 0){
                option.add(Point(emptyBlockIndex.x, emptyBlockIndex.y - 1))
            }
            option.shuffle()
            val selectedIndex = option[0]
            swapMatrix(selectedIndex.x, selectedIndex.y)
        }
    }
    private fun isSolution(): Boolean{
        var c = 1
        for (element in mat){
            for (j in mat[0].indices){
                if (element[j].ID != c && c != n * n){
                    return false
                }
                c ++
            }
        }
        return true
    }

    private fun swapMatrix(i: Int, j: Int){
        val ID = mat[i][j].ID
        mat[i][j].ID = 0
        mat[emptyBlockIndex.x][emptyBlockIndex.y].ID = ID
        emptyBlockIndex = Point(i, j)
    }
    private fun makeMove(i: Int, j: Int){
        swapMatrix(i, j)
        invalidate()
        if (isSolution()){
            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setTitle("Congrats!")
            alertDialog.setCancelable(false)
            alertDialog.setMessage("Do you want to play again?")
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"YES"){
                dialog, _ ->
                run {
                    initGame()
                    invalidate()
                    dialog.dismiss()
                }
            }
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No"){
                dialog, _ ->  run {
                (context as MainActivity).fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                dialog.dismiss()
            }
            }
            alertDialog.show()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (element in mat){
            for (j in mat[0].indices){
                element[j].onDraw(canvas!!, paint)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        containerWidth = measuredWidth
        if (containerWidth == 0){
            return
        }
        initGame()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action){
            MotionEvent.ACTION_DOWN -> {
                Log.d("event", event.x.toString() + ":" + event.y.toString())
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (size == 0){
                    return false
                }
                val i = (event.y / size).toInt()
                val j = (event.x / size).toInt()

                if (i + 1 < n && i + 1 == emptyBlockIndex.x && j == emptyBlockIndex.y){
                    makeMove(i,j)
                }else if (i - 1 >= 0 && i - 1 == emptyBlockIndex.x && j == emptyBlockIndex.y){
                    makeMove(i, j)
                }else if (j + 1 < n && i == emptyBlockIndex.x && j + 1 == emptyBlockIndex.y){
                    makeMove(i, j)
                }else if (j - 1 >= 0 && i == emptyBlockIndex.x && j - 1 == emptyBlockIndex.y){
                    makeMove(i, j)
                }
                Log.d("event", "${event.x} : ${event.y}")
            }
        }
        return super.onTouchEvent(event)
    }
}
