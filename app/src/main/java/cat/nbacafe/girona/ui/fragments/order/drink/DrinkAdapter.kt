package cat.nbacafe.girona.ui.fragments.order.drink

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.entities.Beguda

class DrinkAdapter(
    val drink: List<Beguda>,
    val favDrink: List<Beguda>
) :
    RecyclerView.Adapter<DrinkAdapter.DrinkHolder>() {

    lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
        fun onFavClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun getItemCount() = drink.size

    class DrinkHolder(val view: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(view) {

        val favBtn: ImageView = view.findViewById(R.id.favDrinkBtn)

        fun bind(drink: Beguda) {
            view.findViewById<TextView>(R.id.drinkNom).text = drink.nomBeguda
            view.findViewById<TextView>(R.id.drinkPreu).text =
                (drink.preuBeguda.toString() + " €")
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

            favBtn.setOnClickListener {
                listener.onFavClick(adapterPosition)
                if (favBtn.tag.toString() == "nonfav") {
                    favBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                    favBtn.tag = "fav"
                } else if (favBtn.tag.toString() == "fav") {
                    favBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    favBtn.tag = "nonfav"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkHolder {

        val bh = LayoutInflater.from(parent.context)
                .inflate(R.layout.drink_cell_layout, parent, false)

        return DrinkHolder(bh, mListener)
    }

    override fun onBindViewHolder(holder: DrinkHolder, position: Int) {
        for (i in favDrink.indices) {
            if (drink[position] == favDrink[i]) {
                holder.favBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                holder.favBtn.tag = "fav"
                break
            } else {
                holder.favBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                holder.favBtn.tag = "nonfav"
            }
        }
        holder.bind(drink[position])
    }
}