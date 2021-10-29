
package org.wit.placemark.main

import android.app.Application
import org.wit.placemark.models.PlacemarkMemStore
//import org.wit.placemark.models.PlacemarkModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {
    //val placemarks = ArrayList<PlacemarkModel>()
    val placemarks =  PlacemarkMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Placemark started")


    }
}