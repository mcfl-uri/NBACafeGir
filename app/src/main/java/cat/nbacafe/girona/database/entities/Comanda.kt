package cat.nbacafe.girona.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comanda(
    @PrimaryKey(autoGenerate = true)
    val idComanda: Int,
    @ColumnInfo(name = "nom_usuari_comanda")
    var nomUsuariCo: String,
    @ColumnInfo(name = "nom_sandwich_comanda")
    var sandwichNom: String,
    @ColumnInfo(name = "nom_postre_comanda")
    var postreNom: String,
    @ColumnInfo(name = "nom_beguda_comanda")
    var begudaNom: String,
    @ColumnInfo(name = "preu_comanda")
    var totalComanda: Float
)
