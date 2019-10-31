package com.sandeep.mindgame.memo.datalayer

import android.content.Context
import com.sandeep.mindgame.memo.datalayer.model.PlayDto
import kotlin.random.Random

object GenImageData {
    fun getImage(gameType: Int, gameSize: Int, context: Context): List<PlayDto> {
        val numlist: ArrayList<Int> = ArrayList(gameSize)
        var imageList: ArrayList<PlayDto> = ArrayList()
        var control: Boolean
        var i = 0
        while (i < (gameSize / 2)) {
            control = true
            val randomNum = Random.nextInt(1, 30)
            for (l in 0 until numlist.size) {
                if (numlist[l] == randomNum) {
                    control = false
                    break
                }
            }
            if (control) {
                numlist.add(randomNum)
                i += 1
            }
        }
        when (gameType) {
            GameTypeEnum.ANIMAL.value -> {
                imageList = getImageList("animal", numlist, context)
            }
            GameTypeEnum.PLANT.value -> {
                imageList = getImageList("plant", numlist, context)
            }
            GameTypeEnum.SHAPE.value -> {
                imageList = getImageList("Shape", numlist, context)
            }
            /*GameTypeEnum.OBJECT.value -> {
                imageList = getImageList("object", numlist, context)
                }

             */

        }
        return imageList
    }

    private fun getImageList(typeGame: String, numberList: ArrayList<Int>, context: Context): ArrayList<PlayDto>{
        val imgList: ArrayList<PlayDto> = ArrayList()
        for (i in 0 until numberList.size){
            val imgName = typeGame + numberList[i]
            val playImg = PlayDto(false, context.resources.getIdentifier(imgName, "drawable", "com.sandeep.mindgame"))
            val playImg2 = PlayDto(false, context.resources.getIdentifier(imgName, "drawable", "com.sandeep.mindgame"))
            imgList.add(playImg)
            imgList.add(playImg2)
        }
        return imgList
    }
}