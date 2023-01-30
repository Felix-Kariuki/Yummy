package com.flexcode.yummy.presentation.meals_screen

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.flexcode.yummy.domain.models.Meals
import com.flexcode.yummy.presentation.destinations.MealDetailsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun MealItem(
    meals: Meals,
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable {
                navigator.navigate(MealDetailsScreenDestination(meals))
            },
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(16.dp),
        elevation = 10.dp,
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val model = ImageRequest.Builder(LocalContext.current)
                .data("${meals.strMealThumb}")
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build()
            val painter = rememberAsyncImagePainter(model)
            Image(
                modifier = Modifier.fillMaxWidth()
                    .height(150.dp),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "${meals.strMeal}",
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontFamily = MaterialTheme.typography.body1.fontFamily,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
