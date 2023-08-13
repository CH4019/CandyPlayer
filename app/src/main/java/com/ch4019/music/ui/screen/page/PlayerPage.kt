package com.ch4019.music.ui.screen.page

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material.icons.rounded.AllInclusive
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Commit
import androidx.compose.material.icons.rounded.Dehaze
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ch4019.music.R

@Composable
fun PlayerPage(
    sheetTop : Dp,
    isTablet : Boolean,
    screenState : Boolean,
    startBarHeight : Dp
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .windowInsetsPadding(WindowInsets(top = sheetTop))
            .padding(
                start = if (!isTablet && screenState) startBarHeight + 32.dp else 32.dp,
                end = 32.dp
            )
    ){
        if (!screenState){
            VPlayerView()
            return
        }
        if (isTablet) HPlayerTableView() else HPlayerPhoneView()
    }
}

@Composable
fun HPlayerPhoneView() {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//           TODO:播放器封面
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.75f),
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    shape = RoundedCornerShape(20.dp),
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://example.com/image.jpg")
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
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "歌词"
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            TODO:播放器
        }
    }
}

@Composable
fun VPlayerView() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}

@Composable
fun HPlayerTableView() {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//           TODO:播放器
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            TODO:歌词
        }
    }
}

@Composable
fun PlayerTableView() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "歌曲名")
        Spacer(modifier = Modifier.weight(1f))
        Card(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .aspectRatio(1f),
            shape = RoundedCornerShape(20.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://example.com/image.jpg")
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
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                progress = 0.3f,
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primaryContainer,
                strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(5.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(2.dp)
                    ) {
                        Text(text = "00:50")
                    }
                }
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    tonalElevation = 3.dp,
                    shadowElevation = 3.dp,
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "01:32",
                            fontSize = 16.sp
                        )
                    }
                }
                Surface(
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(2.dp)
                    ) {
                        Text(text = "03:41")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Row(
            modifier = Modifier
                .fillMaxWidth(0.65f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Rounded.SkipPrevious,
                    contentDescription = "previous"
                )
            }
            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = "play"
                )
            }
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Rounded.SkipNext,
                    contentDescription = "next"
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.3f))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.AllInclusive,
                    contentDescription = "previous"
                )
            }
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Alarm,
                    contentDescription = "previous"
                )
            }
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.BarChart,
                    contentDescription = "previous"
                )
            }
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Dehaze,
                    contentDescription = "previous"
                )
            }
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Commit,
                    contentDescription = "previous"
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayerPhoneView() {
    val pagerState = rememberPagerState{2}
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "歌曲名")
        Spacer(modifier = Modifier.weight(1f))
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            state = pagerState,
        ) {
            when (it){
                0 -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f, true),
                        shape = RoundedCornerShape(20.dp),
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://example.com/image.jpg")
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
                }
                1 -> {
                    LyricsView()
                }
            }

        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                progress = 0.3f,
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primaryContainer,
                strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(5.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(2.dp)
                    ) {
                        Text(text = "00:50")
                    }
                }
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    tonalElevation = 3.dp,
                    shadowElevation = 3.dp,
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(4.dp)
                    ) {
                        Text(
                            text = "01:32",
                            fontSize = 16.sp
                        )
                    }
                }
                Surface(
                    shape = RoundedCornerShape(5.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(2.dp)
                    ) {
                        Text(text = "03:41")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Row(
            modifier = Modifier
                .fillMaxWidth(0.65f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Rounded.SkipPrevious,
                    contentDescription = "previous"
                )
            }
            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = "play"
                )
            }
            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Rounded.SkipNext,
                    contentDescription = "next"
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.3f))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.AllInclusive,
                    contentDescription = "previous"
                )
            }
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Alarm,
                    contentDescription = "previous"
                )
            }
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.BarChart,
                    contentDescription = "previous"
                )
            }
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Dehaze,
                    contentDescription = "previous"
                )
            }
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Commit,
                    contentDescription = "previous"
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun LyricsView() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
//                    歌词显示
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = Color.Transparent,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(3.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(2.dp)
                ) {
                    Text(text = "词")
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "歌词来源")
        }
    }
}

@Preview(showBackground = true, device = Devices.TABLET)
@Composable
fun PlayerPagePreview() {
    PlayerPage(
        sheetTop = 0.dp,
        isTablet = false,
        screenState = true,
        startBarHeight = 0.dp
    )
}