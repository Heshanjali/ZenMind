package com.example.zenmind.ui

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.zenmind.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MoodHistory : AppCompatActivity() {

    private val demoMoods = listOf("😊","🙂","😐","😔","😁","😴","🤗")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_history)

        setupBottomNav(R.id.nav_history)

        val grid = findViewById<GridLayout>(R.id.gridCalendar)
        for (i in 1..35) {
            val tv = TextView(this).apply {
                text = if (i in 1..31) "$i\n${demoMoods[i % demoMoods.size]}" else ""
                textSize = 14f
                setPadding(10,10,10,10)
                gravity = Gravity.CENTER
                setTextColor(getColor(R.color.textPrimary))
                setBackgroundColor(getColor(R.color.surface))
            }
            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(4,4,4,4)
            }
            grid.addView(tv, params)
        }
        for (idx in 0 until grid.childCount) {
            if (idx < 7) (grid.getChildAt(idx) as? TextView)?.setTypeface(null, Typeface.BOLD)
        }
    }

    private fun setupBottomNav(selected: Int) {
        val bottom = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottom.selectedItemId = selected
        bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> startActivity(Intent(this, Home::class.java))
                R.id.nav_library -> startActivity(Intent(this, Library::class.java))
                R.id.nav_journal -> startActivity(Intent(this, Journal::class.java))
                R.id.nav_history -> {}
            }
            overridePendingTransition(0,0); finish()
            true
        }
    }
}
