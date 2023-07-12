package com.main.tinkoffsummer2023.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.animeproject.ui.screen.settings.SettingsScreen
import com.main.tinkoffsummer2023.core.PreferenceManager
import com.main.tinkoffsummer2023.ui.model.MockBackend
import com.main.tinkoffsummer2023.ui.screen.admin.MyProductsScreen
import com.main.tinkoffsummer2023.ui.screen.auth.signIn.SignInScreen
import com.main.tinkoffsummer2023.ui.screen.auth.signUp.SignUpScreen
import com.main.tinkoffsummer2023.ui.screen.cart.CartScreen
import com.main.tinkoffsummer2023.ui.screen.catalog.CatalogScreen
import com.main.tinkoffsummer2023.ui.screen.catalog.category.CategoryScreen
import com.main.tinkoffsummer2023.ui.screen.catalog.filter.FilterScreen
import com.main.tinkoffsummer2023.ui.screen.orders.OrdersScreen
import com.main.tinkoffsummer2023.ui.screen.orders.order_flow.OrderAddressScreen
import com.main.tinkoffsummer2023.ui.screen.orders.order_flow.OrderIsProcessedScreen
import com.main.tinkoffsummer2023.ui.screen.orders.order_flow.OrderingScreen
import com.main.tinkoffsummer2023.ui.screen.product.ProductScreen
import com.main.tinkoffsummer2023.ui.screen.profile.ProfileScreen
import com.main.tinkoffsummer2023.ui.screen.profile.about_app.AboutAppScreen
import com.main.tinkoffsummer2023.ui.screen.profile.balance.BalanceScreen
import com.main.tinkoffsummer2023.ui.screen.start.StartScreen
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.Start,
    preferenceManager: PreferenceManager,
) {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    when (navBackStackEntry?.destination?.route) {
        Screen.Start.route -> {
            bottomBarState.value = false
        }

        Screen.SignIn.route -> {
            bottomBarState.value = false
        }

        Screen.SignUp.route -> {
            bottomBarState.value = false
        }

        else -> bottomBarState.value = true
    }

//    val isAdmin = MockBackend.usersDataBase[0].isAdmin

    val bottomScreens = if (MockBackend.usersDataBase.isNotEmpty() && MockBackend.usersDataBase[0].isAdmin) {
        listOf(
            BottomScreen.Orders,
            BottomScreen.Category,
            //почему-то съехало(
            BottomScreen.MyProducts,
            BottomScreen.Profile
        )
    } else {
        listOf(
            BottomScreen.Orders,
            BottomScreen.Category,
            BottomScreen.Cart,
            BottomScreen.Profile
        )
    }
//    val bottomScreens =
//        listOf(
//            BottomScreen.Orders,
//            BottomScreen.Category,
//            BottomScreen.Cart,
//            BottomScreen.Profile
//        )

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = ExitTransition.None,
                content = {
                    BottomNavigation(
                        backgroundColor = CustomTheme.colors.white
                    ) {
                        bottomScreens.forEach { screen ->
                            BottomNavigationItem(
                                icon = {
                                    Icon(
                                        painter = painterResource(id = screen.icon),
                                        contentDescription = null,
                                        modifier = Modifier.size(25.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        stringResource(screen.name),
                                        style = CustomTheme.typography.bottom
                                    )
                                },
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                },
                            )
                        }
                    }
                })
        },
    ) { innerPadding ->
        //насрал
        NavHost(
            navController,
            startDestination = startDestination.route,
            Modifier.padding(innerPadding),
        ) {

            composable(BottomScreen.Cart.route) {
                if (MockBackend.usersDataBase.isNotEmpty() && MockBackend.usersDataBase[0].isAdmin)
                    MyProductsScreen(navController)
                else
                    CartScreen(navController)
            }

            composable(BottomScreen.Category.route) { CategoryScreen(navController) }
            composable(
                Screen.Catalog.route,
                arguments = listOf(navArgument("query") { nullable = true })
            ) { CatalogScreen(navController, it.arguments?.getString("query")) }

            composable(Screen.Filter.route) { FilterScreen(navController) }

            //todo norm?
            composable(
                Screen.Product.route,
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) {
                ProductScreen(navController, it.arguments?.getInt("productId"))
            }

            composable(BottomScreen.Profile.route) { ProfileScreen(navController) }
            composable(Screen.Settings.route) { SettingsScreen(navController) }
            composable(Screen.AboutApp.route) { AboutAppScreen(navController) }
            composable(Screen.Balance.route) { BalanceScreen(navController) }

            composable(Screen.Start.route) { StartScreen(navController) }

            composable(Screen.SignIn.route) { SignInScreen(navController) }

            composable(Screen.SignUp.route) { SignUpScreen(navController) }
            composable(BottomScreen.Category.route) { CategoryScreen(navController) }

            composable(BottomScreen.Orders.route) { OrdersScreen(navController) }
            composable(Screen.OrderAddress.route) { OrderAddressScreen(navController) }

            composable(Screen.Ordering.route) { OrderingScreen(navController) }
            composable(Screen.OrderIsProcessed.route) { OrderIsProcessedScreen(navController) }

            composable(BottomScreen.MyProducts.route) { MyProductsScreen(navController) }
        }
    }
}
