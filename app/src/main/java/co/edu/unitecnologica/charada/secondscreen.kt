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
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_secondscreen.*
import java.util.concurrent.ThreadLocalRandom

class secondscreen : AppCompatActivity(), SensorEventListener {
    var i: Int = 0
    //val asc = arrayOf("hola","caminar","correr","comer","dormir","saltar","escribir","manejar","nadar","volar")
    var cambiar: Boolean = true
    var cambiar2: Boolean = true
    var j:Int = 0
    lateinit var sensorManager: SensorManager
    lateinit var ref: DatabaseReference
    var time: Int=0
    var id_player: String = ""
    var name_player: String = ""
    lateinit var ListPalabras: MutableList<Palabra>
    override fun onSensorChanged(event: SensorEvent?) {


        if (event!!.values[2] >=4  && cambiar ==true){
            var ramdon:Int =rand(0,ListPalabras.size-1)
            palabras.setText(ListPalabras[rand(0,ListPalabras.size-1)].palabra)
            //palabras.setText(asc[rand(0,9)])
            j+=1
            i +=1
            if( i == 10){
                next()

            }
            cambiar = false
            cont.setText("Acertadas: "+i.toString()+"\t\tIgnoradas: "+(j-i).toString()+"\t")
        }else if(event!!.values[2] <=4  && cambiar ==false){


            cambiar = true

        }
        if(event!!.values[2] <=-1  && cambiar2 ==true){
            palabras.setText(ListPalabras[rand(0,ListPalabras.size)].palabra)
           // palabras.setText(asc[rand(0,9)])
            j+=1
            cambiar2 = false
            cont.setText("Acertadas: "+i.toString()+"\t\tIgnoradas: "+(j-i).toString()+"\t")

        }else if(event!!.values[2] >=-1  && cambiar2 ==false){

            cambiar2 = true
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondscreen)
        val bundle: Bundle = intent.extras
        id_player = bundle.get("id").toString()
        name_player = bundle.get("nombre").toString()
        ListPalabras = mutableListOf()

        ref = FirebaseDatabase.getInstance().getReference("palabras")
        LeerDB()

        cont.setText("Acertadas: "+i.toString()+"\t\tIgnoradas: "+(j-i).toString()+"\t")


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

        /**
         * actualizando datos en firebase
         *
         *
         * */
        val DBPlayer = FirebaseDatabase.getInstance().getReference("participantes")
        val tiempo = 60 - time
        val ignoradas = j -i
        val acertadas = i
        val datos = tiempo.toString()+","+ignoradas.toString()+","+acertadas.toString()
        val participante = participante(id_player,name_player,datos)
        DBPlayer.child(participante.id).setValue(participante)


        var intent: Intent = Intent(this, thirdActivity::class.java)
        intent.putExtra("cont", i.toString())
        intent.putExtra("cant",j.toString())
        intent.putExtra("time", time.toString())
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
                time = (millisUntilFinished / 1000).toInt()
                tiempo.text = "${millisUntilFinished/1000}"

            }
        }
        timer.start()

    }

    fun LeerDB(){

        ref.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError?) {}
            override fun onDataChange(p0: DataSnapshot?) {
                //en esta parte va todo lo que tiene que hacer al momento de leer
                if(p0!!.exists()){

                    for (p in p0.children){
                        val paa = p.getValue(Palabra::class.java)
                        ListPalabras.add(paa!!)
                        println(paa.palabra)
                    }
                }
            }
        })
    }

}
/**
 * 1. dar click en debug app.
 * 2. puntos de interrupcion: son aquellos puntos que te permiten detener la app en cierto punto. y te muestra informacion de cierta variable o objeto en el app.
 * 3.
 *
 *
 *
 * */