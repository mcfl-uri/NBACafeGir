package cat.nbacafe.girona.ui.fragments.order.sandwiches

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cat.nbacafe.girona.database.daos.SandwichDao

class SandwichViewModelFactory(
    private val dataSource: SandwichDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SandwichViewModel::class.java)) {
            return SandwichViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}