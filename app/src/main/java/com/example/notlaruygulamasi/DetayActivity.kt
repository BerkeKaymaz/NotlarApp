package com.example.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.example.notlaruygulamasi.databinding.ActivityDetayBinding
import com.google.android.material.snackbar.Snackbar

class DetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetayBinding
    private lateinit var not: Notlar
    private lateinit var vt:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarDetay.title = "Not Detay"
        setSupportActionBar(binding.toolbarDetay)

        vt =DBHelper(this)

         not = intent.getSerializableExtra("nesne") as Notlar

        binding.editTextDers.setText(not.ders_adi)
        binding.editTextNot1.setText(not.not1.toString())
        binding.editTextNot2.setText(not.not2.toString())


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_sil ->{
                Snackbar.make(binding.toolbarDetay,"Silinsin Mi?",Snackbar.LENGTH_SHORT)
                    .setAction("EVET"){

                        Notlardao().notSil(vt,not.not_id)
                        startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                        finish()
                    }.show()

                return true
            }
            R.id.action_duzenle ->{
                val ders_adi = binding.editTextDers.text.toString().trim()
                val not1 = binding.editTextNot1.text.toString().trim()
                val not2 = binding.editTextNot2.text.toString().trim()

                if (TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(binding.toolbarDetay,"Ders Adı Giriniz",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if (TextUtils.isEmpty(not1)){
                    Snackbar.make(binding.toolbarDetay,"1. Notu Giriniz",Snackbar.LENGTH_SHORT).show()
                    return false
                }
                if (TextUtils.isEmpty(not2)){
                    Snackbar.make(binding.toolbarDetay,"2. Notu Giriniz",Snackbar.LENGTH_SHORT).show()
                    return false
                }

                Notlardao().notGuncelle(vt,not.not_id,ders_adi, not1.toInt(),not2.toInt() )

                startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                finish()
                return true
            }

            else -> return false
         }


    }
}