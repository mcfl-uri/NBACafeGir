package cat.nbacafe.girona.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Beguda(
    @PrimaryKey(autoGenerate = true)
    val idBeguda: Int,
    var nomBeguda: String,
    var descBeguda: String,
    var preuBeguda: Float
)
