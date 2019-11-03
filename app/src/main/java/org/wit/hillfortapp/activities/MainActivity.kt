package org.wit.hillfortapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import org.wit.hillfortapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        iv_logo.startAnimation(AnimationUtils.loadAnimation(this,
            R.anim.splash_in
        ))

        Handler().postDelayed({
            iv_logo.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.splash_out
            ))
            Handler().postDelayed(
                {iv_logo.visibility= View.GONE

                    startActivity(Intent(this, LoginActivity::class.java))},500)
        },2500)

    }
}
