package co.edu.unitecnologica.charada

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }

    fun jugar(view: View){

        var intent: Intent = Intent(this, secondscreen::class.java)
        startActivity(intent)

    }

}
