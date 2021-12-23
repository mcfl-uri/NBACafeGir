package cat.nbacafe.girona.ui.fragments.cart

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
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.databinding.CartFragmentBinding
import cat.nbacafe.girona.shared.SharedViewModel

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

        val preuTotal = sharedViewModel.preuItems[0] +
                sharedViewModel.preuItems[1] +
                sharedViewModel.preuItems[2]

        binding.comandaPreuEuros.text = "$preuTotal €"

        binding.cancelaComanda.setOnClickListener { View ->
            sharedViewModel.clearOrder()
            Toast.makeText(context, "Comanda cancel·lada", Toast.LENGTH_LONG).show()
            view?.findNavController()?.navigate(R.id.action_cartFragment_to_homeFragment)
        }

        binding.confirmaComanda.setOnClickListener { View ->
            cartViewModel.insert(
                binding.comandaUsuari.text.toString().substring(
                    binding.comandaUsuari.text.toString().indexOf(' ') + 1
                ),
                sharedViewModel.comanda[0],
                sharedViewModel.comanda[1],
                sharedViewModel.comanda[2],
                preuTotal
            )

            Toast.makeText(context, "Comanda realitzada", Toast.LENGTH_LONG).show()
            view?.findNavController()?.navigate(R.id.action_cartFragment_to_homeFragment)
        }

        return binding.root
    }

}