package com.ch4019.music.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import com.ch4019.music.ui.component.DrawerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopAppBar(
    drawerState : DrawerState,
    isPhone : Boolean,
    label : String
) {
    val scope = rememberCoroutineScope()
    CenterAlignedTopAppBar(
        windowInsets = WindowInsets(top = 0.dp),
        navigationIcon = {
            if (isPhone) {
                IconButton(onClick = {
                    if (drawerState.isOpen) {
                        scope.launch { drawerState.close() }
                    } else {
                        scope.launch { drawerState.open() }
                    }
                }) {
                    Icon(imageVector = Icons.Rounded.Menu, contentDescription = "menu")
                }
            }
        },
        title = { Text(text = label) }
    )
}