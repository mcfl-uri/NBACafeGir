package cat.nbacafe.girona.ui.fragments.user.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.databinding.LoginFragmentBinding
import cat.nbacafe.girona.shared.SharedViewModel
import cat.nbacafe.girona.ui.fragments.user.UserViewModel
import cat.nbacafe.girona.ui.fragments.user.UserViewModelFactory

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<LoginFragmentBinding>(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = NbaCafeDB.getInstance(application).usuariDao
        val viewModelFactory = UserViewModelFactory(dataSource, application)

        val userViewModel =
            ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        val sharedViewModel: SharedViewModel by activityViewModels()

        binding.setLifecycleOwner(this)

        binding.cancelaButton.setOnClickListener { View ->
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_firstFragment)
        }

        binding.endavantButton.setOnClickListener { View ->
            if (userViewModel.userExists(binding.loginUser.text.toString())) {
                if (userViewModel.checkLogin(
                        binding.loginUser.text.toString(),
                        binding.loginPassword.text.toString()
                    )
                ) {
                    sharedViewModel.logUser(binding.loginUser.text.toString())
                    view?.findNavController()?.navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    Toast.makeText(context, "Contrasenya incorrecte", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(context, "Usuari inexistent", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return binding.root
    }

}