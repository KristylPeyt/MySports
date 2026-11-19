package com.example.mysports

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exploreButton =
            view.findViewById<Button>(R.id.exploreSportsButton)

        exploreButton.setOnClickListener {
            findNavController().navigate(R.id.mySportsFragment)
        }

        updateHome(view)
    }

    private fun updateHome(view: View) {
        val profilePreferences = requireActivity().getSharedPreferences(
            "MySportsProfile",
            Context.MODE_PRIVATE
        )

        val favoritePreferences = requireActivity().getSharedPreferences(
            "MySportsFavorites",
            Context.MODE_PRIVATE
        )

        val name = profilePreferences.getString("name", "") ?: ""

        val welcomeText = view.findViewById<TextView>(R.id.homeWelcome)

        welcomeText.text = if (name.isNotBlank()) {
            "Welcome, $name!"
        } else {
            "Welcome to My Sports!"
        }

        val sports = listOf(
            "Basketball",
            "Football",
            "Tennis",
            "Volleyball"
        )

        val favoriteCount = sports.count {
            favoritePreferences.getBoolean(it, false)
        }

        view.findViewById<TextView>(R.id.homeFavoriteCount).text =
            "$favoriteCount Favorites"
    }

    override fun onResume() {
        super.onResume()
        view?.let { updateHome(it) }
    }
}


