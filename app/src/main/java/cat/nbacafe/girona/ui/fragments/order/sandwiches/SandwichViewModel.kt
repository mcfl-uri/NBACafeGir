package cat.nbacafe.girona.ui.fragments.order.sandwiches

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cat.nbacafe.girona.database.daos.SandwichDao
import cat.nbacafe.girona.database.entities.Sandwich

class SandwichViewModel(
    private val dataSource: SandwichDao, application: Application
) : AndroidViewModel(application) {

    fun getAll(): List<Sandwich> {
        return dataSource.getAll()
    }

}