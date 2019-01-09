package amat.appkata_kata

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class AppPreference {


    private var preference:SharedPreferences
    private lateinit var a:String
    private var context:Context


    constructor(applicationContext: Context?){
        preference = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        this.context = applicationContext!!
    }

    fun setFirstRun(input : Boolean){
        val editor  = preference.edit()
        val key =context.resources.getString(R.string.app_first_run)
        editor.putBoolean(key,input)
        editor.commit()
    }

    fun getFirstRun():Boolean{
        return preference.getBoolean(context.resources.getString(R.string.app_first_run),true)
    }
}