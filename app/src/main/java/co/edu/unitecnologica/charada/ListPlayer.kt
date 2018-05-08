package co.edu.unitecnologica.charada

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_player.*
import kotlinx.android.synthetic.main.activity_main.*
class ListPlayer : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_player)



        val listview = findViewById<ListView>(R.id.main_list)
        /*val color = Color.parseColor("#FF0000")
        listview.setBackgroundColor(color)*/
        //main_list.adapter= AdapterList(this)
        listview.adapter = AdapterList(this)

    }




    private class AdapterList(context: Context): BaseAdapter() {

        lateinit var ref: DatabaseReference
        lateinit var Listplayer: MutableList<participante>
        private val mContext: Context

        init {

            Listplayer = mutableListOf()
            ref = FirebaseDatabase.getInstance().getReference("participantes")
            Listplayer = LeerDB()
            println("tamano: "+Listplayer.size)
            mContext = context

        }

        fun LeerDB(): MutableList<participante>{

            var lista: MutableList<participante> = mutableListOf()

            ref.addValueEventListener(object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError?) {}
                override fun onDataChange(p0: DataSnapshot?) {
                    //en esta parte va todo lo que tiene que hacer al momento de leer
                    if(p0!!.exists()){
                        for (p in p0.children){
                            val paa = p.getValue(participante::class.java)
                            lista.add(paa!!)

                        }
                    }
                }
            })
            return lista
        }

        fun split(palabra:String): List<String>{
            var lista: List<String> = palabra.split(",")
            return lista
        }
            //cuenta el numero de filas en mi lista
        override fun getCount(): Int {
            return Listplayer.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
        override fun getItem(position: Int): Any {
            return "TEST"
        }

        ///esto se encarga de renderizar las fila
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val lista: List<String> = split(Listplayer[position].datos)
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.main_row, viewGroup, false)
            val nick =  rowMain.findViewById<TextView>(R.id.nick)
            val datos  = rowMain.findViewById<TextView>(R.id.datos)
            datos.text = "Posición: "+(position+1) +"\t\ttiempo: "+lista[0]+"\nignoradas:  "+lista[1]+"\t\t acertadas: "+lista[2]
            nick.text = "Nick: "+Listplayer[position].nick
            return rowMain
            /*val textView =  TextView(mContext)
            textView.text = "pruebas"
            return textView*/
        }

    }
}
