package cat.nbacafe.girona.ui.fragments.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cat.nbacafe.girona.database.daos.UsuariDao

class LoginViewModelFactory(
    private val dataSource: UsuariDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}