package cat.nbacafe.girona.ui.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.databinding.LoginFragmentBinding
import cat.nbacafe.girona.ui.fragments.register.RegisterViewModel
import cat.nbacafe.girona.ui.fragments.register.RegisterViewModelFactory

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
        val viewModelFactory = LoginViewModelFactory(dataSource, application)

        val registerViewModel =
            ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.cancelaButton.setOnClickListener { View ->
            view?.findNavController()?.navigate(R.id.action_loginFragment_to_firstFragment)
        }

        binding.endavantButton.setOnClickListener { View ->

            view?.findNavController()?.navigate(R.id.action_loginFragment_to_homeFragment)
        }

        return binding.root
    }

}