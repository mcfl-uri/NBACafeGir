package cat.nbacafe.girona.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Beguda(
    @PrimaryKey(autoGenerate = true)
    val idBeguda: Int,
    @ColumnInfo(name = "nom_beguda")
    var nomBeguda: String,
    @ColumnInfo(name = "preu_beguda")
    var preuBeguda: Double
)
