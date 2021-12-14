package cat.nbacafe.girona.ui.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import cat.nbacafe.girona.R
import cat.nbacafe.girona.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<LoginFragmentBinding>(inflater, R.layout.login_fragment, container, false)

        

        return binding.root
    }

}