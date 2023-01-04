package ru.sample.store.binscaner

import android.app.Application
import androidx.room.Room
import ru.sample.store.binscaner.model.room.BinDatabase

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        myApp = this
    }

    companion object {
        private var myApp: MyApp? = null
        private var binDatabase: BinDatabase? = null
        //AndroidRuntime: FATAL EXCEPTION: Thread-2 java.lang.NullPointerException
        fun getMyApp() = myApp!!
        fun getBinDatabase(): BinDatabase {
            if (binDatabase == null) {
                binDatabase = Room.databaseBuilder(
                    getMyApp(),
                    BinDatabase::class.java,
                    ROOM_DB_NAME_BIN
                ).build()
            }
            return binDatabase!!
        }
    }
}