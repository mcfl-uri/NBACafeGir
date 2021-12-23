package cat.nbacafe.girona.ui.fragments.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cat.nbacafe.girona.database.daos.ComandaDao
import cat.nbacafe.girona.database.entities.Comanda
import cat.nbacafe.girona.database.entities.Postre

class HistoryViewModel(
    private val dataSource: ComandaDao,
    application: Application
) : AndroidViewModel(application) {

    fun getComandesUser(username: String): List<Comanda> {
        return dataSource.getComandesUser(username)
    }

}