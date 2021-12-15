package cat.nbacafe.girona.ui.fragments.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import cat.nbacafe.girona.R
import cat.nbacafe.girona.databinding.FirstFragmentBinding

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FirstFragmentBinding>(inflater, R.layout.first_fragment, container, false)

        binding.registratButton.setOnClickListener { View ->
            view?.findNavController()?.navigate(R.id.action_firstFragment_to_registerFragment)
        }

        return binding.root
    }
}