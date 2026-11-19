
package com.example.mysports.sports

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mysports.R

class VolleyballFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_volleyball, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.addToFavoritesButton)
        val preferences = requireActivity().getSharedPreferences(
            "MySportsFavorites", Context.MODE_PRIVATE
        )

        fun updateButton() {
            button.text = if (preferences.getBoolean("Volleyball", false))
                "Remove from Favorites" else "Add to Favorites"
        }

        updateButton()

        button.setOnClickListener {
            val newStatus = !preferences.getBoolean("Volleyball", false)
            preferences.edit().putBoolean("Volleyball", newStatus).apply()
            updateButton()

            Toast.makeText(
                requireContext(),
                if (newStatus) "Volleyball added to Favorites!"
                else "Volleyball removed from Favorites!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
