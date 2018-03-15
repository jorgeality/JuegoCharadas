package co.edu.unitecnologica.charada

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class askname : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.askname)
    }

    fun enviar(view: View){

        var intent: Intent = Intent(this, secondscreen::class.java)
        startActivity(intent)

    }
}
