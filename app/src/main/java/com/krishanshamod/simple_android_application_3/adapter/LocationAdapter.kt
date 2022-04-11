package com.krishanshamod.simple_android_application_3.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
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

        //create a bundle to pass data to other fragments
        val bundle = Bundle()
        bundle.putString("locationName", location.locationName)
        bundle.putDouble("latitude", location.latitude)
        bundle.putDouble("longitude", location.longitude)

        // navigate to delete fragment and transfer data bundle
        holder.deleteButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_FirstFragment_to_ThirdFragment, bundle)
        }

    }

    //number of the items in the list
    override fun getItemCount(): Int {
        return locationList.size
    }

    // Holds the views for adding it to text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val locationNameText: TextView = itemView.findViewById(R.id.locationNameText)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    }
}