package com.ch4019.music.ui.screen.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ch4019.music.ui.component.DrawerState
import com.ch4019.music.ui.components.CenterTopAppBar

@Composable
fun LikeListPage(
    drawerState : DrawerState,
    isPhone : Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CenterTopAppBar(drawerState, isPhone,"喜欢的歌曲")
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(text = "喜欢歌曲列表")
        }
    }
}