package com.example.eldorado_test.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Permission (
    var id: Int = 0,
    var permission: String = "",
    var status: Boolean = true

) : Parcelable