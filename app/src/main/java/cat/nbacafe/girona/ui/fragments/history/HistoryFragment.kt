package cat.nbacafe.girona.ui.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.database.entities.Comanda
import cat.nbacafe.girona.databinding.HistoryFragmentBinding
import cat.nbacafe.girona.shared.SharedViewModel

class HistoryFragment : Fragment() {

    var comandes = listOf<Comanda>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<HistoryFragmentBinding>(
            inflater,
            R.layout.history_fragment, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = NbaCafeDB.getInstance(application).comandaDao
        val viewModelFactory = HistoryViewModelFactory(dataSource, application)

        val historyViewModel =
            ViewModelProvider(this, viewModelFactory).get(HistoryViewModel::class.java)

        val sharedViewModel: SharedViewModel by activityViewModels()

        var username = sharedViewModel.getLoggedUser()

        comandes = historyViewModel.getComandesUser(username)

        if (comandes.isEmpty()) {
            binding.comandesText.text = "Encara no tens comandes"
        }

        binding.comandesRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = HistoryAdapter(comandes)

        binding.comandesRecycler.adapter = adapter

        binding.setLifecycleOwner(this)

        binding.enrereButton.setOnClickListener { View ->
            if (R.id.navHeaderText.toString() != "")
                view?.findNavController()?.navigate(R.id.action_historyFragment_to_homeFragment)
            else
                view?.findNavController()?.navigate(R.id.action_historyFragment_to_firstFragment)
        }

        return binding.root
    }

}