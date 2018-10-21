package com.example.elif.ortalamahesaplama

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_yonetim_bilisim.*
import kotlinx.android.synthetic.main.yeni_ders_layout.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

class YonetimBilisim : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yonetim_bilisim)

        ortalamaYbsHesapla.visibility = View.INVISIBLE
        btnYbsEkle.setOnClickListener {

            var inflater = LayoutInflater.from(this)
            var yeniDersView2 = inflater.inflate(R.layout.yeni_ders_layout, null)
            if (!TextUtils.isEmpty(etYbsDers.text)) {
                var ybsDersAdi = etYbsDers.text.toString()
                var ybsDersKredi = spnYbsDersKredi.selectedItemPosition
                var ybsDersNot = spnYbsDersNot.selectedItemPosition
                //eklediğimiz bölüm butona eklenme yeri verdik
                yeniDersView2.etYeniDers.setText(ybsDersAdi)
                yeniDersView2.spnYeniDersKredi.setSelection(ybsDersKredi)
                yeniDersView2.spnYeniDersNot.setSelection(ybsDersNot)
                rootlayout2.addView(yeniDersView2)
                ortalamaYbsHesapla.visibility = View.VISIBLE
                sifirla()
                yeniDersView2.btnSil.setOnClickListener {
                    rootlayout2.removeView(yeniDersView2)
                    if (rootlayout2.childCount == 0) {
                        ortalamaYbsHesapla.visibility = View.INVISIBLE
                    } else ortalamaYbsHesapla.visibility = View.VISIBLE
                }


            } else {
                FancyToast.makeText(this, "Lütfen Bir Ders İsmi Giriniz..", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show()
            }
        }
    }

    fun sifirla() {
        etYbsDers.setText("")
        spnYbsDersNot.setSelection(0)
        spnYbsDersKredi.setSelection(0)
    }


    fun ortalamaYbsHesapla(view: View) {

    }
}
