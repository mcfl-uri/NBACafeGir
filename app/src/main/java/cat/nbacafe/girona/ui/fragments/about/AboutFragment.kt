package cat.nbacafe.girona.ui.fragments.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import cat.nbacafe.girona.R
import cat.nbacafe.girona.databinding.FragmentAboutBinding
import cat.nbacafe.girona.shared.SharedViewModel

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAboutBinding>(inflater, R.layout.fragment_about, container, false)

        binding.telefonRow.setOnClickListener { View ->
            val trucada = Intent(Intent.ACTION_DIAL)
            trucada.data = Uri.parse("tel:" + binding.textTelefon.text.toString())
            startActivity(trucada)
        }

        binding.emailRow.setOnClickListener { View ->
            val uri = Uri.parse("http://www.gmail.com")
            val gmail = Intent(Intent.ACTION_VIEW, uri)
            startActivity(gmail)
        }

        binding.locationRow.setOnClickListener { View ->
            view?.findNavController()?.navigate(R.id.action_aboutFragment_to_mapsFragment)
        }

        binding.backFromAboutButton.setOnClickListener { View ->
            val sharedViewModel: SharedViewModel by activityViewModels()
            if (sharedViewModel.getLoggedUser() != "") {
                view?.findNavController()?.navigate(R.id.action_aboutFragment_to_homeFragment)
            } else {
                view?.findNavController()?.navigate(R.id.action_aboutFragment_to_firstFragment)
            }
        }

        return binding.root
    }

}