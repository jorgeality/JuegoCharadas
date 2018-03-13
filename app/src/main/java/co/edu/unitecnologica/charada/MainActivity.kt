package co.edu.unitecnologica.charada

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity() {

    val Url: String =  "http://beta.json-generador.com/api/json/get/4k_68iOSm"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            println("hola!!")
        Url.httpGet().responseString { request, response, result ->
            //do something with response
            when (result) {
                is Result.Failure -> {
                    Log.d("E","hola mundo cruel")
                }
                is Result.Success -> {
                    Log.d("E","${result.get()}")
                }
            }
        }

    }

    fun jugar(view: View){

        var intent: Intent = Intent(this, secondscreen::class.java)
        startActivity(intent)

    }

}
