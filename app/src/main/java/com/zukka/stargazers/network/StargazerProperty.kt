package com.zukka.stargazers.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StargazerProperty(
    val login: String,
    @Json(name = "avatar_url") val imgSrcUrl: String
): Parcelable