package co.edu.unitecnologica.charada

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_third.*

class thirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val bundle: Bundle = intent.extras
        val conta = bundle.get("cont").toString()
        val cant = bundle.get("cant").toString()
        val time = bundle.get("time").toString()
        var tiempo:Int = 60 - time.toInt()
        val j = cant.toInt()
        val cont: Int = conta.toInt()
        var premio: String = ""
        if(cont >= 8 && cont <=10){
            premio  = "oro"
            gifts.setImageResource(R.drawable.gold)
           
        }else if (cont >=5 && cont <= 7){
            premio  = "plata"
            gifts.setImageResource(R.drawable.silver)
        }else if(cont >=3 && cont <= 4){
            premio  = "bronce"
            gifts.setImageResource(R.drawable.brass)
        }else{
            premio = "perdedor"
            gifts.setImageResource(R.drawable.loser)
        }

        if(tiempo == 0){
            tiempo = 60
        }


        gift.setText("eres un jugador tipo: " + premio)

        resumen.setText("PALABRAS VISTAS:     "+cant.toString()+"\n"+
                        "PALABRAS IGNORADAS: "+(j-cont).toString()+"\n"+
                        "PALABRAS ACERTADAS: "+cont.toString()+"\n"+
                        "TIEMPO OCUPADO:      " +tiempo.toString())

        again.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}
