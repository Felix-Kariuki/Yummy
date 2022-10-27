package com.flexcode.yummy.presentation.meal_details

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.flexcode.yummy.R
import com.flexcode.yummy.domain.models.Meals
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun DetailsItem(
    meals: Meals,
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
    /*    Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            IconButton(
                onClick = {
                    navigator.popBackStack()
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_left),
                    contentDescription = "back",
                    modifier = Modifier.size(32.dp)
                )
            }
            Text(
                text = "${meals.strMeal}",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(48.dp))
        }*/

        
        Box(
                modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(5f / 4f), contentAlignment = BottomCenter
        ){

            Surface(
                    color = Color(0x7F000000),
                    elevation = 8.dp,
                    modifier = Modifier
                            .align(
                                    TopStart
                            )
                            .fillMaxWidth()
                            .padding(
                                    4.dp
                            )
            ) {
                Row(
                        Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                ) {

                    IconButton(
                            onClick = {
                                navigator.popBackStack()
                            },
                    ) {
                        Icon(
                                painter = painterResource(id = R.drawable.ic_left),
                                contentDescription = "back",
                                modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                            text = "${meals.strMeal}",
                            fontSize = 18.sp,
                            modifier = Modifier
                                    .padding(8.dp)
                                    .weight(1f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(48.dp))
                }
                val model = ImageRequest.Builder(LocalContext.current)
                        .data("${meals.strMealThumb}")
                        .size(Size.ORIGINAL)
                        .crossfade(true)
                        .build()
                val painter = rememberAsyncImagePainter(model)
                Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                )


            }

        }
       
        Text(
            text = "Ingredients:",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.SemiBold
        )

        Column(modifier = modifier.height(220.dp)) {
            Text(
                text = TestList(meals).toString(),
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            text = "Procedure:",
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "${meals.strInstructions}:",
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
        )
    }
}

@Composable
fun TestList(
    meals: Meals
) {
    val list = listOf(
        "${meals.strIngredient1} ${meals.strMeasure1}",
        "${meals.strIngredient2} ${meals.strMeasure2}",
        "${meals.strIngredient3} ${meals.strMeasure3}",
        "${meals.strIngredient4} ${meals.strMeasure4}",
        "${meals.strIngredient5} ${meals.strMeasure5}",
        "${meals.strIngredient6} ${meals.strMeasure6}",
        "${meals.strIngredient7} ${meals.strMeasure7}",
        "${meals.strIngredient8} ${meals.strMeasure8}",
        "${meals.strIngredient9} ${meals.strMeasure9}",
        "${meals.strIngredient10} ${meals.strMeasure10}",
        "${meals.strIngredient11} ${meals.strMeasure11}",
        "${meals.strIngredient12} ${meals.strMeasure12}",
        "${meals.strIngredient13} ${meals.strMeasure13}",
        "${meals.strIngredient14} ${meals.strMeasure14}",
        "${meals.strIngredient15} ${meals.strMeasure15}",
        "${meals.strIngredient16} ${meals.strMeasure16}",
        "${meals.strIngredient17} ${meals.strMeasure17}",
        "${meals.strIngredient18} ${meals.strMeasure18}",
    )
    LazyColumn {
        items(list) {
            Row(Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
                Canvas(
                    modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp)
                            .size(6.dp)
                ) {
                    drawCircle(Color.Black)
                }
                Text(text = it, fontSize = 12.sp)
            }
        }
    }
}
