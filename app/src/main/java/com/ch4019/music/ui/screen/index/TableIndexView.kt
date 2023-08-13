package com.ch4019.music.ui.screen.index

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.SheetValue.Expanded
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ch4019.music.config.MainRoute
import com.ch4019.music.ui.component.DrawerValue
import com.ch4019.music.ui.component.SheetState
import com.ch4019.music.ui.component.rememberDrawerState
import com.ch4019.music.ui.screen.navigation.MainNavView
import com.ch4019.music.ui.screen.page.SideBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TableIndexNavView(
    screenState: Boolean,
    startPaddingValues: Dp,
    mainNavController : NavHostController,
    sheetState : SheetState
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) //消除参数缺失

    BackHandler(enabled = true){
        when(sheetState.currentValue){
            Expanded -> scope.launch { sheetState.partialExpand() }
            else -> when(mainNavController.currentDestination?.route){
                MainRoute.HOME_PAGE -> context.startActivity(Intent(Intent.ACTION_MAIN).apply {
                    addCategory(Intent.CATEGORY_HOME)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
                MainRoute.SONGS_LIST_PAGE -> context.startActivity(Intent(Intent.ACTION_MAIN).apply {
                    addCategory(Intent.CATEGORY_HOME)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
                MainRoute.LIKE_LIST_PAGE -> context.startActivity(Intent(Intent.ACTION_MAIN).apply {
                    addCategory(Intent.CATEGORY_HOME)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
                else -> {mainNavController.navigateUp()}
            }
        }
    }

    PermanentNavigationDrawer(
        modifier = if (screenState) Modifier.padding(start = startPaddingValues) else Modifier,
        drawerContent = {
            PermanentDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(if (screenState)0.35f else 0.45f),
                windowInsets = WindowInsets(top = 0.dp)
            ) {
                SideBar( mainNavController, drawerState)
            }
    }) {
        MainNavView( mainNavController, drawerState, false)
    }
}
