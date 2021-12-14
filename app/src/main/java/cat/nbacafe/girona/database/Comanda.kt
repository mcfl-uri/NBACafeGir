package cat.nbacafe.girona.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comanda(
    @PrimaryKey(autoGenerate = true)
    val idComanda: Int,
    var nomUsuariCo: String,
    var sandwich: Sandwich,
    var postre: Postre,
    var beguda: Beguda
)
