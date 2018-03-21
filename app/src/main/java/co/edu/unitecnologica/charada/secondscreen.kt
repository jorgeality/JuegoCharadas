package co.edu.unitecnologica.charada

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateUtils

import kotlinx.android.synthetic.main.activity_secondscreen.*
import java.util.concurrent.ThreadLocalRandom

class secondscreen : AppCompatActivity(), SensorEventListener {
    var i: Int = 0
    val asc = arrayOf("hola","caminar","correr","comer","dormir","saltar","escribir","manejar","nadar","volar")
    lateinit var sensorManager: SensorManager
    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.values[0] <= 6){

            palabras.setText(asc[rand(0,9)])
            i +=1
            cont.setText(i.toString())
            if( i == 10){
                next()

            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondscreen)




        sgt.setOnClickListener {
            palabras.setText(asc[rand(0,9)])
            i +=1
            cont.setText(i.toString())
            if( i == 10){
                next()

            }
        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL
        )
        cronometro()




    }

    /*
    * la funcion  rand nos devuelve las palabras de manera aleatoria.
    * */
    fun rand(inicio: Int, fin: Int): Int {

        return ThreadLocalRandom.current().nextInt(inicio, fin)
    }
    /*
    * funcion next, hace el vinculo con la siguiente actividad.
    * */
    fun next(){
        var intent: Intent = Intent(this, thirdActivity::class.java)
        intent.putExtra("cont", i.toString())
        startActivity(intent)
    }

    /*
    * la funcion cronometro, es la que lleva el conteo regresivo de la aplicacion.
    *
    * */
    fun cronometro(){
            /*
            * declaramos una variable de tipo CountDown Timer la cual recibe dos parametros para saber cuantos son los segundos que debe correr
            * en este caso regresivamente
            *
            * */
        val timer = object : CountDownTimer(60 * DateUtils.SECOND_IN_MILLIS,DateUtils.SECOND_IN_MILLIS){
                /**
                 * la funcion onfinish se cumple al momento de terminar el conteo
                 * */
            override fun onFinish() {
                next()
            }
            /**
             * la funcion ontick se cumple mientras el conteo esta andando
             *
             **/

            override fun onTick(millisUntilFinished: Long) {
               Cronometro.text = "${millisUntilFinished/1000}"
            }
        }
        timer.start()

    }
}
