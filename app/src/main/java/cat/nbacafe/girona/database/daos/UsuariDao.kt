package cat.nbacafe.girona.database.daos

import androidx.room.Dao
import androidx.room.Insert
import cat.nbacafe.girona.database.entities.Usuari

@Dao
interface UsuariDao {

    @Insert
    suspend fun insert(usuari: Usuari)

}