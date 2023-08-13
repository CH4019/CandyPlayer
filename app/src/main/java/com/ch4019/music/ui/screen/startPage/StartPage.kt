package com.ch4019.music.ui.screen.startPage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ch4019.music.config.AppRoute
import com.ch4019.music.ui.theme.CandyPlayerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartPage(
    appNavController : NavHostController
) {
    LaunchedEffect(Unit) {
        //        进入调度器，子协程
        withContext(Dispatchers.IO) {
            delay(1000)
        }
        appNavController.navigate(AppRoute.MAIN_PAGE) {
            // 弹出到图表的起始目的地
            // 避免建立大量目标
            // 在用户选择项目时的后退堆栈上
            popUpTo(AppRoute.START_PAGE) {
                saveState = true
                inclusive = true
            }
            // 重新选择同一项目时避免同一目标的多个副本
            launchSingleTop = true
            // 重新选择先前选定的项目时恢复状态
            restoreState = true
        }
    }
    CandyPlayerTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentWindowInsets = WindowInsets(top = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = Icons.Rounded.ThumbUp,
                    contentDescription = "AppLogo",
                    modifier = Modifier
                        .size(100.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}