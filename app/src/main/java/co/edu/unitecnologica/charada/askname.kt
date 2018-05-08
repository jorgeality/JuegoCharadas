package co.edu.unitecnologica.charada

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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


    }


    fun EnviarNombre(){
         val name : String = nombre.text.toString().trim()

        if (name.isEmpty()){
            nombre.error = "ingrese un nick por favor !! "
            return
        }else{
            val ref = FirebaseDatabase.getInstance().getReference("participantes")

            val id_ejugador = ref.push().key
            val datos = "0,0,0"
            val jugador = participante(id_ejugador, name,datos)


            ref.child(id_ejugador).setValue(jugador).addOnCompleteListener {
                Toast.makeText(applicationContext,"Tu nick ha sido guardado",Toast.LENGTH_SHORT).show()
            }
            Handler().postDelayed({
                var intent: Intent = Intent(this, secondscreen::class.java)
                intent.putExtra("nombre", name)
                intent.putExtra("id", id_ejugador)
                startActivity(intent)
            },2000)
        }



    }

}
