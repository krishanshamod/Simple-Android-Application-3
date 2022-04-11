package com.krishanshamod.simple_android_application_3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krishanshamod.simple_android_application_3.R
import com.krishanshamod.simple_android_application_3.model.Location

class LocationAdapter(private val locationList: List<Location>): RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location: Location = locationList[position]

        holder.locationNameText.text = location.locationName
    }

    //number of the items in the list
    override fun getItemCount(): Int {
        return locationList.size
    }

    // Holds the views for adding it to text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val locationNameText: TextView = itemView.findViewById(R.id.locationNameText)
    }
}