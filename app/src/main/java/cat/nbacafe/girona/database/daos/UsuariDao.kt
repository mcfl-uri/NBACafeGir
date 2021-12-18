package cat.nbacafe.girona.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cat.nbacafe.girona.database.entities.Usuari

@Dao
interface UsuariDao {

    @Insert
    suspend fun insert(usuari: Usuari)

    @Query ("SELECT EXISTS(SELECT * FROM Usuari WHERE nom_usuari = :usuariNom)")
    fun userExists(usuariNom: String): Boolean

}