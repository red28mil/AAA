
package org.wit.pitchlocate.main

import android.app.Application
import org.wit.pitchlocate.models.PitchlocateMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {
 val pitchlocates = PitchlocateMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Pitchlocate started")


    }
}