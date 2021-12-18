package cat.nbacafe.girona.ui.fragments.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cat.nbacafe.girona.database.daos.UsuariDao
import cat.nbacafe.girona.database.entities.Usuari
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val dataSource: UsuariDao, application: Application
) : AndroidViewModel(application) {

    fun insert(nomUsuari: String, emailUsuari: String, passUsuari: String) {
        val usuari = Usuari(nomUsuari, emailUsuari, passUsuari)
        viewModelScope.launch {
            dataSource.insert(usuari)
        }
    }

    fun userExists(usuariNom: String): Boolean {
        return dataSource.userExists(usuariNom)
    }

}