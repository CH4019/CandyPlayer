package com.ch4019.music.ui.screen.index

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ch4019.music.config.MainRoute
import com.ch4019.music.ui.component.DismissibleNavigationDrawer
import com.ch4019.music.ui.component.DrawerState
import com.ch4019.music.ui.component.SheetState
import com.ch4019.music.ui.screen.navigation.MainNavView
import com.ch4019.music.ui.screen.page.SideBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneIndexNavView(
    mainNavController: NavHostController,
    drawerState: DrawerState,
    sheetState : SheetState
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val drawerWidth = (0.55f * screenWidth).toInt().dp

    BackHandler(
        enabled = mainNavController.currentDestination?.route == MainRoute.HOME_PAGE
                || mainNavController.currentDestination?.route == MainRoute.SONGS_LIST_PAGE
                || mainNavController.currentDestination?.route == MainRoute.LIKE_LIST_PAGE
    ){
        //在主页时返回桌面
        context.startActivity(Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_HOME)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    BackHandler(enabled = drawerState.isOpen) {
        //关闭侧边栏
        scope.launch {
            drawerState.close()
        }
    }
    BackHandler(enabled = sheetState.currentValue == SheetValue.Expanded){
        //关闭底栏
        scope.launch {
            sheetState.partialExpand()
        }
    }

    DismissibleNavigationDrawer(
        modifier = Modifier
            .fillMaxSize(),
        drawerState = drawerState,
        drawerWidth = drawerWidth,
        drawerContent = {
            DismissibleDrawerSheet(
                modifier = Modifier
                    .width(drawerWidth),
                windowInsets = WindowInsets(top = 0.dp)
            ) {
                SideBar( mainNavController, drawerState)
            }
        }
    ) {
        MainNavView( mainNavController, drawerState, true)
    }
}
