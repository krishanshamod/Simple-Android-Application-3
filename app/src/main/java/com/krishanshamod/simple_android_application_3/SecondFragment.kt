package com.krishanshamod.simple_android_application_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.krishanshamod.simple_android_application_3.database.AppDatabase
import com.krishanshamod.simple_android_application_3.databinding.FragmentSecondBinding
import com.krishanshamod.simple_android_application_3.model.Location

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {

            binding.apply {
                val locationName = editName.text.toString()
                val locationLatitude = editLatitude.text.toString().toDouble()
                val locationLongitude = editLongitude.text.toString().toDouble()
                val location = Location(locationName, locationLatitude, locationLongitude)

                val db = AppDatabase.getDatabase(view.context)
                db.LocationDao().insertAll(location)
            }

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}