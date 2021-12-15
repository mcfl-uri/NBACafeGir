package cat.nbacafe.girona.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Postre(
    @PrimaryKey(autoGenerate = true)
    val idPostre: Int,
    @ColumnInfo(name = "nom_postre")
    var nomPostre: String,
    @ColumnInfo(name = "desc_postre")
    var descPostre: String,
    @ColumnInfo(name = "preu_postre")
    var preuPostre: Float
)
