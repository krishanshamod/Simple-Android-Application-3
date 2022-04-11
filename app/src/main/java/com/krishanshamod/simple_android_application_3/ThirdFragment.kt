package com.krishanshamod.simple_android_application_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.krishanshamod.simple_android_application_3.database.AppDatabase
import com.krishanshamod.simple_android_application_3.databinding.FragmentThirdBinding
import com.krishanshamod.simple_android_application_3.model.Location


class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.yesButton.setOnClickListener {

            // connect database to delete a location
            val db = AppDatabase.getDatabase(view.context)
            val locationDao = db.LocationDao()

            //get bundled data
            var args = this.arguments
            val locationName = args?.get("locationName").toString()
            val locationLat = args?.get("latitude").toString().toDouble()
            val locationLong = args?.get("longitude").toString().toDouble()

            val location = Location(locationName, locationLat, locationLong)

            //delete location
            locationDao.delete(location)

            findNavController().navigate(R.id.action_ThirdFragment_to_FirstFragment)
        }

        binding.noButton.setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_FirstFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}