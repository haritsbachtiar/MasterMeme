@file:OptIn(ExperimentalMaterial3Api::class)

package com.haritsbachtiar.mastermeme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haritsbachtiar.mastermeme.component.EmptyMemeView
import com.haritsbachtiar.mastermeme.component.MemeListView
import com.haritsbachtiar.mastermeme.component.TemplateMemeBottomSheet
import com.haritsbachtiar.mastermeme.data.MemeTemplates
import com.haritsbachtiar.mastermeme.ui.theme.MasterMemeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MasterMemeTheme {
                HomeScreen()
            }
        }
    }
}

const val ON_PRIMARY_FIXED_COLOR = 0xFF21005D
const val SURFACE_CONTAINER_LOW_COLOR = 0xFF1D1B20
const val SURFACE_CONTAINER_LOWEST_COLOR = 0xFF0F0D13
const val OUTLINE_COLOR = 0xFF79747E
const val LIGHT_GRAY_C0LOR = 0xFFE6E0E9
const val SCRIM_COLOR = 0xB20F0D13

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        containerColor = Color(SURFACE_CONTAINER_LOWEST_COLOR),
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color(SURFACE_CONTAINER_LOW_COLOR),
                    scrolledContainerColor = Color(SURFACE_CONTAINER_LOW_COLOR),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                title = {
                    Text("Your memes")
                }
            )
        }
    ) { innerPadding ->

        var showBottomSheet by remember { mutableStateOf(false) }

        val listImages = MemeTemplates.data

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.BottomEnd
        ) {
            if (listImages.isEmpty()) {
                EmptyMemeView()
            } else {
                MemeListView(data = listImages)
            }

            FloatingActionButton(
                modifier = Modifier
                    .padding(bottom = 32.dp, end = 16.dp),
                containerColor = Color(0xFFEADDFF),
//                Brush.horizontalGradient(
//                    colorStops = arrayOf(
//                        0.0F to Color(0xFFEADDFF),
//                        1.0F to Color(0xFFD0BCFE)
//                    )
//                ),
                contentColor = Color(ON_PRIMARY_FIXED_COLOR),
                onClick = {
                    showBottomSheet = true
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add Meme"
                )
            }

            if (showBottomSheet) {
                TemplateMemeBottomSheet(
                    data = MemeTemplates.data,
                    onDismiss = { showBottomSheet = false }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MasterMemeTheme {
        HomeScreen()
    }
}