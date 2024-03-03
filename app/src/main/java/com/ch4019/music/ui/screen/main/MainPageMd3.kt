package com.ch4019.music.ui.screen.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ch4019.music.ui.component.BottomSheetDefaultsDp
import com.ch4019.music.ui.component.BottomSheetScaffold
import com.ch4019.music.ui.component.DrawerValue
import com.ch4019.music.ui.component.rememberBottomSheetScaffoldState
import com.ch4019.music.ui.component.rememberDrawerState
import com.ch4019.music.ui.component.rememberStandardBottomSheetState
import com.ch4019.music.ui.screen.index.PhoneIndexNavView
import com.ch4019.music.ui.screen.index.TableIndexNavView
import com.ch4019.music.ui.screen.page.PlayerBar
import com.ch4019.music.ui.screen.page.PlayerPage
import com.ch4019.music.ui.theme.CandyPlayerTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageMd3() {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val screenLayout = configuration.screenLayout
    val orientation = context.resources.configuration.orientation
    val heightPx = context.resources.displayMetrics.heightPixels
    val density = LocalDensity.current

    val sheetState = rememberStandardBottomSheetState()
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val mainNavController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var screenState by remember { mutableStateOf(false) }
    var sheetOffset : Float?  by remember{ mutableStateOf(null) }
    var sheetStateOffset : Float? by remember{ mutableStateOf(null) }
    var sheetTop by remember{ mutableStateOf(0.dp)}
    var topBarHeight by remember{mutableStateOf(0.dp)}
    topBarHeight= with(density) {WindowInsets.statusBars.getTop(density).toDp()}
    screenState = orientation != Configuration.ORIENTATION_PORTRAIT
    val sheetPeekHeight = with(density) { BottomSheetDefaultsDp.SheetPeekHeight.toPx()}
    val isTablet = screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    sheetStateOffset = sheetState.offset
    sheetOffset = heightPx - sheetPeekHeight - (sheetStateOffset ?: 0f)
    val factor = if((sheetOffset ?: 0f) <= sheetPeekHeight){ ((sheetOffset ?: 0f) - 5f) / (sheetPeekHeight + 5f) } else { 1f }
    sheetTop = ((1-factor)*sheetPeekHeight).dp + topBarHeight



    CandyPlayerTheme {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetShape = BottomSheetDefaults.HiddenShape,
            sheetDragHandle = {},
            sheetContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (sheetState.currentValue == SheetValue.PartiallyExpanded) {
                        Column(
                            modifier = Modifier
                                .height(64.dp)
                                .fillMaxWidth()
                                .alpha(1 - factor)
                        ) {
                            Column(
                                modifier = if (!isTablet && screenState) {
                                    Modifier.padding(
                                        start = topBarHeight)
                                } else  Modifier
                            ) {
                                PlayerBar(sheetState)
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(factor)
                    ) {
                        PlayerPage(sheetTop, isTablet, screenState, topBarHeight)
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .windowInsetsPadding(DrawerDefaults.windowInsets)
            ) {
                if (isTablet){
                    TableIndexNavView(screenState,0.dp,mainNavController,sheetState)
                }else{
                    if (screenState){
                        TableIndexNavView(true,topBarHeight,mainNavController,sheetState)
                    }else {
                        PhoneIndexNavView(mainNavController,drawerState,sheetState)
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.DEFAULT)
@Composable
fun MainShow(){
    CandyPlayerTheme {
        MainPageMd3()
    }
}