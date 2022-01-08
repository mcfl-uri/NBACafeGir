package cat.nbacafe.girona.ui.fragments.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cat.nbacafe.girona.database.daos.FavouriteDao
import cat.nbacafe.girona.database.entities.Favourite
import kotlinx.coroutines.launch

class FavViewModel(
    private val dataSource: FavouriteDao,
    application: Application
) : AndroidViewModel(application) {

    fun getAll(user: String): List<Favourite> {
        return dataSource.getAll(user)
    }

    fun insert(nomUsuari: String, nomPlat: String) {
        val fav = Favourite(nomUsuari, nomPlat)
        viewModelScope.launch {
            dataSource.insert(fav)
        }
    }

    fun delete(nomUsuari: String, nomPlat: String) {
        val fav = Favourite(nomUsuari, nomPlat)
        viewModelScope.launch {
            dataSource.delete(fav)
        }
    }

    fun favExists(nomUsuari: String, nomPlat: String): Boolean {
        return dataSource.favExists(nomUsuari, nomPlat)
    }

}