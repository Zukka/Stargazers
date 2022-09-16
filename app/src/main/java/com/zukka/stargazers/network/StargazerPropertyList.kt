package com.zukka.stargazers.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StargazerPropertyList(
    val stargazersList: List<StargazerProperty>
): Parcelable
