package cat.nbacafe.girona.ui.fragments.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.entities.Comanda

class HistoryAdapter(
    val comandes: List<Comanda>
) : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    override fun getItemCount() = comandes.size

    class HistoryHolder(val view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(comanda: Comanda) {

            view.findViewById<TextView>(R.id.sandwichNom).text = comanda.sandwichNom
            view.findViewById<TextView>(R.id.sandwichDesc).text = ""
            view.findViewById<TextView>(R.id.sandwichPreu).text = ""
            view.findViewById<ImageView>(R.id.favSandwichBtn).visibility = View.GONE

            view.findViewById<TextView>(R.id.dessertNom).text = comanda.postreNom
            view.findViewById<TextView>(R.id.dessertPreu).text = ""
            view.findViewById<TextView>(R.id.dessertDesc).text = ""
            view.findViewById<ImageView>(R.id.favDessertBtn).visibility = View.GONE

            view.findViewById<TextView>(R.id.drinkNom).text = comanda.begudaNom
            view.findViewById<TextView>(R.id.drinkPreu).text = ""
            view.findViewById<ImageView>(R.id.favDrinkBtn).visibility = View.GONE

            view.findViewById<TextView>(R.id.totalComanda).text =
                "Preu total: " + comanda.totalComanda.toString() + " €"
            view.findViewById<TextView>(R.id.dataComanda).text =
                "Data: " + comanda.dataComanda
            view.findViewById<TextView>(R.id.numComanda).text =
                "Codi de comanda: " + comanda.idComanda

        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistoryHolder(layoutInflater.inflate(R.layout.comandes_cell_layout, parent, false))
    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(comandes[position])
    }

}