package com.flexcode.yummy.presentation.meals_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.flexcode.yummy.R
import com.flexcode.yummy.domain.models.Categories

@Composable
fun CategoryItem(
    category: Categories,
    modifier: Modifier = Modifier,
    onClickCategoryItem: (category: String) -> Unit
) {
    Card(
        modifier = modifier
            .padding(top = 4.dp, end = 4.dp, start = 4.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 7.dp,
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier.clickable {

                category.strCategory?.let {

                    onClickCategoryItem(it)
                }
            },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .placeholder(R.drawable.chips)
                    .data("${category.strCategoryThumb}")
                    .crossfade(true)
                    .build(),
                contentDescription = "${category.strCategory}",
                contentScale = ContentScale.Inside,
                modifier = modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .wrapContentSize(),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                Text(
                    text = "${category.strCategory}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}
