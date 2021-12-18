package cat.nbacafe.girona.ui.fragments.user.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.databinding.RegisterFragmentBinding
import cat.nbacafe.girona.ui.fragments.user.UserViewModel
import cat.nbacafe.girona.ui.fragments.user.UserViewModelFactory

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<RegisterFragmentBinding>(
            inflater,
            R.layout.register_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = NbaCafeDB.getInstance(application).usuariDao
        val viewModelFactory = UserViewModelFactory(dataSource, application)

        val userViewModel =
            ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.cancelaButton.setOnClickListener { View ->
            view?.findNavController()?.navigate(R.id.action_registerFragment_to_firstFragment)
        }

        binding.endavantButton.setOnClickListener { View ->
            val email = binding.email.text.toString()
            val username = binding.registerUser.text.toString()
            val password = binding.registerPassword.text.toString()
            val confPassword = binding.confirmPassword.text.toString()
            if (email != "" && username != "") {
                if (userViewModel.userExists(username)) {
                    Toast.makeText(context, "Aquest nom d'usuari ja existeix", Toast.LENGTH_LONG)
                        .show()
                } else if (password == confPassword) {

                    userViewModel.insert(username, email, password)

                } else {
                    Toast.makeText(context, "Les contrassenyes no coincideixen", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        return binding.root
    }

}