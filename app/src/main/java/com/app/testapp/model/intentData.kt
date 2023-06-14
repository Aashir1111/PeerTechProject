package com.app.testapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class IntentData(
    val username: String,
    val action: String
): Parcelable