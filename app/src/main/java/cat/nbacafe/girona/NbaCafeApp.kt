package cat.nbacafe.girona

import android.app.Application
import cat.nbacafe.girona.shared.SharedPreferences

class NbaCafeApp : Application() {

    companion object {
        lateinit var preferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        preferences = SharedPreferences(applicationContext)
    }

}