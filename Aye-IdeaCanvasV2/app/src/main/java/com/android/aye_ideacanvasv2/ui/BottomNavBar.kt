package com.android.aye_ideacanvasv2.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.aye_ideacanvasv2.model.Screens
import com.android.aye_ideacanvasv2.ui.create.Create
import com.android.aye_ideacanvasv2.ui.home.Home
import com.android.aye_ideacanvasv2.ui.notifications.Notifications
import com.android.aye_ideacanvasv2.ui.profile.Profile
import com.android.aye_ideacanvasv2.ui.search.Search
import com.android.aye_ideacanvasv2.ui.ui.theme.NavBarTheme

data class NavColorScheme(
    val background: Color,
    val selected: Color,
    val unselected: Color
)

@Composable
fun BottomNavigationBar() {
    NavBarTheme {
        val navController = rememberNavController()

        val items = listOf(
            Screens.Home,
            Screens.Search,
            Screens.Create,
            Screens.Notifications,
            Screens.Profile
        )

        val homeNavBarColors = NavColorScheme(
            MaterialTheme.colorScheme.onBackground,
            MaterialTheme.colorScheme.onPrimary,
            MaterialTheme.colorScheme.onSecondary)

        val navBarColors = NavColorScheme(
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary)

        val (navTheme, setNavTheme) = remember { mutableStateOf(homeNavBarColors) }

        Scaffold(
            bottomBar = {
                BottomAppBar(
                    containerColor = navTheme.background
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            alwaysShowLabel = false,
                            label = { Text(stringResource(screen.label), fontSize = 10.sp) },
                            colors = NavigationBarItemColors(
                                selectedIconColor = navTheme.selected,
                                selectedTextColor = navTheme.selected,
                                selectedIndicatorColor = navTheme.background,
                                unselectedIconColor = navTheme.unselected,
                                unselectedTextColor = navTheme.unselected,
                                disabledIconColor = navTheme.unselected,
                                disabledTextColor = navTheme.unselected
                            ),
                            icon = {
                                Icon(
                                    ImageVector.vectorResource(screen.icon),
                                    contentDescription = stringResource(screen.label),
                                    modifier = Modifier.size(28.dp)
                                )
                            },
                            onClick = {
                                if (screen.route == "home") {
                                    setNavTheme(homeNavBarColors)
                                } else {
                                    setNavTheme(navBarColors)
                                }
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screens.Home.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Screens.Home.route) { Home() }
                composable(Screens.Search.route) { Search() }
                composable(Screens.Create.route) { Create() }
                composable(Screens.Notifications.route) { Notifications() }
                composable(Screens.Profile.route) { Profile() }
            }
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar()
}