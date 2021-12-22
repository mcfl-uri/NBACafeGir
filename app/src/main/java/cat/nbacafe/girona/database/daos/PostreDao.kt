package cat.nbacafe.girona.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cat.nbacafe.girona.database.entities.Postre

@Dao
interface PostreDao {
    @Insert
    suspend fun insert(postre: Postre)

    @Query("SELECT * FROM Postre")
    fun getAll(): List<Postre>
}