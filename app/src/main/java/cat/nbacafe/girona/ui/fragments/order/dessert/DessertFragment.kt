package cat.nbacafe.girona.ui.fragments.order.dessert

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
import cat.nbacafe.girona.database.entities.Postre
import cat.nbacafe.girona.databinding.FragmentDessertBinding
import cat.nbacafe.girona.shared.SharedViewModel

class DessertFragment : Fragment() {

    var dessert = listOf<Postre>()
    var favDessert: MutableList<Postre> = mutableListOf()
    val sharedViewModel: SharedViewModel by activityViewModels()

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

        val dataSource2 = NbaCafeDB.getInstance(application).favDao
        val viewModelFactory2 = FavDessertViewModelFactory(dataSource2, application)

        val favDessertViewModel =
            ViewModelProvider(this, viewModelFactory2).get(FavDessertViewModel::class.java)

        dessert = dessertViewModel.getAll()
        checkForFavs(favDessertViewModel)

        binding.dessertRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = DessertAdapter(dessert, favDessert, sharedViewModel.getLoggedUser())

        binding.dessertRecycler.adapter = adapter

        adapter.setOnItemClickListener(object : DessertAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                sharedViewModel.addCourse(
                    dessert[position].nomPostre,
                    dessert[position].preuPostre,
                    1
                )
                view?.findNavController()?.navigate(R.id.action_dessertFragment_to_drinkFragment)
            }

            override fun onFavClick(position: Int) {
                Toast.makeText(context, dessert[position].nomPostre, Toast.LENGTH_LONG).show()
                if (!favDessertViewModel.favExists(sharedViewModel.getLoggedUser(), dessert[position].nomPostre))
                    favDessertViewModel.insert(sharedViewModel.getLoggedUser(), dessert[position].nomPostre)

                else if (favDessertViewModel.favExists(sharedViewModel.getLoggedUser(), dessert[position].nomPostre))
                    favDessertViewModel.delete(sharedViewModel.getLoggedUser(), dessert[position].nomPostre)

                checkForFavs(favDessertViewModel)
                adapter.notifyDataSetChanged()
            }

        })

        binding.setLifecycleOwner(this)

        binding.backToSandwich.setOnClickListener { View ->
            sharedViewModel.removeCourse(1)
            view?.findNavController()?.navigate(R.id.action_dessertFragment_to_sandwichesFragment)
        }

        return binding.root
    }

    fun checkForFavs(favDessertViewModel: FavDessertViewModel) {
        favDessert.clear()
        for (i in dessert.indices) {
            if (favDessertViewModel.favExists(sharedViewModel.getLoggedUser(), dessert[i].nomPostre)) {
                favDessert.add(dessert[i])
            }
        }
    }

}