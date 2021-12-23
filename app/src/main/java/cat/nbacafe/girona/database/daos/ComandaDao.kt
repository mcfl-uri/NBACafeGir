package cat.nbacafe.girona.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cat.nbacafe.girona.database.entities.Comanda

@Dao
interface ComandaDao {

    @Insert
    suspend fun insert(comanda: Comanda)

    @Query("SELECT * FROM Comanda WHERE nom_usuari_comanda = :username")
    fun getComandesUser(username: String): List<Comanda>

    @Query("SELECT idComanda FROM Comanda ORDER BY idComanda DESC LIMIT 1")
    fun getLastComanda(): Int

}