package cat.nbacafe.girona.ui.fragments.order.drink

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cat.nbacafe.girona.database.daos.BegudaDao
import cat.nbacafe.girona.database.entities.Beguda

class DrinkViewModel(
    private val dataSource: BegudaDao, application: Application
) : AndroidViewModel(application) {

    fun getAll(): List<Beguda> {
        return dataSource.getAll()
    }

}