package com.example.elif.ortalamahesaplama

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_yonetim_bilisim.*
import kotlinx.android.synthetic.main.yeni_ders_layout.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

class YonetimBilisim : AppCompatActivity() {

    private val DERSLER1 = arrayOf("Bilişim Hukuku", "Proje Yönetimi", "İktisada Giriş 1", "Yeni İletişim Teknolojileri",
            "İşletme Fonksiyonları", "Matematik 1", "Yabancı Dil 1")
    private var tumDerslerinBilgileri1: ArrayList<Dersler> = ArrayList(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yonetim_bilisim)

        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, DERSLER1)
        etYbsDers.setAdapter(adapter)
        ortalamaYbsHesapla.visibility = View.INVISIBLE
        btnYbsEkle.setOnClickListener {
            if (rootlayout2.childCount == 0) {
                ortalamaYbsHesapla.visibility = View.INVISIBLE
            } else ortalamaYbsHesapla.visibility = View.VISIBLE


            var inflater = LayoutInflater.from(this)
            var yeniDersView2 = inflater.inflate(R.layout.yeni_ders_layout, null)
            if (!TextUtils.isEmpty(etYbsDers.text)) {
                var ybsDersAdi = etYbsDers.text.toString()
                if (DERSLER1.contains(etYbsDers.text.toString())) {
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
                    FancyToast.makeText(this, "Lütfen Bölümünüzden Bir Dersi Giriniz..", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show()
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
        var toplamNot = 0.0
        var toplamKredi = 0.0

        for (i in 0..rootlayout2.childCount - 1) {
            var tekSatir = rootlayout2.getChildAt(i)
            var geciciDers = Dersler(tekSatir.etYeniDers.text.toString(),
                    (tekSatir.spnYeniDersKredi.selectedItemPosition + 1).toString(),
                    tekSatir.spnYeniDersNot.selectedItem.toString())
            tumDerslerinBilgileri1.add(geciciDers)
        }
        for (oankiDers in tumDerslerinBilgileri1) {
            toplamNot += harfiNotaCevir(oankiDers.dersHarfNotu) * (oankiDers.dersKredi.toDouble())
            toplamKredi += oankiDers.dersKredi.toDouble()
        }
        var ortalama = toplamNot / toplamKredi
        FancyToast.makeText(this, "Ortalama : " + "%.2f".format(ortalama), FancyToast.LENGTH_LONG, FancyToast.INFO, false).show()
        //her butona basıldıgında eski desler kalacak o yüzden arraylistimizi temziiliyoruz
        tumDerslerinBilgileri1.clear()
    }

    fun harfiNotaCevir(gelenNotHarfDegeri: String): Double {
        var deger = 0.0
        when (gelenNotHarfDegeri) {
            "AA" -> deger = 4.0
            "BA" -> deger = 3.5
            "BB" -> deger = 3.0
            "CB" -> deger = 2.5
            "CC" -> deger = 2.0
            "DC" -> deger = 1.5
            "DD" -> deger = 1.0
            "FF" -> deger = 0.0
        }
        return deger
    }

}

