package com.kluivert.mininoteapp.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.kluivert.mininoteapp.R
import com.kluivert.mininoteapp.adapter.SlideAdapter
import com.kluivert.mininoteapp.data.entities.SlideModel
import kotlinx.android.synthetic.main.activity_slide.*
import xyz.sangcomz.indicatordecorator.IndicatorItemDecoration
import xyz.sangcomz.indicatordecorator.shape.CircleIndicator



class SlideActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide)

        val slideLists = mutableListOf(
            SlideModel(
                R.drawable.slideone,
                "Title one",
                "Random description one with no actual meaning"
            ),
            SlideModel(
                R.drawable.slidetwo,
                "Title two",
                "Random description two with no actual meaning"
            ),
            SlideModel(
                R.drawable.slidethree,
                "Title three",
                "Random description three with no actual meaning"
            )
        )

        btnPrev.setOnClickListener {
            val slideIntBack : Int = viewPager2.currentItem.minus(1) ?:0
            viewPager2.setCurrentItem(slideIntBack,true)
        }

        btnNext.setOnClickListener {

            val slideInt : Int = viewPager2.currentItem.plus(1) ?: 0
            viewPager2.setCurrentItem(slideInt,true)

        }

        viewPager2.addItemDecoration(IndicatorItemDecoration().apply {
            indicatorShape = CircleIndicator().apply {
                colorActive = ContextCompat.getColor(this@SlideActivity, R.color.colorPrimaryDark)
                colorInactive = ContextCompat.getColor(this@SlideActivity,R.color.colorAccent)

            }
        })

        val adapter = SlideAdapter(slideLists)
        viewPager2.adapter = adapter
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == 0){
                    btnNext.text = getString(R.string.next)
                }else if(position == 1){
                    btnNext.text = getString(R.string.next)
                }else if(position == 2){
                    btnNext.text = getString(R.string.finish)
                    btnNext.setOnClickListener {
                        Intent(this@SlideActivity, MainActivity::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(it)
                            finish()

                        }
                    }

                }

            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)



            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                if (position == 0){
                    btnNext.text = getString(R.string.next)
                }else if(position == 1){
                    btnNext.text = getString(R.string.next)
                }else if(position == 2){
                    btnNext.text = getString(R.string.finish)
                    btnNext.setOnClickListener {
                        Intent(this@SlideActivity, MainActivity::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(it)
                            finish()

                        }
                    }

                }

            }
        })

    }

    override fun onResume() {
        super.onResume()

        var prefname: String = "prefName"

        val sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)
        if (!sharedpreferences.getBoolean(prefname, false)) {
            val editor = sharedpreferences.edit()
            editor.putBoolean(prefname,true)
            editor.apply()
        } else {
            Intent(this@SlideActivity, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(it)
                finish()
            }
        }


    }




}