package amat.appkata_kata

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync

class KataActivity : Activity() {

    private lateinit var rvKata: RecyclerView
    private lateinit var adapter: KataAdapter
    private var listKata : MutableList<Kata> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kata)

        adapter= KataAdapter(applicationContext,listKata){
            val intent = Intent(applicationContext, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("selected", it)
            intent.putExtra("myBundle", bundle)
            startActivity(intent)
        }

        rvKata= findViewById(R.id.list)
        rvKata.layoutManager = LinearLayoutManager(applicationContext)
        rvKata.setHasFixedSize(true)
        rvKata.adapter=adapter

        selectData()
    }

    private fun selectData() {

        doAsync {

            database.use {
                listKata.clear()

                val query=
                    "SELECT "+ Kata.ID_KATA+","+
                            Kata.JUDUL_KATA+","+
                            Kata.ISI_KATA+" FROM "+Kata.TABLE_KATA+" ORDER BY "+Kata.JUDUL_KATA
                val cursor = database.writableDatabase.rawQuery(query, null)
                while (cursor.moveToNext()) {
                    val kata= Kata(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2)
                        )
                    listKata.add(kata)
                }
            }

            adapter.notifyDataSetChanged()
        }
    }


}
