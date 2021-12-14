package cat.nbacafe.girona.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Postre(
    @PrimaryKey(autoGenerate = true)
    val idPostre: Int,
    var nomPostre: String,
    var descPostre: String,
    var preuPostre: Float
)
