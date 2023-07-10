package com.main.tinkoffsummer2023.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.main.tinkoffsummer2023.R

//todo хз?
enum class Screens {
    Sign_up,
}

//todo nasral, надо вынести в константу!
sealed class Screen(
    val route: String,
) {

    // region auth
    object Start : Screen(
        route = "start"
    )

    object SignUp : Screen(
        route = "sign_up",
    )

    object SignIn : Screen(
        route = "sign_in",
    )
    // endregion

    // region catalog
    object Product : Screen(
        route = "product",
    )

    object Catalog : Screen(
        route = "catalog",
    )

    object Filter : Screen(
        route = "filter",
    )
    // endregion

    // Profile
    object Balance : Screen(
        route = "balance"
    )

    object AboutApp : Screen(
        route = "about_app"
    )

    object Settings : Screen(
        route = "settings"
    )
    // endregion

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
        icon = R.drawable.box
    )

    object Category : BottomScreen(
        route = "category",
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

