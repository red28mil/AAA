package org.wit.pitchlocate.models

interface PitchlocateStore {
    fun findAll(): List<PitchlocateModel>
    fun create(pitchlocate: PitchlocateModel)
    fun update(pitchlocate: PitchlocateModel)
}
