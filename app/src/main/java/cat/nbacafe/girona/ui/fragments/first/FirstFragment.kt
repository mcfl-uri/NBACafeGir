package cat.nbacafe.girona.ui.fragments.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import cat.nbacafe.girona.NbaCafeApp.Companion.preferences
import cat.nbacafe.girona.R
import cat.nbacafe.girona.databinding.FirstFragmentBinding
import cat.nbacafe.girona.shared.SharedPreferences
import cat.nbacafe.girona.shared.SharedViewModel
import cat.nbacafe.girona.ui.MainActivity

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FirstFragmentBinding>(inflater, R.layout.first_fragment, container, false)



        binding.registratButton.setOnClickListener { View ->
            view?.findNavController()?.navigate(R.id.action_firstFragment_to_registerFragment)
        }

        binding.iniciaSessioButton.setOnClickListener { View ->
            view?.findNavController()?.navigate(R.id.action_firstFragment_to_loginFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (preferences.getName() != "") {
            val sharedViewModel: SharedViewModel by activityViewModels()
            sharedViewModel.logUser(preferences.getName())
            view.findNavController().navigate(R.id.action_firstFragment_to_homeFragment)
        }
    }
}