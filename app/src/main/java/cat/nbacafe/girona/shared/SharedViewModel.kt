package cat.nbacafe.girona.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private var _loggedUser = MutableLiveData("")

    val loggedUser: LiveData<String> = _loggedUser

    fun logUser(username: String) {
        _loggedUser.value = username
    }

}