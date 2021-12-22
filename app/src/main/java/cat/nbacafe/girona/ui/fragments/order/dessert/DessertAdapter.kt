package cat.nbacafe.girona.ui.fragments.order.dessert

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.entities.Postre
import cat.nbacafe.girona.database.entities.Sandwich
import cat.nbacafe.girona.ui.fragments.order.sandwiches.SandwichAdapter

class DessertAdapter(
    val dessert: List<Postre>,
    private val clickListener: (Postre) -> Unit
) :
    RecyclerView.Adapter<DessertAdapter.DessertHolder>() {

    override fun getItemCount() = dessert.size

    class DessertHolder(val view: View, clickPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bind(dessert: Postre) {
            view.findViewById<TextView>(R.id.dessertNom).text = dessert.nomPostre
            view.findViewById<TextView>(R.id.dessertDesc).text = dessert.descPostre
            view.findViewById<TextView>(R.id.dessertPreu).text =
                (dessert.preuPostre.toString() + " €")
        }

        init {
            itemView.setOnClickListener {
                clickPosition(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DessertHolder {

        val dh = DessertHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.dessert_cell_layout, parent, false)
        ) {
            clickListener(dessert[it])
        }

        return dh
    }

    override fun onBindViewHolder(holder: DessertHolder, position: Int) {
        holder.bind(dessert[position])
    }

}