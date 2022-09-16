package com.zukka.stargazers.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class StargazerPropertyList(
    val stargazersList: List<StargazerProperty>
): Parcelable
