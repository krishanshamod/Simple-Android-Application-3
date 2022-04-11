package com.krishanshamod.simple_android_application_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.krishanshamod.simple_android_application_3.database.AppDatabase
import com.krishanshamod.simple_android_application_3.databinding.FragmentFourthBinding
import com.krishanshamod.simple_android_application_3.databinding.FragmentThirdBinding
import com.krishanshamod.simple_android_application_3.model.Location

class FourthFragment : Fragment() {
    private var _binding: FragmentFourthBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect database to edit a location
        val db = AppDatabase.getDatabase(view.context)
        val locationDao = db.LocationDao()

        //get bundled data
        var args = this.arguments
        val locationName = args?.get("locationName").toString()
        val locationLat = args?.get("latitude").toString().toDouble()
        val locationLong = args?.get("longitude").toString().toDouble()

        val location = Location(locationName, locationLat, locationLong)

        // set location data set text fields
        binding.editLocationNameText.setText(locationName)
        binding.editLocationLatitudeText.setText(locationLat.toString())
        binding.editLocationLongitudeText.setText(locationLong.toString())

        binding.editLocationSaveButton.setOnClickListener {

            binding.apply {
                val locationName = editLocationNameText.text.toString()
                val locationLatitude = editLocationLatitudeText.text.toString().toDouble()
                val locationLongitude = editLocationLongitudeText.text.toString().toDouble()
                val updatedLocation = Location(locationName, locationLatitude, locationLongitude)

                // Delete the old location from the database
                db.LocationDao().delete(location)

                // Add new updated location to the database
                db.LocationDao().insertAll(updatedLocation)
            }

            findNavController().navigate(R.id.action_FourthFragment_to_FirstFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}