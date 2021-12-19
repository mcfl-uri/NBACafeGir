package cat.nbacafe.girona.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cat.nbacafe.girona.database.entities.Sandwich

@Dao
interface SandwichDao {
    @Insert
    suspend fun insert(sandwich: Sandwich)

    @Query ("SELECT * FROM Sandwich")
    fun getAll(): List<Sandwich>
}