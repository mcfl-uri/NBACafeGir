package cat.nbacafe.girona.ui.fragments.order.sandwiches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.entities.Sandwich

class SandwichAdapter (val sandwich: List<Sandwich>) : RecyclerView.Adapter<SandwichAdapter.SandwichHolder>() {

    override fun getItemCount() = sandwich.size

    class SandwichHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(sandwich: Sandwich) {
            view.findViewById<TextView>(R.id.sandwichNom).text = sandwich.nomSandwich
            view.findViewById<TextView>(R.id.sandwichDesc).text = sandwich.descSandwich
            view.findViewById<TextView>(R.id.sandwichPreu).text = (sandwich.preuSandwich.toString()+" €")
        }

        companion object {
            fun from(parent: ViewGroup): SandwichHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.sandwich_cell_layout, parent, false)

                return SandwichHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SandwichHolder {
        return SandwichHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SandwichHolder, position: Int) {
        holder.bind(sandwich[position])
    }

}