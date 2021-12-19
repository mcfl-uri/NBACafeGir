package cat.nbacafe.girona.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _loggedUser = MutableLiveData("")

    val loggedUser: LiveData<String> = _loggedUser

    val comanda = mutableListOf<Any>()

    fun logUser(username: String) {
        _loggedUser.value = username
    }

    fun addCourse(objecte: Any, posicio: Int) {
        comanda[posicio] = objecte
    }

    fun removeCourse(posicio: Int) {
        comanda.remove(posicio)
    }

    fun clearOrder() {
        comanda.clear()
    }

}