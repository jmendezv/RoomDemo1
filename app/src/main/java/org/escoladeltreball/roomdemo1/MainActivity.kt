package org.escoladeltreball.roomdemo1

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.escoladeltreball.roomdemo1.dummy.AppViewModel
import org.escoladeltreball.roomdemo1.dummy.DummyContent
import org.escoladeltreball.roomdemo1.dummy.ItemEntity

class MainActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener {

    private var mAppViewModel: AppViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        /*
        * Use ViewModelProviders to associate your ViewModel with your UI controller
        *
        * When your app first starts, the ViewModelProviders will create the ViewModel.
        *
        * When the activity is destroyed, for example through a configuration change,
        * the ViewModel persists.
        *
        * When the activity is re-created, the ViewModelProviders return the existing ViewModel.
        *
        * */
        mAppViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        /*
        * Add an observer for the LiveData returned by mAllItemEntity
        *
        * */
        mAppViewModel?.mAllItemEntity?.observe(this, Observer<List<ItemEntity>> {
            // update adapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
    }

}
