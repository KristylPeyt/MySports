
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

class FootballFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_football, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.addToFavoritesButton)
        val preferences = requireActivity().getSharedPreferences(
            "MySportsFavorites", Context.MODE_PRIVATE
        )

        fun updateButton() {
            button.text = if (preferences.getBoolean("Football", false))
                "Remove from Favorites" else "Add to Favorites"
        }

        updateButton()

        button.setOnClickListener {
            val newStatus = !preferences.getBoolean("Football", false)
            preferences.edit().putBoolean("Football", newStatus).apply()
            updateButton()

            Toast.makeText(
                requireContext(),
                if (newStatus) "Football added to Favorites!"
                else "Football removed from Favorites!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
