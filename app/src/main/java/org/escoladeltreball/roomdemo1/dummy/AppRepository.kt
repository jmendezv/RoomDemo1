package org.escoladeltreball.roomdemo1.dummy

import android.app.Application
import android.arch.lifecycle.LiveData
import kotlin.concurrent.thread

class AppRepository(val application: Application) {

    var mItemDao: ItemDao? = null
    var mAllItems: LiveData<List<ItemEntity>>? = null

    init {
        val appDataBase = AppDataBase.getInstance(application)
        mItemDao = appDataBase?.getItemDao()
        mAllItems = mItemDao?.getAll()
    }

    fun store(itemEntity: ItemEntity): Unit {
        thread {
            mItemDao?.store(itemEntity)
        }
    }

}