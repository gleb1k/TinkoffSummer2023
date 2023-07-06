package com.main.tinkoffsummer2023.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.main.tinkoffsummer2023.R

//хз?
enum class Screens {
    Sign_up,
}

//todo nasral, надо вынести в константу!
sealed class Screen(
    val route: String,
) {
    object Start : Screen(
        route = "start"
    )

    object SignUp : Screen(
        route = "sign_up",
    )

    object SignIn : Screen(
        route = "sign_in",

        )

    object Product : Screen(
        route = "product",
    )
}

sealed class BottomScreen(
    val route: String,
    @StringRes
    val name: Int,
    @DrawableRes
    val icon: Int
) {

    object Orders : BottomScreen(
        route = "orders",
        name = R.string.my_orders,
        icon = R.drawable.search
    )

    object Catalog : BottomScreen(
        route = "catalog",
        name = R.string.catalog,
        icon = R.drawable.search
    )

    object Cart : BottomScreen(
        route = "cart",
        name = R.string.cart,
        icon = R.drawable.bag_2
    )

    object Profile : BottomScreen(
        route = "profile",
        name = R.string.profile,
        icon = R.drawable.user_outlined
    )

}

