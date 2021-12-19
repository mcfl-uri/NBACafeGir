package cat.nbacafe.girona.ui.fragments.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import cat.nbacafe.girona.database.daos.SandwichDao

class OrderViewModel(
    private val dataSource: SandwichDao, application: Application
) : AndroidViewModel(application) {



}