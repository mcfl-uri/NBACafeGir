package cat.nbacafe.girona.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sandwich(
    @PrimaryKey(autoGenerate = true)
    val idSandwich: Int,
    @ColumnInfo(name = "nom_sandwich")
    var nomSandwich: String,
    @ColumnInfo(name = "desc_sandwich")
    var descSandwich: String,
    @ColumnInfo(name = "preu_sandwich")
    var preuSandwich: Double
)
