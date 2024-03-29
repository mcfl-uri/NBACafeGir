package cat.nbacafe.girona.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import cat.nbacafe.girona.NbaCafeApp.Companion.preferences
import cat.nbacafe.girona.R
import cat.nbacafe.girona.databinding.HomeFragmentBinding
import cat.nbacafe.girona.shared.SharedViewModel

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<HomeFragmentBinding>(inflater,
            R.layout.home_fragment,container,false)

        binding.newComandaButton.setOnClickListener { View ->
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_sandwichesFragment)
        }

        binding.newHistoryButton.setOnClickListener { View ->
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_historyFragment)
        }

        binding.newCancelaButton.setOnClickListener { View ->
            preferences.saveName("")
            val sharedViewModel: SharedViewModel by activityViewModels()
            sharedViewModel.logUser(preferences.getName())
            view?.findNavController()?.navigate(R.id.action_homeFragment_to_firstFragment)
        }

        return binding.root
    }

}