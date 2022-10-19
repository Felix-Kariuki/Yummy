package com.flexcode.yummy.presentation.welcome_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexcode.yummy.R
import com.flexcode.yummy.presentation.destinations.MealsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun WelComeScreen(
    navigator: DestinationsNavigator
) {

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {

        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .height(378.dp)
                .fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.background
        ) {
            Image(
                painter = painterResource(R.drawable.chef),
                contentDescription = "Welcome Screen Image",
                modifier = Modifier
                    .height(520.dp)
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp)
                .offset(y = -(16.dp)),
            text = " It's \n Cooking \n Time!",
            fontSize = 46.sp,
            color = MaterialTheme.colors.onBackground,
            fontFamily = MaterialTheme.typography.h1.fontFamily,
            fontStyle = MaterialTheme.typography.h1.fontStyle,
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navigator.navigate(MealsScreenDestination)
            },
            // Uses ButtonDefaults.ContentPadding by default
            contentPadding = PaddingValues(
                start = 40.dp,
                top = 16.dp,
                end = 40.dp,
                bottom = 16.dp
            ),
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface
            ),
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
        ) {
            Text(
                "Get Started",
                fontSize = 20.sp,
            )
        }
    }
}
