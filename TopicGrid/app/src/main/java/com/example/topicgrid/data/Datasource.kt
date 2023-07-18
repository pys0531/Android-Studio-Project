package com.example.topicgrid.data

import com.example.topicgrid.R
import com.example.topicgrid.model.GridImages

class Datasource {
    fun loadGridImages(): List<GridImages>{
        return listOf<GridImages>(
            GridImages(R.string.architecture, 58, R.drawable.architecture),
            GridImages(R.string.crafts, 121, R.drawable.crafts),
            GridImages(R.string.business, 78, R.drawable.business),
            GridImages(R.string.culinary, 118, R.drawable.culinary),
            GridImages(R.string.design, 423, R.drawable.design),
            GridImages(R.string.fashion, 92, R.drawable.fashion),
            GridImages(R.string.film, 165, R.drawable.film),
            GridImages(R.string.gaming, 164, R.drawable.gaming),
            GridImages(R.string.drawing, 326, R.drawable.drawing),
            GridImages(R.string.lifestyle, 305, R.drawable.lifestyle),
            GridImages(R.string.music, 212, R.drawable.music),
            GridImages(R.string.painting, 172, R.drawable.painting),
            GridImages(R.string.photography, 321, R.drawable.photography),
            GridImages(R.string.tech, 118, R.drawable.tech)
        )
    }
}