package pl.momlok.wisielec.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import pl.momlok.wisielec.R
import pl.momlok.wisielec.table.animal
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animalBT.setOnClickListener {
            var intent = Intent(applicationContext,GameActivity::class.java)
            intent.putExtra("animal", "animal")
            startActivity(intent)
        }

        brandBT.setOnClickListener {
            var intent = Intent(applicationContext,GameActivity::class.java)
            intent.putExtra("brand", "brand")
            startActivity(intent)
        }
    }

}


