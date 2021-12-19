package cat.nbacafe.girona.ui.fragments.order.sandwiches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.entities.Sandwich

class SandwichesFragment : Fragment() {

    var sandwiches = listOf<Sandwich>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sandwiches, container, false)
    }

}