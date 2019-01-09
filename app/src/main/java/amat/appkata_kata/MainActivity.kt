package amat.appkata_kata

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        LoadData().execute()
        //preLoadRaw()
    }
    inner class LoadData : AsyncTask<Void, Int, Void>  (){

        lateinit var appPreference : AppPreference
        var progress:Double = 0.0
        var maxprogress:Double= 100.0

        override fun onPreExecute() {
            appPreference= AppPreference(this@MainActivity)
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            val firstRun=appPreference.getFirstRun()

            if (firstRun){
                progress = 18.0
                Thread.sleep(2000)
                val listWord : MutableList<Kata> = preLoadRaw()

                publishProgress(progress.toInt())
                val progressMaxInsert = 83.0
                val progressDiff = (progressMaxInsert - progress) / listWord.size

                database.use {
                    this.beginTransaction()

                    try {
                        for (Kata in listWord){
                            insert(Kata)
                            progress +=progressDiff
                            publishProgress(progress.toInt())
                        }
                        this.setTransactionSuccessful()

                    }catch (e:Exception){
                        Log.e("KAYA","background")
                    }
                    this.endTransaction()
                    this.close()

                }

                appPreference.setFirstRun(false)
                publishProgress(maxprogress.toInt())

            }else {
                try {
                    synchronized (this) {

                        Thread.sleep(800)
                        publishProgress(15)
                        Thread.sleep(500)
                        publishProgress(25)

                        Thread.sleep(500)
                        publishProgress(30)


                        Thread.sleep(500)
                        publishProgress(45)

                        Thread.sleep(500)
                        publishProgress(52)
                        Thread.sleep(1000)
                        publishProgress(60)

                        Thread.sleep(1000)
                        publishProgress(75)

                        Thread.sleep(900)
                        publishProgress(85)

                        Thread.sleep(800)
                        publishProgress(maxprogress.toInt())
                    }
                } catch (e:Exception) {
                }
            }



            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            values[0]?.let { progress_bar.progress = it }
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Void?) {
            startActivity(Intent(this@MainActivity, KataActivity::class.java))
            finish()
            super.onPostExecute(result)
        }


    }

    private fun preLoadRaw(): MutableList<Kata> {
        val listWord : MutableList<Kata> = mutableListOf()
        val reader:BufferedReader

        try {
            val res=resources
            val rawKata=res.openRawResource(R.raw.datakata)

            reader = BufferedReader(InputStreamReader(rawKata))
            var count=0
            do {
                val line=reader.readLine()
                val splitstr = line.split("\t")

                val kata = Kata(null,splitstr[0],splitstr[1])
                listWord.add(kata)
                count++
            }while (line!=null)


        }catch (E: Exception){

        }
        return listWord
    }

    private fun insert(kata: Kata){

        database.use {
            val sql="INSERT INTO "+Kata.TABLE_KATA+" ("+Kata.JUDUL_KATA+","+Kata.ISI_KATA+") VALUES (?,?)"
            val stmt = this.compileStatement(sql)
            stmt.bindString(1,kata.judul)
            stmt.bindString(2,kata.isi)
            stmt.execute()
            stmt.clearBindings()
        }

    }
}
