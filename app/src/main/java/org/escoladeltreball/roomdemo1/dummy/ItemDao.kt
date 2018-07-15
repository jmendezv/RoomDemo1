package org.escoladeltreball.roomdemo1.dummy

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
public interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun store(itemEntity: ItemEntity): Unit

    @Query("DELETE FROM item")
    fun deleteAll(): Unit

    /*
    * When data changes you usually want to take some action,
    * such as displaying the updated data in the UI.
    *
    * LiveData, a lifecycle library class for data observation,
    * solves this problem.
    *
    * If you, the developer, want to update the stored data,
    * you must use MutableLiveData instead of LiveData.
    *
    * The MutableLiveData class has two public methods that
    * allow you to set the value of a LiveData object,
    *
    * setValue(T) and postValue(T). Usually, MutableLiveData
    * is used in the ViewModel, and then the ViewModel only
    * exposes immutable LiveData objects to the observers.
    *
    * Later you create an Observer of the data in the onCreate()
    * method of MainActivity and override the observer's onChanged() method.
    *
    * When the LiveData changes, the observer is notified and onChanged()
    * is executed.
    *
    * You will then update the cached data in the adapter, and the adapter
    * will update what the user sees.
    *
    * */
    @Query("SELECT * FROM item ORDER BY content ASC")
    fun getAll(): LiveData<List<ItemEntity>>

}