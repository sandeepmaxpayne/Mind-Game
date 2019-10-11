package com.sandeep.mindgame.puzzle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.sandeep.mindgame.R

open class PuzzleBlock(private val context: Context, var ID: Int, var x: Float, var y: Float, var size: Float) {
    private val textsize = 25
    private val strokeWidth = 3

    open fun onDraw(canvas: Canvas, paint: Paint) {
        if (ID == 0) {
            paint.style = Paint.Style.FILL
            paint.color = ContextCompat.getColor(context,
                R.color.gameBackground
            )
            //canvas.drawFilter
            canvas.drawRect(x, y, x + size, y + size, paint)
            return
        }
        //fill
        paint.style = Paint.Style.FILL
        paint.color = ContextCompat.getColor(context, R.color.puzzleColor)
        canvas.drawRect(x, y, x + size, y + size, paint)

        //text
        paint.textSize = textsize * context.resources.displayMetrics.scaledDensity
        paint.textAlign = Paint.Align.CENTER
        paint.color = ContextCompat.getColor(context, R.color.textColor)
        canvas.drawText(
            ID.toString(),
            x + size / 2,
            y + size / 2 - ((paint.descent() + paint.ascent()) / 2),
            paint
        )

        //border
        paint.style = Paint.Style.STROKE
        paint.color = ContextCompat.getColor(context, R.color.gameBackground)
        paint.strokeWidth = strokeWidth * context.resources.displayMetrics.scaledDensity
        canvas.drawRect(x, y, x + size, y + size, paint)
    }
}