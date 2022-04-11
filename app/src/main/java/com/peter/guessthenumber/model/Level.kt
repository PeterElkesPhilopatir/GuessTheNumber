package com.peter.guessthenumber.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Level(
    var id: Int = 0,
    var name: String = "",
    var max: Int = 0,
    var i: Int = 0
) : Parcelable {
    var min: Int = 0
}
