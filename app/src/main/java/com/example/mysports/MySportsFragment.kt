
package com.example.mysports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MySportsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_my_sports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.basketballCard).setOnClickListener {
            findNavController().navigate(
                R.id.action_mySportsFragment_to_basketballFragment
            )
        }

        view.findViewById<View>(R.id.footballCard).setOnClickListener {
            findNavController().navigate(
                R.id.action_mySportsFragment_to_footballFragment
            )
        }

        view.findViewById<View>(R.id.tennisCard).setOnClickListener {
            findNavController().navigate(
                R.id.action_mySportsFragment_to_tennisFragment
            )
        }

        view.findViewById<View>(R.id.volleyballCard).setOnClickListener {
            findNavController().navigate(
                R.id.action_mySportsFragment_to_volleyballFragment
            )
        }
    }
}


