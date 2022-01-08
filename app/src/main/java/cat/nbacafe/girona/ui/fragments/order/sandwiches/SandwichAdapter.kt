package cat.nbacafe.girona.ui.fragments.order.sandwiches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.entities.Sandwich

class SandwichAdapter(
    val sandwich: List<Sandwich>,
    val favSandwich: List<Sandwich>
) :
    RecyclerView.Adapter<SandwichAdapter.SandwichHolder>() {

    lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
        fun onFavClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun getItemCount() = sandwich.size

    class SandwichHolder(val view: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(view) {

        val favBtn: ImageView = view.findViewById(R.id.favSandwichBtn)

        fun bind(sandwich: Sandwich) {
            view.findViewById<TextView>(R.id.sandwichNom).text = sandwich.nomSandwich
            view.findViewById<TextView>(R.id.sandwichDesc).text = sandwich.descSandwich
            view.findViewById<TextView>(R.id.sandwichPreu).text =
                (sandwich.preuSandwich.toString() + " €")
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SandwichHolder {

        val sh = LayoutInflater.from(parent.context)
            .inflate(R.layout.sandwich_cell_layout, parent, false)

        return SandwichHolder(sh, mListener)
    }

    override fun onBindViewHolder(holder: SandwichHolder, position: Int) {
        for (i in favSandwich.indices) {
            if (sandwich[position] == favSandwich[i]) {
                holder.favBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                holder.favBtn.tag = "fav"
            }
        }
        holder.bind(sandwich[position])
    }

}