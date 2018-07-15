package org.escoladeltreball.roomdemo1.dummy

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

/*
* The ViewModel's role is to provide data to the UI and survive configuration changes.
*
* A ViewModel acts as a communication center between the Repository and the UI.
*
* You can also use a ViewModel to share data between fragments.
*
* The ViewModel is part of the lifecycle library.
*
* A ViewModel holds your app's UI data in a lifecycle-conscious way that survives
* configuration changes.
*
* Separating your app's UI data from your Activity and Fragment classes lets you better
* follow the single responsibility principle:
*
* Your activities and fragments are responsible for drawing data to the screen,
* while your ViewModel can take care of holding and processing all the data needed
* for the UI.
*
* In the ViewModel, use LiveData for changeable data that the UI will use or display.
*
* Using LiveData has several benefits:
*
* - You can put an observer on the data (instead of polling for changes) and only
*   update the the UI when the data actually changes.
*
* - The Repository and the UI are completely separated by the ViewModel.
*   There are no database calls from the ViewModel, making the code more testable.
*
* */
class AppViewModel(application: Application) : AndroidViewModel(application) {

    private var mAppRepository: AppRepository? = null
    var mAllItemEntity: LiveData<List<ItemEntity>>? = null

    init {
        mAppRepository = AppRepository(application)
        mAllItemEntity = mAppRepository?.mAllItems
    }

    public fun store(itemEntity: ItemEntity): Unit {
        mAppRepository?.store(itemEntity)
    }

}