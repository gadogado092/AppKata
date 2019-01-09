package amat.appkata_kata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView

class KataAdapter (private val context: Context, private val room : List <Kata>, private val listener: (Kata) -> Unit)
    : RecyclerView.Adapter<KataAdapter.NextViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = NextViewHolder(
        LayoutInflater.from(context).inflate(R.layout.kata_adapter, parent, false
    )
    )

    override fun getItemCount(): Int = room.size

    override fun onBindViewHolder(holder: NextViewHolder, position: Int) {
        //if (holder.name.toString() != "Kosong"){
        holder.bindItem(room[position], listener)

        //}

    }


    class NextViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name= view.findViewById<TextView>(R.id.name)


        fun bindItem(kata: Kata, listener: (Kata) -> Unit){

            name.text=kata.judul



            /* val layout= RelativeLayout(itemView.context)
             val relativeParams= RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)

             val tv = TextView(itemView.context)
             tv.text = "TES"
             relativeParams.addRule(RelativeLayout.BELOW, tv.id)
             //layout.addView(tv,relativeParams)*/

            itemView.setOnClickListener {
                listener(kata)
            }

        }
    }


}