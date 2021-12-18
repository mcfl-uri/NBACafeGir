package cat.nbacafe.girona.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cat.nbacafe.girona.database.daos.*
import cat.nbacafe.girona.database.entities.*

@Database(
    entities =
        [Beguda::class,
        Comanda::class,
        Postre::class,
        Sandwich::class,
        Usuari::class],
    version = 3,
    exportSchema = false
)
abstract class NbaCafeDB : RoomDatabase() {

    abstract val begudaDao: BegudaDao
    abstract val comandaDao: ComandaDao
    abstract val postreDao: PostreDao
    abstract val sandwichDao: SandwichDao
    abstract val usuariDao: UsuariDao

    companion object {

        @Volatile
        private var INSTANCE: NbaCafeDB? = null

        fun getInstance(context: Context): NbaCafeDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NbaCafeDB::class.java,
                        "nba_cafe_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}