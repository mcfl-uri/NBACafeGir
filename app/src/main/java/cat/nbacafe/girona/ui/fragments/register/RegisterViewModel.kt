package cat.nbacafe.girona.ui.fragments.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cat.nbacafe.girona.database.daos.UsuariDao
import cat.nbacafe.girona.database.entities.Usuari

class RegisterViewModel(
    private val dataSource: UsuariDao, application: Application
) : AndroidViewModel(application) {

    private suspend fun insert(usuari: Usuari) {
        dataSource.insert(usuari)
    }

    private fun userExists(usuari: String) {
        dataSource.userExists(usuari)
    }

}