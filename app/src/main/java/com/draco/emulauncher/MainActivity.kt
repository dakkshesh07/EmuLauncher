package com.draco.emulauncher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private fun getAppList(): Array<AppInfo> {
        val a = AppInfo().also { it ->
            it.image = ContextCompat.getDrawable(this, R.drawable.snes)!!
            it.label = "Super Nintendo Entertainment System"
            val intent = packageManager.getLaunchIntentForPackage("com.retroarch.aarch64")!!
            it.launchIntent = intent
        }
        return arrayOf(a)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val recyclerAdapter = RecyclerAdapter(getAppList(), this)
        val manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        manager.scrollToPosition(Integer.MAX_VALUE / 2)

        with (recycler) {
            adapter = recyclerAdapter
            layoutManager = manager
        }
    }
}