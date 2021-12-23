package cat.nbacafe.girona.ui.fragments.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import cat.nbacafe.girona.database.daos.ComandaDao
import cat.nbacafe.girona.database.entities.Comanda
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CartViewModel(
    private val dataSource: ComandaDao,
    application: Application
) : AndroidViewModel(application) {

    fun getIdNova(): Int {
        return dataSource.getLastComanda()+1
    }

    fun insert(usuari: String, sandwich: String, postre: String, beguda: String, preu: Double) {

        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm")
        val data = sdf.format(Date())

        val comanda = Comanda(0, usuari, sandwich, postre, beguda, preu, data)

        viewModelScope.launch {
            dataSource.insert(comanda)
        }
    }

}