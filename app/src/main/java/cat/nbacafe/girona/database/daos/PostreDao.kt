package cat.nbacafe.girona.database.daos

import androidx.room.Dao
import androidx.room.Insert
import cat.nbacafe.girona.database.entities.Postre

@Dao
interface PostreDao {
    @Insert
    suspend fun insert(postre: Postre)
}