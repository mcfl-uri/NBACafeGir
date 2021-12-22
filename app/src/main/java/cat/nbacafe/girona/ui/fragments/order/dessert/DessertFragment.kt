package cat.nbacafe.girona.ui.fragments.order.dessert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.entities.Postre
import cat.nbacafe.girona.databinding.FragmentDessertBinding

class DessertFragment : Fragment() {

    var dessert = listOf<Postre>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentDessertBinding>(
            inflater,
            R.layout.fragment_dessert, container, false
        )



        return binding.root
    }

}