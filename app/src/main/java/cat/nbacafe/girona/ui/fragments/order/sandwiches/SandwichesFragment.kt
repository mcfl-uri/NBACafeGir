package cat.nbacafe.girona.ui.fragments.order.sandwiches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.database.entities.Sandwich
import cat.nbacafe.girona.databinding.FragmentSandwichesBinding

class SandwichesFragment : Fragment() {

    var sandwiches = listOf<Sandwich>()
    lateinit var mRecyclerView : RecyclerView
    val mAdapter : SandwichAdapter = SandwichAdapter(sandwiches)
    lateinit var _binding: FragmentSandwichesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSandwichesBinding>(
            inflater,
            R.layout.fragment_sandwiches, container, false
        )

        _binding = binding

        val application = requireNotNull(this.activity).application
        val dataSource = NbaCafeDB.getInstance(application).sandwichDao
        val viewModelFactory = SandwichViewModelFactory(dataSource, application)

        val sandwichViewModel =
            ViewModelProvider(this, viewModelFactory).get(SandwichViewModel::class.java)

        binding.setLifecycleOwner(this)

        sandwiches = sandwichViewModel.getAll()

        initRecycler()

        return binding.root
    }

    fun initRecycler() {
        mRecyclerView = _binding.sandwichRecycler
        mRecyclerView.adapter = mAdapter
    }

}