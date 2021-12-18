package cat.nbacafe.girona.ui.fragments.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cat.nbacafe.girona.database.NbaCafeDB
import cat.nbacafe.girona.database.daos.UsuariDao

class LoginViewModel(
    private val dataSource: UsuariDao, application: Application
) : AndroidViewModel(application) {

    fun checkLogin(usuariNom: String, usuariPass: String): Boolean {
        return dataSource.checkLogin(usuariNom, usuariPass)
    }

}