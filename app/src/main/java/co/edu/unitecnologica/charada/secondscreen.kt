package co.edu.unitecnologica.charada

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils

import kotlinx.android.synthetic.main.activity_secondscreen.*
import java.util.concurrent.ThreadLocalRandom

class secondscreen : AppCompatActivity() {
    var i: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondscreen)

        val asc = arrayOf("hola","caminar","correr","comer","dormir","saltar","escribir","manejar","nadar","volar")



        sgt.setOnClickListener {
            palabras.setText(asc[rand(0,9)])
            i +=1
            cont.setText(i.toString())
            if( i == 10){
                next()

            }
        }
        cronometro()


    }
    fun rand(inicio: Int, fin: Int): Int {

        return ThreadLocalRandom.current().nextInt(inicio, fin)
    }
    fun next(){
        var intent: Intent = Intent(this, thirdActivity::class.java)
        intent.putExtra("cont", i.toString())
        startActivity(intent)
    }
    fun cronometro(){

        val timer = object : CountDownTimer(60 * DateUtils.SECOND_IN_MILLIS,DateUtils.SECOND_IN_MILLIS){
            override fun onFinish() {
                next()
            }

            override fun onTick(millisUntilFinished: Long) {
               Cronometro.text = "${millisUntilFinished/1000}"
            }
        }
        timer.start()

    }
}
