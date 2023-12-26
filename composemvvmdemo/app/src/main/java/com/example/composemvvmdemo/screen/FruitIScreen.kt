package com.example.composemvvmdemo.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composemvvmdemo.R
import com.example.composemvvmdemo.viewmodel.FruitViewModel

@Composable
fun FruitScreen(onClick: (String) -> Unit) {
    val fruitViewModel: FruitViewModel = hiltViewModel()
    val fruits: State<List<String>> = fruitViewModel.fruits.collectAsState()

    if (fruits.value.isEmpty()) {
        // Showing loader.
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = "Loading...", style = MaterialTheme.typography.h3)
        }
    } else {
        // Showing data.
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(fruits.value.distinct()) {
                FruitItem(fruit = it, onClick)
            }
        }
    }
}

@Composable
fun FruitItem(fruit: String, onClick: (String) -> Unit) {
    Box(modifier = Modifier
        .padding(4.dp)
        .clickable { onClick(fruit) }
        .size(160.dp)
        .clip(RoundedCornerShape(8.dp))
        .paint(
            painter = painterResource(id = R.drawable.bg_fruit), contentScale = ContentScale.Crop
        )
        .border(1.dp, Color(0xFFEEEEEE)), contentAlignment = Alignment.BottomCenter) {
        Text(
            modifier = Modifier.padding(4.dp, 20.dp),
            style = MaterialTheme.typography.body1,
            text = fruit,
            fontSize = 19.sp,
            color = Color.Black
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewScreen() {
    FruitItem(fruit = "Apple", onClick = {})
}