package pl.momlok.wisielec.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import pl.momlok.wisielec.R
import pl.momlok.wisielec.table.animal
import pl.momlok.wisielec.table.brand
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    var enigma = animal.get(Random.nextInt(0,1))
    var chars = enigma.toCharArray()
    var length = enigma.length - 1
    val hidden = Array(30){"_"}
    var hiddenWord = ""
    var word = ""
    var wypisane: String = ""
    var chance = 7
    var lose="LOSE"
    var win = "WIN"
    var extra = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        if(intent.hasExtra("animal") || extra.equals("animal")){
            extra = "animal"
            enigma = animal.get(Random.nextInt(0,15))
        }
        if(intent.hasExtra("brand")|| extra.equals("brand")){
            extra = "brand"
            enigma = brand.get(Random.nextInt(0,16))
        }
        chars = enigma.toCharArray()
        length = enigma.length - 1

        for (i in 0..length){
            hiddenWord += hidden.get(i)
            hiddenWord += " "
        }

        wordTV.setText("Search word:  $hiddenWord")
        lettersTV.setText("Letters: $wypisane")

        checkBT.setOnClickListener {
            var danaLitera = letterEdit.text.toString()
            check(danaLitera)
            draw(chance, word)
            letterEdit.setText("")
        }
        resetBT.setOnClickListener {
            var intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun draw(zycie: Int, slowo: String) {
        val bitmap = Bitmap.createBitmap(700, 550, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        if (slowo.equals(enigma)){
            paint.strokeWidth = 4f
            paint.setTextSize(80F)
            canvas?.drawText(win,5f,300f,paint)
        }
        if (zycie<7){
            canvas?.drawLine(50f,550f,150f,450f,paint)
            canvas?.drawLine(150f,450f,250f,550f,paint)
        }
        if (zycie < 6)canvas?.drawLine(150f,450f,150f,50f,paint)
        if (zycie < 5)canvas?.drawLine(150f,50f,450f,50f,paint)
        if (zycie < 4)canvas?.drawLine(450f,50f,450f,150f,paint)
        if (zycie < 3){
            canvas?.drawCircle(450f,200f,50f,paint)
            canvas?.drawLine(450f,250f,450f,350f,paint)
        }
        if (zycie < 2) {
            canvas?.drawLine(450f,350f,400f,400f,paint)
            canvas?.drawLine(450f,350f,500f,400f,paint)
        }
        if (zycie < 1) {
            canvas?.drawLine(450f,270f,400f,320f,paint)
            canvas?.drawLine(450f,270f,500f,320f,paint)
            canvas?.drawLine(420f,190f,430f,190f,paint)
            canvas?.drawLine(460f,190f,470f,190f,paint)
            canvas?.drawLine(430f,220f,445f,210f,paint)
            canvas?.drawLine(445f,210f,460f,220f,paint)
            paint.strokeWidth = 4f
            paint.setTextSize(40F)
            canvas?.drawText(lose,10f,30f,paint)
        }
        imageView.setImageBitmap(bitmap)
    }

    fun check(danaLitera: String){
        var status = "zly"
        for (i in 0..length){
            var litera = chars.get(i).toString()
            if (danaLitera.equals(litera)){
                hidden.set(i,danaLitera)
                hiddenWord = ""
                word = ""
                for (i in 0..length){
                    hiddenWord += hidden.get(i)
                    hiddenWord += " "
                    word += hidden.get(i)
                }
                wordTV.setText("Search word:  $hiddenWord")
                status = "dobry"
                wypisane += danaLitera
                wypisane += " "
                lettersTV.setText("Letters: $wypisane")
            }
        }
        if (status.equals("zly")) {
            if(chance >0){
                chance--
                chanceTV.setText("You have $chance chance")
                wypisane += danaLitera
                wypisane += " "
                lettersTV.setText("Letters: $wypisane")
            }
        }
    }
}