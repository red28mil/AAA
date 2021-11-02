
package org.wit.placemark.main

import android.app.Application
import org.wit.placemark.models.PlacemarkMemStore
import org.wit.placemark.models.PlacemarkStore
import org.wit.placemark.models.PlacemarkModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {
    //lateinit var placemarks =  PlacemarkStore()
 val placemarks = PlacemarkMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
       // placemarks = PlacemarkMemStore()
        i("Placemark started")


    }
}