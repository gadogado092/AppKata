package amat.appkata_kata

import android.os.Bundle
import android.app.Activity
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : Activity() {

    private lateinit var kata: Kata

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bundle = intent.getBundleExtra("myBundle")

        if (bundle!=null){
            kata = bundle.getParcelable("selected") as Kata

            textView.text="\t\t\t\t\t\t"+kata.isi
        }
    }

}
