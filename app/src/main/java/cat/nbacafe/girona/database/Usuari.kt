package cat.nbacafe.girona.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuari(
    @PrimaryKey(autoGenerate = false)
    var nomUsuari: String,
    var emailUsuari: String,
    var passwordUsuari: String
)
