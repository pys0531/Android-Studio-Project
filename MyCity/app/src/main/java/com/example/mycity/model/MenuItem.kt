package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class MenuItem(
    @DrawableRes open val image: Int,
    @StringRes open val name: Int,
    @StringRes open val descript: Int
) {
    data class City(
        override val image: Int,
        override val name: Int,
        override val descript: Int
    ): MenuItem(image, name, descript)

    data class Recommend(
        override val image: Int,
        override val name: Int,
        override val descript: Int
    ): MenuItem(image, name, descript)
}