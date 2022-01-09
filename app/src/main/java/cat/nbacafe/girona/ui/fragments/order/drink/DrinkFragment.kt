package cat.nbacafe.girona.ui.fragments.order.drink

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
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.database.entities.Beguda
import cat.nbacafe.girona.databinding.FragmentDrinkBinding
import cat.nbacafe.girona.shared.SharedViewModel
import cat.nbacafe.girona.ui.fragments.order.FavViewModel
import cat.nbacafe.girona.ui.fragments.order.FavViewModelFactory

class DrinkFragment : Fragment() {

    var drink = listOf<Beguda>()
    private var favDrink: MutableList<Beguda> = mutableListOf()
    val sharedViewModel: SharedViewModel by activityViewModels()

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

        val dataSource2 = NbaCafeDB.getInstance(application).favDao
        val viewModelFactory2 = FavViewModelFactory(dataSource2, application)

        val favViewModel =
            ViewModelProvider(this, viewModelFactory2).get(FavViewModel::class.java)

        drink = drinkViewModel.getAll()
        checkForFavs(favViewModel)

        binding.drinkRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = DrinkAdapter(drink, favDrink)

        binding.drinkRecycler.adapter = adapter

        adapter.setOnItemClickListener(object : DrinkAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                sharedViewModel.addCourse(
                    drink[position].nomBeguda,
                    drink[position].preuBeguda,
                    2
                )
                view?.findNavController()
                    ?.navigate(R.id.action_drinkFragment_to_cartFragment)
            }

            override fun onFavClick(position: Int) {

                if (!favViewModel.favExists(
                        sharedViewModel.getLoggedUser(),
                        drink[position].nomBeguda
                    )
                ) {
                    favViewModel.insert(
                        sharedViewModel.getLoggedUser(),
                        drink[position].nomBeguda
                    )
                    Toast.makeText(
                        context, drink[position].nomBeguda + " afegida a preferits",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (favViewModel.favExists(
                        sharedViewModel.getLoggedUser(),
                        drink[position].nomBeguda
                    )
                ) {
                    favViewModel.delete(
                        sharedViewModel.getLoggedUser(),
                        drink[position].nomBeguda
                    )
                    Toast.makeText(
                        context, drink[position].nomBeguda + " eliminada de preferits",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                checkForFavs(favViewModel)
            }
        })

        binding.setLifecycleOwner(this)

        binding.backToDessert.setOnClickListener { View ->
            sharedViewModel.removeCourse(2)
            view?.findNavController()?.navigate(R.id.action_drinkFragment_to_dessertFragment)
        }

        return binding.root
    }

    fun checkForFavs(favViewModel: FavViewModel) {
        favDrink.clear()
        for (i in drink.indices) {
            if (favViewModel.favExists(
                    sharedViewModel.getLoggedUser(), drink[i].nomBeguda
                )
            ) {
                favDrink.add(drink[i])
            }
        }
    }
}