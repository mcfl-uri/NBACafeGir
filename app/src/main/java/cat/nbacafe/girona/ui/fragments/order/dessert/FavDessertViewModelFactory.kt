package cat.nbacafe.girona.ui.fragments.order.dessert

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cat.nbacafe.girona.database.daos.FavouriteDao

class FavDessertViewModelFactory(
    private val dataSource: FavouriteDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavDessertViewModel::class.java)) {
            return FavDessertViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}