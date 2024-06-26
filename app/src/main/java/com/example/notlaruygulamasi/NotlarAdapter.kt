package com.example.notlaruygulamasi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NotlarAdapter(private val mContext:Context,private val notlarListesi:List<Notlar>)
    :RecyclerView.Adapter<NotlarAdapter.CardTasarimTutucu>() {


    inner class CardTasarimTutucu(tasarim:View) :RecyclerView.ViewHolder(tasarim){
        var not_card:CardView
        var textViewDers:TextView
        var textViewNot1:TextView
        var textViewNot2:TextView
        val textViewOrtalama: TextView
        val textViewHarf: TextView

        init {
            not_card = tasarim.findViewById(R.id.not_card)
            textViewDers = tasarim.findViewById(R.id.textViewDers)
            textViewNot1 = tasarim.findViewById(R.id.textViewNot1)
            textViewNot2 = tasarim.findViewById(R.id.textViewNot2)
            textViewOrtalama = tasarim.findViewById(R.id.textViewOrtalama)
            textViewHarf = tasarim.findViewById(R.id.textViewHarf)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return notlarListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val not = notlarListesi[position]
        holder.textViewDers.text = not.ders_adi
        holder.textViewNot1.text = not.not1.toString()
        holder.textViewNot2 .text = not.not2.toString()

        holder.textViewOrtalama.text = ((((not.not1)*4/10)+((not.not2)*6/10))).toString()
        val harfNot:String
        val ort = ((((not.not1)*4/10)+((not.not2)*6/10)))
        if (ort>=90){
            harfNot ="AA"
        }
        else if (90>ort && ort>= 85){
            harfNot = "BA"
        }

        else if (85>ort && ort>= 80){
            harfNot = "BB"
        }
        else if (80>ort && ort>= 75){
            harfNot = "CB"
        }
        else if (75>ort && ort>= 65){
            harfNot = "CC"
        }
        else if (65>ort && ort>= 60){
            harfNot = "DC"
        }
        else if (60>ort && ort>= 55){
            harfNot = "DD"
        }
        else if (ort in 50..54){
            harfNot = "FD"
        }
        else if (50>ort && ort>= 0){
            harfNot = "FF"
        }
        else{
            harfNot = "NA"
        }
        holder.textViewHarf.text = harfNot

        if (holder.textViewHarf.text=="FF" || holder.textViewHarf.text == "FD"){
            holder.textViewHarf.setTextColor(Color.RED)
        }



        holder.not_card.setOnClickListener {
            val intent = Intent(mContext,DetayActivity::class.java)
            intent.putExtra("nesne",not)
            mContext.startActivity(intent)
        }

    }


}