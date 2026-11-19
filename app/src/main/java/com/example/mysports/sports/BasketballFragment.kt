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

class BasketballFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_basketball, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteButton = view.findViewById<Button>(R.id.addToFavoritesButton)

        val preferences = requireActivity().getSharedPreferences(
            "MySportsFavorites",
            Context.MODE_PRIVATE
        )

        val isFavorite = preferences.getBoolean("Basketball", false)

        favoriteButton.text = if (isFavorite) {
            "Remove from Favorites"
        } else {
            "Add to Favorites"
        }

        favoriteButton.setOnClickListener {
            val currentlyFavorite = preferences.getBoolean("Basketball", false)
            val newFavoriteStatus = !currentlyFavorite

            preferences.edit()
                .putBoolean("Basketball", newFavoriteStatus)
                .apply()

            favoriteButton.text = if (newFavoriteStatus) {
                "Remove from Favorites"
            } else {
                "Add to Favorites"
            }

            Toast.makeText(
                requireContext(),
                if (newFavoriteStatus) "Basketball added to Favorites!"
                else "Basketball removed from Favorites!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
