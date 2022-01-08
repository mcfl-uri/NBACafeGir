package cat.nbacafe.girona.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["usuari_fav", "nom_course_fav"])
data class Favourite(
    @ColumnInfo(name = "usuari_fav")
    var favUsername: String,
    @ColumnInfo(name = "nom_course_fav")
    var favCourseName: String,
)
