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
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.database.entities.Sandwich
import cat.nbacafe.girona.databinding.FragmentSandwichesBinding
import cat.nbacafe.girona.shared.SharedViewModel
import cat.nbacafe.girona.ui.fragments.order.FavViewModel
import cat.nbacafe.girona.ui.fragments.order.FavViewModelFactory

class SandwichesFragment : Fragment() {

    var sandwiches = listOf<Sandwich>()
    private var favSandwich: MutableList<Sandwich> = mutableListOf()
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

        val dataSource2 = NbaCafeDB.getInstance(application).favDao
        val viewModelFactory2 = FavViewModelFactory(dataSource2, application)

        val favViewModel =
            ViewModelProvider(this, viewModelFactory2).get(FavViewModel::class.java)

        sandwiches = sandwichViewModel.getAll()
        checkForFavs(favViewModel)

        binding.sandwichRecycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = SandwichAdapter(sandwiches, favSandwich)

        binding.sandwichRecycler.adapter = adapter

        adapter.setOnItemClickListener(object : SandwichAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                sharedViewModel.addCourse(
                    sandwiches[position].nomSandwich,
                    sandwiches[position].preuSandwich,
                    1
                )
                view?.findNavController()
                    ?.navigate(R.id.action_sandwichesFragment_to_dessertFragment)
            }

            override fun onFavClick(position: Int) {

                if (!favViewModel.favExists(
                        sharedViewModel.getLoggedUser(),
                        sandwiches[position].nomSandwich
                    )
                ) {
                    favViewModel.insert(
                        sharedViewModel.getLoggedUser(),
                        sandwiches[position].nomSandwich
                    )
                    Toast.makeText(
                        context, sandwiches[position].nomSandwich + " afegit a preferits",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (favViewModel.favExists(
                        sharedViewModel.getLoggedUser(),
                        sandwiches[position].nomSandwich
                    )
                ) {
                    favViewModel.delete(
                        sharedViewModel.getLoggedUser(),
                        sandwiches[position].nomSandwich
                    )
                    Toast.makeText(
                        context, sandwiches[position].nomSandwich + " eliminat de preferits",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                checkForFavs(favViewModel)
            }
        })

        binding.setLifecycleOwner(this)

        binding.cancelaComanda.setOnClickListener { View ->
            sharedViewModel.clearOrder()
            view?.findNavController()?.navigate(R.id.action_sandwichesFragment_to_homeFragment)
        }

        return binding.root
    }

    fun checkForFavs(favViewModel: FavViewModel) {
        favSandwich.clear()
        for (i in sandwiches.indices) {
            if (favViewModel.favExists(
                    sharedViewModel.getLoggedUser(), sandwiches[i].nomSandwich
                )
            ) {
                favSandwich.add(sandwiches[i])
            }
        }
    }
}