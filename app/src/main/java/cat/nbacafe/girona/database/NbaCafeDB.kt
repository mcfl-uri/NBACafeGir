package cat.nbacafe.girona.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cat.nbacafe.girona.database.daos.*
import cat.nbacafe.girona.database.entities.*

@Database(entities = [Beguda::class, Comanda::class, Postre::class, Sandwich::class, Usuari::class], version = 1, exportSchema = false)
abstract class NbaCafeDB : RoomDatabase() {

    abstract val begudaDao: BegudaDao
    abstract val comandaDao: ComandaDao
    abstract val postreDao: PostreDao
    abstract val sandwichDao: SandwichDao
    abstract val usuariDao: UsuariDao


}