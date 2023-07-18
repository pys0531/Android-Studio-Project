package com.example.topicgrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class GridImages(
    @StringRes val stringResourceId: Int,
    val CourseNum: Int,
    @DrawableRes val imageResourceId: Int
)
