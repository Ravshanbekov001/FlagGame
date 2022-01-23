package com.example.flaggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import flags.Flag
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    lateinit var flagArrayList: ArrayList<Flag>

    var position = 0
    var txtAnswer = ""
    var answerLetters = ArrayList<Char>()
    var a = "ABCDEFGHIJKLMNOPQRSTVWXYZ"
    var visibilityManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide navigation and status bar
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }

        flagArrayList = ArrayList()
        flagArrayList.add(Flag("UZBEKISTAN", R.drawable.uzbekistan))
        flagArrayList.add(Flag("BELARUS", R.drawable.belarus))
        flagArrayList.add(Flag("BRAZIL", R.drawable.brazil))
        flagArrayList.add(Flag("BRITAIN", R.drawable.britain))
        flagArrayList.add(Flag("CANADA", R.drawable.canada))
        flagArrayList.add(Flag("FRANCE", R.drawable.france))
        flagArrayList.add(Flag("GERMANY", R.drawable.germany))
        flagArrayList.add(Flag("INDIA", R.drawable.india))
        flagArrayList.add(Flag("KAZAKHSTAN", R.drawable.kazakhstan))
        flagArrayList.add(Flag("KOREA", R.drawable.korea))
        flagArrayList.add(Flag("TURKEY", R.drawable.turkey))
        flagArrayList.add(Flag("UKRAINE", R.drawable.ukraine))
        flagArrayList.add(Flag("USA", R.drawable.usa))
        flagArrayList.add(Flag("CHINA", R.drawable.china))
        flagArrayList.add(Flag("RUSSIA", R.drawable.russia))
        flagArrayList.add(Flag("ARGENTINA", R.drawable.argentina))



        btn_1.setOnClickListener { clickBtn(btn_1, txt1, txt1.text.toString()) }
        btn_2.setOnClickListener { clickBtn(btn_2, txt2, txt2.text.toString()) }
        btn_3.setOnClickListener { clickBtn(btn_3, txt3, txt3.text.toString()) }
        btn_4.setOnClickListener { clickBtn(btn_4, txt4, txt4.text.toString()) }
        btn_5.setOnClickListener { clickBtn(btn_5, txt5, txt5.text.toString()) }
        btn_6.setOnClickListener { clickBtn(btn_6, txt6, txt6.text.toString()) }
        btn_7.setOnClickListener { clickBtn(btn_7, txt7, txt7.text.toString()) }
        btn_8.setOnClickListener { clickBtn(btn_8, txt8, txt8.text.toString()) }
        btn_9.setOnClickListener { clickBtn(btn_9, txt9, txt9.text.toString()) }
        btn_10.setOnClickListener { clickBtn(btn_10, txt10, txt10.text.toString()) }
        btn_11.setOnClickListener { clickBtn(btn_11, txt11, txt11.text.toString()) }
        btn_12.setOnClickListener { clickBtn(btn_12, txt12, txt12.text.toString()) }

        txt_answer.setOnClickListener {
            back()
        }


        val animation = AnimationUtils.loadAnimation(this, R.anim.flag_anim)
        card_flag.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                randomFlag()
            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        addRandomLetters()
    }


    private fun clickBtn(cardView: CardView, textView: TextView, text: String) {
        txtAnswer += text
        textView.text = text
        txt_answer.text = txtAnswer

        if (txtAnswer.length == flagArrayList[position].name?.length) {

            if (txtAnswer == flagArrayList[position].name) {
                Toast.makeText(this, "True!", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "False!", Toast.LENGTH_SHORT).show()

            }

            val animation = AnimationUtils.loadAnimation(this, R.anim.flag_anim)
            card_flag.startAnimation(animation)

            animation.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(animation: Animation?) {
                    randomFlag()
                }

                override fun onAnimationEnd(animation: Animation?) {

                }

                override fun onAnimationRepeat(animation: Animation?) {

                }
            })

            setVisibility()
            addRandomLetters()

            txtAnswer = ""
            txt_answer.text = ""

        } else {
            cardView.visibility = View.INVISIBLE
        }
    }


    private fun randomFlag() {

        position = nextInt(flagArrayList.size)
        img_flag.setImageResource(flagArrayList[position].image!!)


    }


    private fun addRandomLetters() {
        answerLetters = flagArrayList[position].name?.toCharArray()?.toList() as ArrayList<Char>
        repeat(12 - answerLetters.size) {
            answerLetters.add(a[nextInt(a.length)])
        }

        answerLetters.shuffle()
        getText()
    }


    private fun getTextView(position: Int): TextView {
        when (position) {
            0 -> return txt1
            1 -> return txt2
            2 -> return txt3
            3 -> return txt4
            4 -> return txt5
            5 -> return txt6
            6 -> return txt7
            7 -> return txt8
            8 -> return txt9
            9 -> return txt10
            10 -> return txt11
            11 -> return txt12
        }
        return txt1
    }


    private fun getCardView(position: Int): CardView {
        when (position) {
            0 -> return btn_1
            1 -> return btn_2
            2 -> return btn_3
            3 -> return btn_4
            4 -> return btn_5
            5 -> return btn_6
            6 -> return btn_7
            7 -> return btn_8
            8 -> return btn_9
            9 -> return btn_10
            10 -> return btn_11
            11 -> return btn_12
        }
        return btn_1
    }


    private fun getText() {
        for (i in 0..11) {
            getTextView(i).text = answerLetters[i].toString()
        }
    }


    private fun setVisibility() {

        for (i in 0..11) {
            getCardView(i).visibility = View.VISIBLE

        }
    }

    private fun back() {
        val str = txt_answer.text.toString()

        if (str.isNotEmpty()) {

            val c = str[str.length - 1]

            for (i in 0..11) {

                if (answerLetters[i] == c) {
                    getCardView(i).visibility = View.VISIBLE
                }
            }

            txtAnswer = str.substring(0, str.length - 1)
            txt_answer.text = txtAnswer

            if (txt_answer.text.isEmpty()) {
                txtAnswer = ""
            }
        }
    }
}