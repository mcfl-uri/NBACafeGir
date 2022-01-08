package cat.nbacafe.girona.ui.fragments.order.sandwiches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.database.entities.Postre
import cat.nbacafe.girona.database.entities.Sandwich
import cat.nbacafe.girona.databinding.FragmentSandwichesBinding
import cat.nbacafe.girona.shared.SharedViewModel

class SandwichesFragment : Fragment() {

    var sandwiches = listOf<Sandwich>()
    private var favDessert: MutableList<Postre> = mutableListOf()
    val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSandwichesBinding>(
            inflater,
            R.layout.fragment_sandwiches, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = NbaCafeDB.getInstance(application).sandwichDao
        val viewModelFactory = SandwichViewModelFactory(dataSource, application)

        val sandwichViewModel =
            ViewModelProvider(this, viewModelFactory).get(SandwichViewModel::class.java)

        sandwiches = sandwichViewModel.getAll()

        binding.sandwichRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = SandwichAdapter(sandwiches) {
            sharedViewModel.addCourse(it.nomSandwich, it.preuSandwich, 0)
            view?.findNavController()?.navigate(R.id.action_sandwichesFragment_to_dessertFragment)
        }
        binding.sandwichRecycler.adapter = adapter

        binding.setLifecycleOwner(this)

        binding.cancelaComanda.setOnClickListener { View ->
            sharedViewModel.clearOrder()
            view?.findNavController()?.navigate(R.id.action_sandwichesFragment_to_homeFragment)
        }

        return binding.root
    }

}