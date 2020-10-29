package com.draco.emulauncher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlin.coroutines.coroutineContext
import kotlin.math.max

class RecyclerAdapter(
        private val list: Array<AppInfo>,
        private val context: Context
): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById(R.id.image) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position % list.size]
        holder.image.setImageDrawable(item.image)
        holder.image.contentDescription = item.label

        holder.image.setOnClickListener {
            context.startActivity(item.launchIntent)
        }
    }
}