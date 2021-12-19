package cat.nbacafe.girona.database.daos

import androidx.room.Dao
import androidx.room.Insert
import cat.nbacafe.girona.database.entities.Beguda

@Dao
interface BegudaDao {
    @Insert
    suspend fun insert(beguda: Beguda)
}