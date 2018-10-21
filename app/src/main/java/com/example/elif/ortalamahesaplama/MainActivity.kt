package com.example.elif.ortalamahesaplama

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders_layout.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

class MainActivity : AppCompatActivity() {

    //veri kaynagını tanımladık.
    private val DERSLER = arrayOf("Matematik 1", "Hukukun Temel Kavramları",
            "Iktisada Giriş 1", "Genel İşletme", "Finansal Muhasebe", "Yabancı Dil", "Türk Dili 1", "Ingilizce 1", "Borçlar Hukuku", "Iktisada Giriş 2", "Ingilizce 2",
            "Finansal Muhasebe 2", "Psikoloji", "Türk Dili 2")
    private var tumDerslerinBilgileri: ArrayList<Dersler> = ArrayList(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //adapterimizi burda tanımladık.
        //simple_dropdown_item_1line ise ctrl ile içine girdğimizde text bulunuyordu. Bu demek oluyor ki veri kayangımdan
        //alınan her veri tek tek textview içine yazılacak.
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, DERSLER)
        etDers.setAdapter(adapter)
        if (rootlayout.childCount == 0) {
            ortalamaHesapla.visibility = View.INVISIBLE
        } else ortalamaHesapla.visibility = View.VISIBLE

        btnEkle.setOnClickListener {

            var inflater = LayoutInflater.from(this)
            var yeniDersView = inflater.inflate(R.layout.yeni_ders_layout, null)

            if (!TextUtils.isEmpty(etDers.text)) {
                var DersAdi = etDers.text.toString()
                if (DERSLER.contains(etDers.text.toString())) {
                    var DersNot = spnDersNot.selectedItemPosition
                    var DersKredi = spnDersKredi.selectedItemPosition

                    yeniDersView.etYeniDers.setText(DersAdi)
                    yeniDersView.spnYeniDersNot.setSelection(DersNot)
                    yeniDersView.spnYeniDersKredi.setSelection(DersKredi)

                    //rootlayouta eklemeden önce ulaşmamız lazım ki hangisinin silecegini bilsin.
                    yeniDersView.btnSil.setOnClickListener {
                        //rootlayouta eklenen yenidersviewı kaldır
                        rootlayout.removeView(yeniDersView)
                        if (rootlayout.childCount == 0) {
                            ortalamaHesapla.visibility = View.INVISIBLE
                        } else ortalamaHesapla.visibility = View.VISIBLE

                    }
                    rootlayout.addView(yeniDersView)
                    sifirla()
                    ortalamaHesapla.visibility = View.VISIBLE
                } else {
                    FancyToast.makeText(this, "Bölümünüzdeki Dersi İçermiyor !", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show()

                }
            } else {
                FancyToast.makeText(this, "Lütfen Bir Ders İsmi Giriniz.. !", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show()

            }
        }
    }

    fun sifirla() {
        etDers.setText("")
        spnDersKredi.setSelection(0)
        spnDersNot.setSelection(0)
    }

    fun ortalamahesapla(view: View) {
        var toplamNot = 0.0
        var toplamKredi = 0.0

        for (i in 0..rootlayout.childCount - 1) {
            var tekSatir = rootlayout.getChildAt(i)
            var geciciDers = Dersler(tekSatir.etYeniDers.text.toString(),
                    (tekSatir.spnYeniDersKredi.selectedItemPosition + 1).toString(),
                    tekSatir.spnYeniDersNot.selectedItem.toString())
            tumDerslerinBilgileri.add(geciciDers)
        }
        for (oankiDers in tumDerslerinBilgileri) {
            toplamNot += harfiNotaCevir(oankiDers.dersHarfNotu) * (oankiDers.dersKredi.toDouble())
            toplamKredi += oankiDers.dersKredi.toDouble()
        }
        var ortalama = toplamNot / toplamKredi
        FancyToast.makeText(this, "Ortalama : " + "%.2f".format(ortalama), FancyToast.LENGTH_LONG, FancyToast.INFO, false).show()
        //her butona basıldıgında eski desler kalacak o yüzden arraylistimizi temziiliyoruz
        tumDerslerinBilgileri.clear()
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

    fun formatla(KacTaneTakam: Int) = java.lang.String.format("%.${KacTaneTakam}f", this)
}

