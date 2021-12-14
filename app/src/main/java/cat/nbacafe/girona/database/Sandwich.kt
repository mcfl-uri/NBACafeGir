package cat.nbacafe.girona.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sandwich(
    @PrimaryKey(autoGenerate = true)
    val idSandwich: Int,
    var nomSandwich: String,
    var descSandwich: String,
    var preuSandwich: Float
)
