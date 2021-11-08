package org.wit.pitchlocate.models

import timber.log.Timber.i


var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PitchlocateMemStore : PitchlocateStore {

    val pitchlocates = ArrayList<PitchlocateModel>()

    override fun findAll(): List<PitchlocateModel> {
        return pitchlocates
    }


    override fun create(pitchlocate: PitchlocateModel) {
       pitchlocate.id = getId()
       pitchlocates.add(pitchlocate)
        logAll()
    }
    override fun update(pitchlocate: PitchlocateModel) {
        var foundPitchlocate: PitchlocateModel? = pitchlocates.find { p -> p.id == pitchlocate.id }
        if (foundPitchlocate != null) {
            foundPitchlocate.title = pitchlocate.title
            foundPitchlocate.description = pitchlocate.description
            foundPitchlocate.image = pitchlocate.image
            logAll()
        }
    }

    private fun logAll() {
        pitchlocates.forEach{ i("$it") }
    }
}