package cat.nbacafe.girona.ui.fragments.order.dessert

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.entities.Postre
import cat.nbacafe.girona.database.entities.Sandwich
import cat.nbacafe.girona.ui.fragments.order.sandwiches.SandwichAdapter

class DessertAdapter(
    val dessert: List<Postre>
) :
    RecyclerView.Adapter<DessertAdapter.DessertHolder>() {

    lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
        fun onAddFavClick(position: Int)
        fun onRemFavClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun getItemCount() = dessert.size

    class DessertHolder(val view: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(view) {
        fun bind(dessert: Postre) {
            view.findViewById<TextView>(R.id.dessertNom).text = dessert.nomPostre
            view.findViewById<TextView>(R.id.dessertDesc).text = dessert.descPostre
            view.findViewById<TextView>(R.id.dessertPreu).text =
                (dessert.preuPostre.toString() + " €")
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
            val addFav: ImageView = view.findViewById(R.id.nonFavSandwich)
            addFav.setOnClickListener {
                listener.onAddFavClick(adapterPosition)
            }
            val remFav: ImageView = view.findViewById(R.id.favSandwich)
            remFav.setOnClickListener {
                listener.onRemFavClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DessertHolder {

        val dh = LayoutInflater.from(parent.context)
                .inflate(R.layout.dessert_cell_layout, parent, false)

        return DessertHolder(dh, mListener)
    }

    override fun onBindViewHolder(holder: DessertHolder, position: Int) {
        holder.bind(dessert[position])
    }

}