package org.escoladeltreball.roomdemo1.dummy

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlin.concurrent.thread

/*
* When you modify the database schema, you'll need to update the version
* number and define how to handle migrations.
* */
@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getItemDao(): ItemDao

    private object sRoomDatabaseCallback : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            thread {
                AppDataBase.INSTANCE?.getItemDao()?.deleteAll()
                AppDataBase.INSTANCE?.getItemDao()?.store(ItemEntity(0, "content1", "detail1"))
                AppDataBase.INSTANCE?.getItemDao()?.store(ItemEntity(0, "content2", "detail2"))
            }
        }
    }

    companion object {

        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase? {

            if (INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room
                            .databaseBuilder(context,
                                    AppDataBase::class.java, "database.db")
                            //.allowMainThreadQueries()
                            //.addCallback(sRoomDatabaseCallback)
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}