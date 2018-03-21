package co.edu.unitecnologica.charada

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.askname.*

class askname : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.askname)
    }

    fun enviar(view: View){


        EnviarNombre()
        var intent: Intent = Intent(this, secondscreen::class.java)
        startActivity(intent)

    }


    fun EnviarNombre(){
         val name : String = nombre.text.toString().trim()

        if (name.isEmpty()){
            nombre.error = "ingrese un nick por favor !! "
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("participantes")

        val id_ejugador = ref.push().key


        val jugador = participante(id_ejugador, name,0)
        Toast.makeText(applicationContext,""+jugador.puntuacion ,Toast.LENGTH_SHORT).show()

        ref.child(id_ejugador).setValue(jugador).addOnCompleteListener {
            Toast.makeText(applicationContext,"Tu nick ha sido guardado",Toast.LENGTH_SHORT).show()

        }

    }
    fun next(){

        var intent: Intent = Intent(this, askname::class.java)
        startActivity(intent)

    }
}
