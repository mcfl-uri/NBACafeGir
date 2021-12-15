package cat.nbacafe.girona.ui.fragments.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<RegisterFragmentBinding>(inflater, R.layout.register_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = NbaCafeDB.getInstance(application).usuariDao
        val viewModelFactory = RegisterViewModelFactory(dataSource, application)

        val registerViewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)

        

        return binding.root
    }

}