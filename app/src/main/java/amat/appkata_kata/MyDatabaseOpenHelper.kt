package amat.appkata_kata

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteStatement
import org.jetbrains.anko.db.*


class MyDatabaseOpenHelper (ctx: Context) : ManagedSQLiteOpenHelper(ctx, "katakata.db", null,1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.createTable(
            Kata.TABLE_KATA,true,
            Kata.ID_KATA to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Kata.JUDUL_KATA to TEXT,
            Kata.ISI_KATA to TEXT

        )
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    /*fun beginTransaction(){
        instance?.beginTransaction()
    }

    fun setTransactionSuccess(){
        instance?.setTransactionSuccess()
    }

    fun endTransaction(){
        instance?.endTransaction()
    }
    fun insertTransaction(kata: Kata){
        val sql="INSERT INTO "+Kata.TABLE_KATA+" ("+Kata.JUDUL_KATA+","+Kata.ISI_KATA+") VALUES (?,?)"
        val stmt : SQLiteStatement= instance.compileStatement()

    }*/


}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)