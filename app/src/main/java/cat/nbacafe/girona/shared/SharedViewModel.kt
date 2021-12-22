package cat.nbacafe.girona.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _loggedUser = MutableLiveData("")

    val loggedUser: LiveData<String> = _loggedUser

    val comanda = Array(3) {""}
    val preuItems = Array(3) {0.0}

    fun logUser(username: String) {
        _loggedUser.value = username
    }

    fun addCourse(nomItem: String, preuItem: Double, posicio: Int) {
        comanda[posicio] = nomItem
        preuItems[posicio] = preuItem
    }

    fun removeCourse(posicio: Int) {
        comanda[posicio] = ""
        preuItems[posicio] = 0.0
    }

    fun clearOrder() {
        comanda[0] = ""
        comanda[1] = ""
        comanda[2] = ""
    }

}