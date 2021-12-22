package cat.nbacafe.girona.ui.fragments.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cat.nbacafe.girona.database.daos.ComandaDao

class CartViewModel(
    private val dataSource: ComandaDao,
    application: Application
) : AndroidViewModel(application) {

    fun getIdNova(): Int {
        return dataSource.getLastComanda()+1
    }

}