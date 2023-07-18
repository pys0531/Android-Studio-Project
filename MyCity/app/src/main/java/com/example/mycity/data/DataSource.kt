package com.example.mycity.data

import com.example.mycity.model.MenuItem
import com.example.mycity.R

object DataSource {
    val city = listOf(
        MenuItem.City(
            image = R.drawable.namguro,
            name = R.string.namguro,
            descript = R.string.namguro
        ),
        MenuItem.City(
            image = R.drawable.hongdae,
            name = R.string.hongdae,
            descript = R.string.hongdae
        ),
        MenuItem.City(
            image = R.drawable.sillim,
            name = R.string.sillim,
            descript = R.string.sillim
        ),
    )

    val namguro_recommend = listOf(
        MenuItem.Recommend(
            image = R.drawable.guro_marketplace,
            name = R.string.guro_marketplace,
            descript = R.string.guro_marketplace
        ),
        MenuItem.Recommend(
            image = R.drawable.guro_seven_princess,
            name = R.string.guro_seven_princess,
            descript = R.string.guro_seven_princess
        )
    )

    val hongdae_recommend = listOf(
        MenuItem.Recommend(
            image = R.drawable.hongdae_street,
            name = R.string.hongdae_street,
            descript = R.string.hongdae_street
        ),
    )

    val sillim_recommend = listOf(
        MenuItem.Recommend(
            image = R.drawable.sillim_street,
            name = R.string.sillim_street,
            descript = R.string.sillim_street
        ),
    )

    val defaultCity = city[0]
    val defaultRecommend = namguro_recommend[0]
}
