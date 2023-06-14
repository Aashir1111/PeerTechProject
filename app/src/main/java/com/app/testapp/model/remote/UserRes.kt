package com.app.testapp.model.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel (
    val name: String?,
    val avatar_url: String?,
    val login: String?,
    val bio: String? = "N/A",
    val company: String? = "N/A",
    val location: String? = "N/A",
    val followers: String?,
    val following: String?,
        ): Parcelable

