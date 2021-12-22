package cat.nbacafe.girona.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cat.nbacafe.girona.database.entities.Beguda

@Dao
interface BegudaDao {
    @Insert
    suspend fun insert(beguda: Beguda)

    @Query("SELECT * FROM BEGUDA")
    fun getAll(): List<Beguda>
}