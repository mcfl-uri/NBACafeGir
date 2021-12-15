package cat.nbacafe.girona.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Usuari(
    @PrimaryKey
    var nomUsuari: String,
    @ColumnInfo(name = "email_usuari")
    var emailUsuari: String,
    @ColumnInfo(name = "password_usuari")
    var passwordUsuari: String
)
