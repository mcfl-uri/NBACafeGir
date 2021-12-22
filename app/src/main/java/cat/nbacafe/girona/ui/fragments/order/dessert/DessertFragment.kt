package cat.nbacafe.girona.ui.fragments.order.dessert

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
import cat.nbacafe.girona.database.entities.Postre
import cat.nbacafe.girona.databinding.FragmentDessertBinding
import cat.nbacafe.girona.shared.SharedViewModel

class DessertFragment : Fragment() {

    var dessert = listOf<Postre>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDessertBinding>(
            inflater,
            R.layout.fragment_dessert, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = NbaCafeDB.getInstance(application).postreDao
        val viewModelFactory = DessertViewModelFactory(dataSource, application)

        val dessertViewModel =
            ViewModelProvider(this, viewModelFactory).get(DessertViewModel::class.java)

        val sharedViewModel: SharedViewModel by activityViewModels()

        dessert = dessertViewModel.getAll()

        binding.dessertRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = DessertAdapter(dessert) {
            sharedViewModel.addCourse(it.nomPostre, it.preuPostre, 0)
            view?.findNavController()?.navigate(R.id.action_dessertFragment_to_drinkFragment)
        }

        binding.dessertRecycler.adapter = adapter

        binding.setLifecycleOwner(this)

        binding.backToSandwich.setOnClickListener { View ->
            sharedViewModel.removeCourse(1)
            view?.findNavController()?.navigate(R.id.action_dessertFragment_to_sandwichesFragment)
        }

        return binding.root
    }

}