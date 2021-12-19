package cat.nbacafe.girona.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import cat.nbacafe.girona.database.daos.*
import cat.nbacafe.girona.database.entities.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
                    ).addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Thread(Runnable { prepopulateDb(getInstance(context)) }).start()
                        }
                    })
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        private fun prepopulateDb(db: NbaCafeDB) {
            GlobalScope.launch {
                //Sandwichs
                db.sandwichDao.insert(Sandwich(0, "New York", "Originari de Harlem i el Bronx. Ingredients: carn de vedella i ceba mòlta/picada, formatge americà fos, enciam, tomàquets i condiments en un hero roll.", 6.5))
                db.sandwichDao.insert(Sandwich(0, "San Francisco", "Consisteix en carn rostida a rodanxes fines, pebre negre, mostassa i rave picant sobre pà de baguette que s'ha submergit en sucs de paella o salsa.", 6.0))
                db.sandwichDao.insert(Sandwich(0, "Phoenix", "Un hotdog embolicat en bacon, amb mongetes pintas, ceba, tomàquet, salsa de bitxo jalapeño i maiones", 5.5))
                db.sandwichDao.insert(Sandwich(0, "Miami vice", "Es tracta d'un bocata amb porc rostit, pernil, formatge suís, cogombrets adobats, mostassa.", 8.5))
                db.sandwichDao.insert(Sandwich(0, "Chicago", "Un frankfurter de vedella amb pa de llavors de rosella, cobert amb tomàquet, mostassa groga, condiment dolç, ceba picada, pebrots i una mica de sal d'api.", 4.5))
                db.sandwichDao.insert(Sandwich(0, "Portland", "Una baguette farcida de porc, maionesa, coriandre, suc de llima, cogombre i ceba.", 7.5))
                db.sandwichDao.insert(Sandwich(0, "Minnesota", "La carn mòlta saltejada i la carn d'embotit es barregen en un panet, cobertes amb salsa marinara i mozzarella, rostides a la perfecció.", 8.5))
                db.sandwichDao.insert(Sandwich(0, "Oklahoma", "Fet d'una hamburguesa amb una gran quantitat de ceba tallada a rodanxes aixafada a la carn. A mesura que es cou, les cebes s'infonen a la carn i els sucs de la carn cobreixen les cebes.", 4.5))
                db.sandwichDao.insert(Sandwich(0, "Utah", "Dos tipus de vedella: una hamburguesa a la brasa i pastrami a rodanxes fines, natch, servides sobre un pa de sèsam degotant amb fry sauce i cobert amb tomàquets, enciam ratllat i ceba.", 10.5))
                db.sandwichDao.insert(Sandwich(0, "Houston", "Un costell, carregat amb carn de vedella a la brasa picada o a rodanxes, servit en un wrap", 8.5))

                //Postres
                db.postreDao.insert(Postre(0, "Denver", "Pastís de prèssec", 5.5))
                db.postreDao.insert(Postre(0, "Indiana", "Pastís de crema de sucre", 4.5))
                db.postreDao.insert(Postre(0, "Boston", "Pastís de crema", 4.5))
                db.postreDao.insert(Postre(0, "New Orleans", "Donuts glacejats de sucre", 3.5))
                db.postreDao.insert(Postre(0, "Charlotte", "Krispy Kreme donuts", 4.0))
                db.postreDao.insert(Postre(0, "Cleveland", "Buckeye candy", 4.5))
                db.postreDao.insert(Postre(0, "Philly", "Funnel cake", 4.0))
                db.postreDao.insert(Postre(0, "Memphis", "Moon pies", 3.5))
                db.postreDao.insert(Postre(0, "Washington", "Nanaimo bars", 3.5))
                db.postreDao.insert(Postre(0, "Milwaukee", "Danish Kringle", 4.0))

                //Begudes
                db.begudaDao.insert(Beguda(0, "Canya", 2.3))
                db.begudaDao.insert(Beguda(0, "Clara", 2.5))
                db.begudaDao.insert(Beguda(0, "Alhambra", 2.8))
                db.begudaDao.insert(Beguda(0, "Voll-Damm", 2.8))
                db.begudaDao.insert(Beguda(0, "Coca-Cola", 2.1))
                db.begudaDao.insert(Beguda(0, "Trina", 2.1))
                db.begudaDao.insert(Beguda(0, "Fanta", 2.1))
                db.begudaDao.insert(Beguda(0, "Aquarius", 2.1))
                db.begudaDao.insert(Beguda(0, "Aigua", 1.8))
                db.begudaDao.insert(Beguda(0, "Aigua amb gas", 1.9))
            }
        }
    }
}