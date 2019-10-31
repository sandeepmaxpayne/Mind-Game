package com.sandeep.mindgame.memo

import android.content.Context
import android.content.Intent
import com.sandeep.mindgame.memo.ui.BaseActivity
import com.sandeep.mindgame.puzzle.MainActivity

class Router{

   fun switchPage(activity: BaseActivity, activity1: BaseActivity){
       activity.startActivity(Intent(activity, activity1::class.java))
   }
    fun switchPage(context: Context?, activity1: BaseActivity){
        val i = Intent(context, activity1::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(i)
    }
    fun switchPage(context: Context?, activity1: BaseActivity, itemKey: String, item: String){
        val i = Intent(context,activity1::class.java)
        i.putExtra(itemKey, item)

        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(i)
    }
    fun switchPage(context: Context?, activity1: BaseActivity, itemKey: String, item: Int){
        val i = Intent(context,activity1::class.java)
        i.putExtra(itemKey, item)

        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(i)
    }
    fun switchPage(context: Context?, activity1: BaseActivity, itemKey: String, item: Long){
        val i = Intent(context,activity1::class.java)
        i.putExtra(itemKey, item)

        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(i)
    }
    fun switchPage(context: Context?, activity1: BaseActivity, itemKey: String, item: Boolean){
        val i = Intent(context,activity1::class.java)
        i.putExtra(itemKey, item)

        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(i)
    }

    fun switchPage(context: Context?, activity1: BaseActivity, itemKey: String, item: String, itemKey1: String, item1: String){
        val i = Intent(context, activity1::class.java)
        i.putExtra(itemKey, item)
        i.putExtra(itemKey1, item1)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(i)
    }
    fun switchPage(context: Context?, activity1: BaseActivity, itemKey: String, item: Int, itemKey1: String, item1: Int){
        val i = Intent(context, activity1::class.java)
        i.putExtra(itemKey, item)
        i.putExtra(itemKey1, item1)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(i)
    }

    fun switchPage(context: Context?, activity1: BaseActivity, itemKey: String, item: Int, itemKey1: String, item1: Int, showKeyboard: Boolean){
        val i = Intent(context, activity1::class.java)
        i.putExtra(itemKey, item)
        i.putExtra(itemKey1,item1)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context?.startActivity(i)
    }



}