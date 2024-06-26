package com.example.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notlaruygulamasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var notlarListe:ArrayList<Notlar>
    private lateinit var adapter:NotlarAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var vt:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         binding.toolbar.title = "Notlar Uygulaması"

        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this )

        vt = DBHelper(this)

        notlarListe = Notlardao().tumNotlar(vt)

        adapter = NotlarAdapter(this,notlarListe)

        binding.rv.adapter = adapter

                var toplam = 0

                for (n in notlarListe){
                    toplam +=( n.not1 +n.not2)/2
                }

                if (toplam!=0){
                    binding.toolbar.subtitle = "Ortalama: ${toplam/notlarListe.size}"
                }

        binding.fab.setOnClickListener {
            startActivity(Intent(this@MainActivity,NotKayitActivity::class.java))

        }





    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}