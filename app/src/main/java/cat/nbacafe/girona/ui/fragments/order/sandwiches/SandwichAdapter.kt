package cat.nbacafe.girona.ui.fragments.order.sandwiches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.nbacafe.girona.R
import cat.nbacafe.girona.database.entities.Sandwich

class SandwichAdapter (val sandwich: List<Sandwich>) : RecyclerView.Adapter<SandwichAdapter.SandwichHolder>() {


    class SandwichHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun render(sandwich: Sandwich) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SandwichHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SandwichHolder(layoutInflater.inflate(R.layout.sandwich_cell_layout, parent, false))
    }

    override fun onBindViewHolder(holder: SandwichHolder, position: Int) {
        holder.render(sandwich[position])
    }

    override fun getItemCount(): Int = sandwich.size
}