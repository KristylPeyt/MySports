package com.example.mysports

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayFavorites(view)
    }

    private fun displayFavorites(view: View) {

        val favoritesContainer =
            view.findViewById<LinearLayout>(R.id.favoritesContainer)

        val preferences = requireActivity().getSharedPreferences(
            "MySportsFavorites",
            Context.MODE_PRIVATE
        )

        val sports = listOf(
            Triple("Basketball", "🏀", "#FFF0DF"),
            Triple("Football", "⚽", "#E8F5E9"),
            Triple("Tennis", "🎾", "#F1F5D9"),
            Triple("Volleyball", "🏐", "#E3F2FD")
        )

        favoritesContainer.removeAllViews()

        val savedSports = sports.filter {
            preferences.getBoolean(it.first, false)
        }

        if (savedSports.isEmpty()) {

            val emptyCard = MaterialCardView(requireContext()).apply {
                radius = 18f
                cardElevation = 2f
                setCardBackgroundColor(Color.WHITE)
                strokeColor = Color.rgb(229, 234, 240)
                strokeWidth = 1
            }

            val emptyMessage = TextView(requireContext()).apply {
                text = "☆\n\nNo Favorites Yet\n\nExplore My Sports and add your favorite sports to see them here."
                textSize = 16f
                gravity = android.view.Gravity.CENTER
                setTextColor(Color.rgb(113, 128, 150))
                setPadding(30, 40, 30, 40)
                setLineSpacing(8f, 1f)
            }

            emptyCard.addView(emptyMessage)

            val emptyParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            favoritesContainer.addView(emptyCard, emptyParams)

        } else {

            savedSports.forEach { sport ->

                val favoriteCard = MaterialCardView(requireContext()).apply {
                    radius = 18f
                    cardElevation = 2f
                    setCardBackgroundColor(Color.WHITE)
                    strokeColor = Color.rgb(229, 234, 240)
                    strokeWidth = 1
                }

                val cardContent = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = android.view.Gravity.CENTER_VERTICAL
                    setPadding(20, 20, 20, 20)
                }

                val sportIcon = TextView(requireContext()).apply {
                    text = sport.second
                    textSize = 34f
                    gravity = android.view.Gravity.CENTER
                    setBackgroundColor(Color.parseColor(sport.third))
                    setPadding(12, 12, 12, 12)
                }

                val sportDetails = LinearLayout(requireContext()).apply {
                    orientation = LinearLayout.VERTICAL
                    setPadding(16, 0, 0, 0)
                }

                val sportName = TextView(requireContext()).apply {
                    text = sport.first
                    textSize = 18f
                    setTextColor(Color.rgb(23, 43, 77))
                    setTypeface(null, android.graphics.Typeface.BOLD)
                }

                val favoriteLabel = TextView(requireContext()).apply {
                    text = "Added to your favorites"
                    textSize = 13f
                    setTextColor(Color.rgb(113, 128, 150))
                    setPadding(0, 5, 0, 0)
                }

                sportDetails.addView(sportName)
                sportDetails.addView(favoriteLabel)

                cardContent.addView(sportIcon)

                cardContent.addView(
                    sportDetails,
                    LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f
                    )
                )

                favoriteCard.addView(cardContent)

                val cardParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 14
                }

                favoritesContainer.addView(favoriteCard, cardParams)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        view?.let { displayFavorites(it) }
    }
}


