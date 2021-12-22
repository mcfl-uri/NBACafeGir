package cat.nbacafe.girona.ui.fragments.order.dessert

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cat.nbacafe.girona.database.daos.PostreDao
import cat.nbacafe.girona.database.entities.Postre

class DessertViewModel(
    private val dataSource: PostreDao,
    application: Application
) : AndroidViewModel(application) {

    fun getAll(): List<Postre> {
        return dataSource.getAll()
    }

}