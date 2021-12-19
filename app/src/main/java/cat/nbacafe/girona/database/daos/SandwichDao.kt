package cat.nbacafe.girona.database.daos

import androidx.room.Dao
import androidx.room.Insert
import cat.nbacafe.girona.database.entities.Sandwich

@Dao
interface SandwichDao {
    @Insert
    suspend fun insert(sandwich: Sandwich)
}