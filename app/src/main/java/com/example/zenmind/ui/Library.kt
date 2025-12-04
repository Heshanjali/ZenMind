package com.example.zenmind.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zenmind.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Library : AppCompatActivity() {

    private val items = listOf(
        MeditationItem("Mindful Breathing", "10 min · Beginner"),
        MeditationItem("Body Scan", "12 min · Beginner"),
        MeditationItem("Loving Kindness", "15 min · Intermediate"),
        MeditationItem("Focus Reset", "5 min · Quick"),
        MeditationItem("Deep Relaxation", "20 min · Intermediate")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        setupBottomNav(R.id.nav_library)

        val rv = findViewById<RecyclerView>(R.id.rvMeditations)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = MeditationAdapter(items)
    }

    private fun setupBottomNav(selected: Int) {
        val bottom = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottom.selectedItemId = selected
        bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> startActivity(Intent(this, Home::class.java))
                R.id.nav_library -> {}
                R.id.nav_journal -> startActivity(Intent(this, Journal::class.java))
                R.id.nav_history -> startActivity(Intent(this, MoodHistory::class.java))
            }
            overridePendingTransition(0,0); finish()
            true
        }
    }

    data class MeditationItem(val title: String, val meta: String)

    class MeditationAdapter(private val data: List<MeditationItem>) :
        RecyclerView.Adapter<MeditationAdapter.MeditationVH>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationVH {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_meditation, parent, false)
            return MeditationVH(v)
        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: MeditationVH, position: Int) {
            holder.bind(data[position])
        }

        class MeditationVH(view: View) : RecyclerView.ViewHolder(view) {
            private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            private val tvMeta = view.findViewById<TextView>(R.id.tvMeta)
            fun bind(item: MeditationItem) { tvTitle.text = item.title; tvMeta.text = item.meta }
        }
    }
}
