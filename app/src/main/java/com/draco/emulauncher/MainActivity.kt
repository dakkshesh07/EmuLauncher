package com.draco.emulauncher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private fun getAppList(): Array<AppInfo> {
        val launcherIntent = Intent(Intent.ACTION_MAIN, null)
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER)

        val activities = packageManager.queryIntentActivities(launcherIntent, 0)
        val appList = ArrayList<AppInfo>()

        for (app in activities) {
            val appId = app.activityInfo.packageName
            if (appId == packageName)
                continue

            AppInfo().apply {
                image = packageManager.getApplicationIcon(appId)
                label = app.activityInfo.loadLabel(packageManager).toString()
                launchIntent = packageManager.getLaunchIntentForPackage(appId)!!
                appList.add(this)
            }
        }

        appList.sortBy {
            it.label.toLowerCase(Locale.getDefault())
        }

        return appList.toTypedArray()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val recyclerAdapter = RecyclerAdapter(getAppList(), this)
        val manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        with (recycler) {
            adapter = recyclerAdapter
            layoutManager = manager
            PagerSnapHelper().attachToRecyclerView(this)
            scrollToPosition(Integer.MAX_VALUE / 2)
            smoothScrollToPosition(Integer.MAX_VALUE / 2 + 5)
        }
    }
}