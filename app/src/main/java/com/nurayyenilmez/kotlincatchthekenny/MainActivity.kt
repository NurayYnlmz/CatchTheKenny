package com.nurayyenilmez.kotlincatchthekenny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.nurayyenilmez.kotlincatchthekenny.databinding.ActivityMainBinding
import java.lang.reflect.Array
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    var score =0
    var runnable =Runnable{ }
    var handler = Handler(Looper.getMainLooper())

    var imageArray=ArrayList<ImageView>( )

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        imageArray.add(binding.imageView1)
        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)

        for (image in imageArray){
            image.visibility=View.INVISIBLE
        }

    }
    fun start(view: View){
        hideImages()

        object : CountDownTimer(15000,1000){
            override fun onFinish() {

             binding.timeText.text="Time:0"

                handler.removeCallbacks(runnable)


                val alert= AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over!")
                alert.setMessage("Restart the game?")

                alert.setPositiveButton("Yes"){dialog,which ->
                    val intent = intent
                    finish()
                    startActivity(intent)

                    for (image in imageArray){
                        image.visibility=View.INVISIBLE
                    }


                }
                alert.setNegativeButton("No"){dialog,which ->
              Toast.makeText(applicationContext,"Game Over",Toast.LENGTH_LONG).show()
                }
                alert.show()
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }


            }
            override fun onTick(p0: Long) {
             binding.timeText.text=" Time:${p0/1000}"
            }
        }.start()

             binding.startText.isEnabled=false

    }

         fun hideImages(){
          runnable= object:Runnable{
              override fun run() {
                  for (image in imageArray){
                      image.visibility=View.INVISIBLE
                  }

                  var random =Random()
                  var randomIndex=random.nextInt(9)
                  imageArray[randomIndex].visibility=View.VISIBLE

                  handler.postDelayed(runnable,500)

              }

          }
             handler.post(runnable)

         }



     fun score(view: View){
         score++
         binding.scoreText.text ="score"+score



    }

}
