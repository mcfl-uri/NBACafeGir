package cat.nbacafe.girona.ui.fragments.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cat.nbacafe.girona.database.daos.ComandaDao
import cat.nbacafe.girona.database.entities.Comanda
import kotlinx.coroutines.launch

class CartViewModel(
    private val dataSource: ComandaDao,
    application: Application
) : AndroidViewModel(application) {

    fun getIdNova(): Int {
        return dataSource.getLastComanda()+1
    }

    fun insert(usuari: String, sandwich: String, postre: String, beguda: String, preu: Double) {

        val comanda = Comanda(0, usuari, sandwich, postre, beguda, preu)

        viewModelScope.launch {
            dataSource.insert(comanda)
        }
    }

}