package com.example.learnapp.ui.components
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.learnapp.data.entity.LearnResponse


@Composable
fun Loader() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(50.dp)
            .padding(10.dp),
        color = Color.Black
    )
}

@Composable
fun NewsList(response: LearnResponse) {
    LazyColumn {
        items(response.articles) { article ->
            normalTextComponent(textValue = article.title ?: "NA")
        }
    }

}

@Composable
fun normalTextComponent(textValue: String) {
    Text(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp),

    text = textValue,
    style = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    )
    )

}