package cat.nbacafe.girona.ui.fragments.about

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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
import cat.nbacafe.girona.ui.MainActivity
import com.google.android.gms.common.wrappers.Wrappers.packageManager




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

            val pm: PackageManager = requireActivity().packageManager

            if (isInstalled("com.google.android.gms", pm)) {

                val intent = Intent(Intent.ACTION_VIEW)
                intent.type = "message/rfc822"
                intent.data = Uri.parse("mailto:"+binding.textMail)
                startActivity(intent)

            } else {

                val uri = Uri.parse("http://www.gmail.com")
                val gmail = Intent(Intent.ACTION_VIEW, uri)
                startActivity(gmail)

            }
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

    fun isInstalled(packageName: String, pm: PackageManager): Boolean {
        return try {
            pm.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

}