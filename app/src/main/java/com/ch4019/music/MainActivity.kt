package com.ch4019.music

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ch4019.music.config.AppRoute
import com.ch4019.music.ui.screen.main.MainPageMd3
import com.ch4019.music.ui.screen.startPage.StartPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置全屏
        WindowCompat.setDecorFitsSystemWindows(window, false)


        val lp = window.attributes
        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        window.attributes = lp

        setContent {
            val appNavController = rememberNavController()
            NavHost(
                navController = appNavController,
                startDestination = AppRoute.START_PAGE
            ){
                composable(AppRoute.START_PAGE){
                    StartPage(appNavController)
                }
                composable(AppRoute.MAIN_PAGE){
                    MainPageMd3()
                }
            }
        }
    }
}