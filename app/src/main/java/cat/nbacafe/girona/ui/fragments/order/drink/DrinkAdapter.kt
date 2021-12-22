package cat.nbacafe.girona.ui.fragments.order.drink

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.entities.Beguda

class DrinkAdapter(
    val drink: List<Beguda>,
    private val clickListener: (Beguda) -> Unit
) :
    RecyclerView.Adapter<DrinkAdapter.DrinkHolder>() {

    override fun getItemCount() = drink.size

    class DrinkHolder(val view: View, clickPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        fun bind(drink: Beguda) {
            view.findViewById<TextView>(R.id.drinkNom).text = drink.nomBeguda
            view.findViewById<TextView>(R.id.drinkPreu).text =
                (drink.preuBeguda.toString() + " €")
        }

        init {
            itemView.setOnClickListener {
                clickPosition(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkHolder {

        val bh = DrinkHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.drink_cell_layout, parent, false)
        ) {
            clickListener(drink[it])
        }

        return bh
    }

    override fun onBindViewHolder(holder: DrinkHolder, position: Int) {
        holder.bind(drink[position])
    }
}