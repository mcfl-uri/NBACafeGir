package cat.nbacafe.girona.ui.fragments.order.drink

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
import cat.nbacafe.girona.database.entities.Beguda
import cat.nbacafe.girona.databinding.FragmentDrinkBinding
import cat.nbacafe.girona.shared.SharedViewModel
import cat.nbacafe.girona.ui.fragments.order.dessert.DessertAdapter
import cat.nbacafe.girona.ui.fragments.order.dessert.DessertViewModel
import cat.nbacafe.girona.ui.fragments.order.dessert.DessertViewModelFactory

class DrinkFragment : Fragment() {

    var drink = listOf<Beguda>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDrinkBinding>(
            inflater,
            R.layout.fragment_drink, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = NbaCafeDB.getInstance(application).begudaDao
        val viewModelFactory = DrinkViewModelFactory(dataSource, application)

        val drinkViewModel =
            ViewModelProvider(this, viewModelFactory).get(DrinkViewModel::class.java)

        val sharedViewModel: SharedViewModel by activityViewModels()

        drink = drinkViewModel.getAll()

        binding.drinkRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = DrinkAdapter(drink) {
            sharedViewModel.addCourse(it.nomBeguda, it.preuBeguda, 0)
            view?.findNavController()?.navigate(R.id.action_sandwichesFragment_to_dessertFragment)
        }

        binding.drinkRecycler.adapter = adapter

        binding.setLifecycleOwner(this)

        binding.backToDessert.setOnClickListener { View ->
            sharedViewModel.removeCourse(2)
            view?.findNavController()?.navigate(R.id.action_drinkFragment_to_dessertFragment)
        }

        return binding.root
    }

}