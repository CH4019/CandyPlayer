package com.ch4019.music.ui.screen.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.PlaylistPlay
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ch4019.music.R
import com.ch4019.music.ui.component.SheetState
import com.ch4019.music.ui.component.rememberSheetState
import com.ch4019.music.viewModel.PlayViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerBar(
    sheetState : SheetState,
    playViewModel: PlayViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()
    val isPlay by playViewModel.isPlaying.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                scope.launch { sheetState.expand() }
            }
            .padding(start = 16.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .size(50.dp),
            shape = RoundedCornerShape(15.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://avatars.githubusercontent.com/u/52722434?s=48&v=4")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "songsImage",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier
                .size(50.dp),
            onClick = {
                playViewModel.changePlayState()
            }
        ) {
            Icon(
                modifier = Modifier
                    .size(36.dp),
                imageVector = if(isPlay.playState) Icons.Rounded.Pause else Icons.Rounded.PlayArrow,
                contentDescription = null
            )
        }
        IconButton(
            modifier = Modifier
                .size(50.dp),
            onClick = { /*TODO*/ }
        ) {
            Icon(
                modifier = Modifier
                    .size(36.dp),
                imageVector = Icons.AutoMirrored.Rounded.PlaylistPlay,
                contentDescription =null
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PlayerBarPreview() {
    val sheetState = rememberSheetState()
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ){
        PlayerBar(sheetState)
    }

}
