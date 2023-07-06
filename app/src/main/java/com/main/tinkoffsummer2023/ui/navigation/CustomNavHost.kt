package com.main.tinkoffsummer2023.ui.navigation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.main.tinkoffsummer2023.ui.screen.auth.signIn.SignInScreen
import com.main.tinkoffsummer2023.ui.screen.auth.signUp.SignUpScreen
import com.main.tinkoffsummer2023.ui.screen.cart.CartScreen
import com.main.tinkoffsummer2023.ui.screen.catalog.CatalogScreen
import com.main.tinkoffsummer2023.ui.screen.orders.OrdersScreen
import com.main.tinkoffsummer2023.ui.screen.profile.ProfileScreen
import com.main.tinkoffsummer2023.ui.screen.start.StartScreen
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.Start
) {
    val bottomScreens = listOf(
        BottomScreen.Orders,
        BottomScreen.Catalog,
        BottomScreen.Cart,
        BottomScreen.Profile
    )
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = CustomTheme.colors.white
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomScreens.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = null,
                                modifier = Modifier.size(26.dp)
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
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = startDestination.route,
            Modifier.padding(innerPadding),
        ) {
            composable(BottomScreen.Cart.route) { CartScreen() }
            composable(BottomScreen.Catalog.route) { CatalogScreen(navController) }
            composable(BottomScreen.Orders.route) { OrdersScreen() }
            composable(BottomScreen.Profile.route) { ProfileScreen() }


            composable(Screen.Start.route) { StartScreen(navController) }
            composable(Screen.SignIn.route) { SignInScreen(navController) }
            composable(Screen.SignUp.route) { SignUpScreen(navController) }

//            composable(
//                Screen.Anime.route,
//                arguments = listOf(navArgument("animeId") { type = NavType.IntType })
//            ) {
//                AnimeScreen(it.arguments?.getInt("animeId"))
//            }
//            composable(
//                Screen.Character.route,
//                arguments = listOf(navArgument("characterId") { type = NavType.IntType })
//            ) {
//                CharacterScreen(it.arguments?.getInt("characterId"))
//            }

        }
    }
}
