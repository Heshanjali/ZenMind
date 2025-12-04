package com.example.zenmind.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.zenmind.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {

    private val slogans = listOf(
        "Breathe. Reflect. Rebalance.",
        "Mental clarity, one tap away.",
        "Calm your chaos."
    )
    private var sloganIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupBottomNav(R.id.nav_home)

        val tvSlogan = findViewById<TextView>(R.id.tvSlogan)
        val btnStart = findViewById<View>(R.id.btnStartMeditation)
        val cardJournal = findViewById<View>(R.id.cardQuickJournal)

        tvSlogan.setOnClickListener {
            sloganIndex = (sloganIndex + 1) % slogans.size
            tvSlogan.text = slogans[sloganIndex]
        }
        btnStart.setOnClickListener {
            startActivity(Intent(this, Library::class.java)); overridePendingTransition(0,0); finish()
        }
        cardJournal.setOnClickListener {
            startActivity(Intent(this, Journal::class.java)); overridePendingTransition(0,0); finish()
        }
    }

    private fun setupBottomNav(selected: Int) {
        val bottom = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottom.selectedItemId = selected
        bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {}
                R.id.nav_library -> startActivity(Intent(this, Library::class.java))
                R.id.nav_journal -> startActivity(Intent(this, Journal::class.java))
                R.id.nav_history -> startActivity(Intent(this, MoodHistory::class.java))
            }
            overridePendingTransition(0,0); finish()
            true
        }
    }
}
