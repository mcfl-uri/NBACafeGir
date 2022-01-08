package cat.nbacafe.girona.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cat.nbacafe.girona.database.entities.Favourite

@Dao
interface FavouriteDao {
    @Insert
    suspend fun insert(favourite: Favourite)

    @Query("SELECT * FROM Favourite WHERE usuari_fav = :user")
    fun getAll(user: String): List<Favourite>

    @Delete
    suspend fun delete(favourite: Favourite)

    @Query ("SELECT EXISTS(SELECT * FROM Favourite WHERE usuari_fav = :usuariNom AND nom_course_fav = :platNom)")
    fun favExists(usuariNom: String, platNom: String): Boolean
}