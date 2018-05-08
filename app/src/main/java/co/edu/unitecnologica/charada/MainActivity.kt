package co.edu.unitecnologica.charada

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.util.Log
import android.util.Log.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.askname.*

class MainActivity : AppCompatActivity() {



   

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        list.setOnClickListener {

            var intent: Intent = Intent(this, ListPlayer::class.java)
            startActivity(intent)

        }


        /*val p = ""
        val ps: List<String> = p.split(",")
       tal.text = (ps[0]+"\n"+ps[1]+"\n"+ps[2]+"\n")
*/
    }


    fun jugar(view: View){
        Log.e("hola","estamos pasando a la segunda actividad\n")
        var intent: Intent = Intent(this, askname::class.java)
        startActivity(intent)

    }

}


