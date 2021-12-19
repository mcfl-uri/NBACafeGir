package cat.nbacafe.girona.ui.fragments.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cat.nbacafe.girona.database.daos.UsuariDao
import cat.nbacafe.girona.database.entities.Usuari
import kotlinx.coroutines.launch

class UserViewModel(
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

    fun checkLogin(usuariNom: String, usuariPass: String): Boolean {
        return dataSource.checkLogin(usuariNom, usuariPass)
    }

}