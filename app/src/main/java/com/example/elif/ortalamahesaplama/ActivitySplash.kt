package com.example.elif.ortalamahesaplama

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.active_splash.*

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.active_splash)

        var asagidanGelenButon = AnimationUtils.loadAnimation(this, R.anim.asagidangelenbuton)
        var yukaridanGelenBalon = AnimationUtils.loadAnimation(this, R.anim.yukaridangelenbalon)
        var asagidanGelenButon2=AnimationUtils.loadAnimation(this,R.anim.asagidangelenbuton2)


        var asagiyaGeriDonenButon = AnimationUtils.loadAnimation(this, R.anim.asagigidenbuton)
        var yukariyaGeriDonenBalon = AnimationUtils.loadAnimation(this, R.anim.yukariyagidenbalon)
        var asagiyaGeriDonenButon2=AnimationUtils.loadAnimation(this,R.anim.asagigidenbuton2)

        btnisletme.animation = asagidanGelenButon
        imgBalon.animation = yukaridanGelenBalon
        btnybs.animation=asagidanGelenButon2

        btnisletme.setOnClickListener {
            btnisletme.startAnimation(asagiyaGeriDonenButon)
            imgBalon.startAnimation(yukariyaGeriDonenBalon)
            btnybs.startAnimation(asagiyaGeriDonenButon2)

            object: CountDownTimer(1000,1000)
            {
                override fun onFinish() {
                    //kotlin ve javaya ulaşmak için mainactivity::class kullanılır
                    var intent=Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }
                override fun onTick(p0: Long) {

                }
            }.start()
        }

        btnybs.setOnClickListener {
            btnisletme.startAnimation(asagiyaGeriDonenButon)
            imgBalon.startAnimation(yukariyaGeriDonenBalon)
            btnybs.startAnimation(asagiyaGeriDonenButon2)

            object: CountDownTimer(1000,1000)
            {
                override fun onFinish() {
                    //kotlin ve javaya ulaşmak için mainactivity::class kullanılır
                    var intent=Intent(applicationContext,YonetimBilisim::class.java)
                    startActivity(intent)
                }
                override fun onTick(p0: Long) {

                }
            }.start()

        }
    }
}
