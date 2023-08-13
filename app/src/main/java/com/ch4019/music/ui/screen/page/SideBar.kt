package com.ch4019.music.ui.screen.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ch4019.music.config.MainRoute
import com.ch4019.music.model.SideItem
import com.ch4019.music.ui.component.DrawerState
import kotlinx.coroutines.launch

@Composable
fun SideBar(
    mainNavController : NavHostController,
    drawerState : DrawerState,
) {
    val scope = rememberCoroutineScope()
    val sideItems = listOf(
        SideItem(Icons.Rounded.CalendarToday, "每日推荐", MainRoute.HOME_PAGE),
        SideItem(Icons.Rounded.AutoAwesome, "喜欢的音乐", MainRoute.LIKE_LIST_PAGE),
        SideItem(Icons.Rounded.Upcoming, "我的歌单", MainRoute.SONGS_LIST_PAGE)
    )
    val sideSettingItems = listOf(
        SideItem(Icons.Rounded.Settings, "设置", MainRoute.SETTING_PAGE),
        SideItem(Icons.Rounded.Info, "关于", MainRoute.ABOUT_PAGE),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    scope.launch { drawerState.close() }
                }) {
                    Icon(imageVector = Icons.Rounded.WbSunny, contentDescription = "theme")
                }
                IconButton(onClick = {
                    scope.launch { drawerState.close() }
                }) {
                    Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "user")
                }
                IconButton(onClick = { scope.launch { drawerState.close() } }) {
                    Icon(imageVector = Icons.Rounded.ThumbUp, contentDescription = "thumb up")
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            sideItems.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch { drawerState.close() }
                            if (mainNavController.currentDestination?.route != it.route) {
                                mainNavController.navigate(it.route){
                                    popUpTo(it.route){
                                        inclusive = true
                                        saveState = true
                                    }
                                    // 重新选择同一项目时避免同一目标的多个副本
                                    launchSingleTop = true
                                }
                            }
                        }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(it.title)
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            sideSettingItems.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch { drawerState.close() }
                            if (mainNavController.currentDestination?.route != it.route) {
                                mainNavController.navigate(it.route) {
                                    popUpTo(it.route) {
                                        inclusive = true
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                }
                            }
                        }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(it.title)
                }
            }
        }
    }
}

//@Composable
//fun TableSideBar(
//    mainNavController : NavHostController,
//    indexNavController : NavHostController,
//) {
//    val scope = rememberCoroutineScope()
//    val sideItems = listOf(
//        SideItem(Icons.Rounded.ExitToApp, "退出", "/"),
//        SideItem(Icons.Rounded.Face, "我的音乐", "/music"),
//        SideItem(Icons.Rounded.ThumbUp, "我的收藏", "/collection")
//    )
//    val sideSettingItems = listOf(
//        SideItem(Icons.Rounded.ExitToApp, "退出", "/"),
//        SideItem(Icons.Rounded.Face, "我的音乐", "/music"),
//        SideItem(Icons.Rounded.ThumbUp, "我的收藏", "/collection"),
//        SideItem(Icons.Rounded.ExitToApp, "退出", "/"),
//        SideItem(Icons.Rounded.ExitToApp, "退出", "/"),
//        SideItem(Icons.Rounded.ExitToApp, "退出", "/"),
//        SideItem(Icons.Rounded.ExitToApp, "退出", "/"),
//    )
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 8.dp),
//            shape = RoundedCornerShape(10.dp)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                IconButton(onClick = { scope.launch { }}) {
//                    Icon(imageVector = Icons.Rounded.ExitToApp, contentDescription = "exit to app")
//                }
//                IconButton(onClick = { scope.launch {  } }) {
//                    Icon(imageVector = Icons.Rounded.Face, contentDescription = "face")
//                }
//                IconButton(onClick = { scope.launch { } }) {
//                    Icon(imageVector = Icons.Rounded.ThumbUp, contentDescription = "thumb up")
//                }
//            }
//        }
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 8.dp),
//        ) {
//            sideItems.forEach {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable {
//
//                        }
//                        .padding(16.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = it.icon,
//                        contentDescription = null
//                    )
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Text(it.title)
//                }
//            }
//        }
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 8.dp),
//        ) {
//            sideSettingItems.forEach {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable {
//
//                        }
//                        .padding(16.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = it.icon,
//                        contentDescription = null
//                    )
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Text(it.title)
//                }
//            }
//        }
//    }
//}
