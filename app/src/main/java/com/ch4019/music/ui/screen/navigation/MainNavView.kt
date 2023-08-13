package com.ch4019.music.ui.screen.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ch4019.music.config.MainRoute
import com.ch4019.music.ui.component.DrawerState
import com.ch4019.music.ui.screen.page.AboutPage
import com.ch4019.music.ui.screen.page.HomePage
import com.ch4019.music.ui.screen.page.LikeListPage
import com.ch4019.music.ui.screen.page.SettingPage
import com.ch4019.music.ui.screen.page.SongsListPage

@Composable
fun MainNavView(
    mainNavController: NavHostController,
    drawerState : DrawerState,
    isPhone : Boolean
) {
    NavHost(
        navController = mainNavController,
        startDestination = MainRoute.HOME_PAGE,
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(750),
                initialOffsetX = { it }
            )
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = tween(750),
                targetOffsetX = { -it }
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                animationSpec = tween(750),
                initialOffsetX = { -it }
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                animationSpec = tween(750),
                targetOffsetX = { it }
            )
        }
    ){
        composable(MainRoute.HOME_PAGE){
            HomePage(drawerState, isPhone)
        }
        composable(MainRoute.LIKE_LIST_PAGE){
            LikeListPage(drawerState, isPhone)
        }
        composable(MainRoute.SONGS_LIST_PAGE){
            SongsListPage(drawerState, isPhone)
        }
        composable(MainRoute.SETTING_PAGE){
            SettingPage(mainNavController)
        }
        composable(MainRoute.ABOUT_PAGE){
            AboutPage(mainNavController)
        }
    }
}