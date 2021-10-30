package org.wit.placemark.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@parcelize
data class PlacemarkModel(
   //var id: Long = 0,
    var title: String = "",
    var description: String = "") : Parcelable {

}

