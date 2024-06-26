package com.example.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.notlaruygulamasi.databinding.ActivityNotKayitBinding
import com.google.android.material.snackbar.Snackbar

class NotKayitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotKayitBinding
    private lateinit var vt:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotKayitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vt = DBHelper(this)

        binding.toolbarNotKayit.title = "Not Kayıt"
        setSupportActionBar(binding.toolbarNotKayit)


        binding.buttonKaydet.setOnClickListener {
           val ders_adi = binding.editTextDers.text.toString().trim()
            val not1 = binding.editTextNot1.text.toString().trim()
            val not2 = binding.editTextNot2.text.toString().trim()

            if (TextUtils.isEmpty(ders_adi)){
                Snackbar.make(binding.toolbarNotKayit,"Ders Adı Giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(not1)){
                Snackbar.make(binding.toolbarNotKayit,"1. Notu Giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(not2)){
                Snackbar.make(binding.toolbarNotKayit,"2. Notu Giriniz",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Notlardao().notEkle(vt,ders_adi,not1.toInt(),not2.toInt())


            val intent = Intent(this@NotKayitActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }








    }
}