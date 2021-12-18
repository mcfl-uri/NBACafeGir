package cat.nbacafe.girona.ui.fragments.user

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cat.nbacafe.girona.database.daos.UsuariDao

class UserViewModelFactory(
    private val dataSource: UsuariDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}