
package com.example.mysports

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.text.InputType
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameText = view.findViewById<TextView>(R.id.profileName)
        val emailText = view.findViewById<TextView>(R.id.profileEmail)

        val nameInfo = view.findViewById<TextView>(R.id.profileNameInfo)
        val emailInfo = view.findViewById<TextView>(R.id.profileEmailInfo)

        val editButton = view.findViewById<Button>(R.id.editProfileButton)
        val settingsButton = view.findViewById<Button>(R.id.settingsButton)

        val preferences = requireActivity().getSharedPreferences(
            "MySportsProfile",
            Context.MODE_PRIVATE
        )

        fun displayProfile() {
            val name = preferences.getString("name", "") ?: ""
            val email = preferences.getString("email", "") ?: ""

            val displayedName = name.ifBlank { "Your Name" }
            val displayedEmail = email.ifBlank { "your@email.com" }

            nameText.text = displayedName
            emailText.text = displayedEmail

            nameInfo.text = displayedName
            emailInfo.text = displayedEmail
        }

        displayProfile()

        settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_settingsFragment)
        }

        editButton.setOnClickListener {

            val layout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(50, 20, 50, 10)
            }

            val nameInput = EditText(requireContext()).apply {
                hint = "Enter your name"
                setText(preferences.getString("name", ""))
            }

            val emailInput = EditText(requireContext()).apply {
                hint = "Enter your email"
                inputType = InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                setText(preferences.getString("email", ""))
            }

            layout.addView(nameInput)
            layout.addView(emailInput)

            AlertDialog.Builder(requireContext())
                .setTitle("Edit Profile")
                .setView(layout)
                .setPositiveButton("Save") { _, _ ->

                    val name = nameInput.text.toString().trim()
                    val email = emailInput.text.toString().trim()

                    if (name.isNotEmpty() && email.isNotEmpty()) {

                        preferences.edit()
                            .putString("name", name)
                            .putString("email", email)
                            .apply()

                        displayProfile()

                    }
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}

