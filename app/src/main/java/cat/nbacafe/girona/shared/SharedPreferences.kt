package cat.nbacafe.girona.shared

import android.content.Context

class SharedPreferences (context: Context) {

    val SHARED_NAME = "shared_prefs"
    val SHARED_USER_NAME = ""

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveName(username: String) {
        storage.edit().putString(SHARED_USER_NAME, username).apply()
    }

    fun getName() : String {
        return storage.getString(SHARED_USER_NAME, "")!!
    }

}