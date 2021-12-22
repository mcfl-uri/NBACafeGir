package cat.nbacafe.girona.ui.fragments.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.databinding.CartFragmentBinding
import cat.nbacafe.girona.databinding.FragmentDrinkBinding
import cat.nbacafe.girona.shared.SharedViewModel
import cat.nbacafe.girona.ui.fragments.order.dessert.DessertViewModel
import cat.nbacafe.girona.ui.fragments.order.dessert.DessertViewModelFactory

class CartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<CartFragmentBinding>(
            inflater,
            R.layout.cart_fragment, container, false
        )

        val sharedViewModel: SharedViewModel by activityViewModels()

        val application = requireNotNull(this.activity).application
        val dataSource = NbaCafeDB.getInstance(application).comandaDao
        val viewModelFactory = CartViewModelFactory(dataSource, application)

        val cartViewModel =
            ViewModelProvider(this, viewModelFactory).get(CartViewModel::class.java)

        binding.comandaId.text = "Número de comanda: " + cartViewModel.getIdNova().toString()
        sharedViewModel.loggedUser.observe(this, { loggedUser ->
            binding.comandaUsuari.text = "Usuari: $loggedUser"
        })

        binding.sandwichIncluded.sandwichNom.text = sharedViewModel.comanda[0]
        binding.sandwichIncluded.sandwichDesc.text = ""
        binding.sandwichIncluded.sandwichPreu.text = sharedViewModel.preuItems[0].toString() + " €"

        binding.dessertincluded.dessertNom.text = sharedViewModel.comanda[1]
        binding.dessertincluded.dessertDesc.text = ""
        binding.dessertincluded.dessertPreu.text = sharedViewModel.preuItems[1].toString() + " €"

        binding.drinkIncluded.drinkNom.text = sharedViewModel.comanda[2]
        binding.drinkIncluded.drinkPreu.text = sharedViewModel.preuItems[2].toString() + " €"

        return binding.root
    }

}