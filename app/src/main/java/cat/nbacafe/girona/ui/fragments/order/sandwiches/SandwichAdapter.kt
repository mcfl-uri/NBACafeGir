package cat.nbacafe.girona.ui.fragments.order.sandwiches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.entities.Sandwich

class SandwichAdapter(
    val sandwich: List<Sandwich>,
    private val clickListener: (String) -> Unit
) :
    RecyclerView.Adapter<SandwichAdapter.SandwichHolder>() {

    override fun getItemCount() = sandwich.size

    class SandwichHolder(val view: View, clickPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bind(sandwich: Sandwich) {
            view.findViewById<TextView>(R.id.sandwichNom).text = sandwich.nomSandwich
            view.findViewById<TextView>(R.id.sandwichDesc).text = sandwich.descSandwich
            view.findViewById<TextView>(R.id.sandwichPreu).text =
                (sandwich.preuSandwich.toString() + " €")
        }

        init {
            itemView.setOnClickListener {
                clickPosition(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SandwichHolder {

        val sh = SandwichHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.sandwich_cell_layout, parent, false)
        ) {
            clickListener(sandwich[it].nomSandwich)
        }

        return sh
    }

    override fun onBindViewHolder(holder: SandwichHolder, position: Int) {
        holder.bind(sandwich[position])
    }

}