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

    @Query ("SELECT EXISTS(SELECT * FROM Usuari WHERE nom_usuari = :usuariNom AND password_usuari = :usuariPass)")
    fun checkLogin(usuariNom: String, usuariPass: String): Boolean
}