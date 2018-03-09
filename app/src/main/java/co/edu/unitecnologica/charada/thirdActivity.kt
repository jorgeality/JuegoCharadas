package co.edu.unitecnologica.charada

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_third.*

class thirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val bundle: Bundle = intent.extras
        val conta = bundle.get("cont").toString()
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



        gift.setText("eres un jugador tipo: " + premio)
        num.setText(conta+" / 10")

    }
}
