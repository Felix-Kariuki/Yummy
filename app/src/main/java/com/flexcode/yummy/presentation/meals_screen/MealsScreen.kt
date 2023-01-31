package com.flexcode.yummy.presentation.meals_screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.More
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.flexcode.inapppurchasescompose.InAppPurchasesHelper
import com.flexcode.yummy.R
import com.flexcode.yummy.domain.models.Categories
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MealsScreen(
    navigator: DestinationsNavigator,
    viewModel: MealsViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val categoriesState = viewModel.categoryState

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val focusManager = LocalFocusManager.current

    val billingPurchaseHelper = InAppPurchasesHelper(LocalContext.current as Activity, "more_recipe_sub")
    billingPurchaseHelper.setUpBillingPurchases()

    Scaffold(
        floatingActionButton = {
            FloatingMoreRecipesButton(billingPurchaseHelper)
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Text(
                text = "What would you like to cook?",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                fontSize = 21.sp,
                fontFamily = FontFamily.SansSerif

            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = state.searchMeal,
                    onValueChange = {
                        viewModel.onEvent(
                            MealsEvent.OnSearchMeal(it)
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Search",
                            color = MaterialTheme.colors.onBackground.copy(alpha = .95f),
                            style = MaterialTheme.typography.body1,
                        )
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colors.background,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .shadow(8.dp),
                    shape = RoundedCornerShape(size = 8.dp),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(

                        onSearch = {
                            focusManager.clearFocus()
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.White,
                        disabledTextColor = MaterialTheme.colors.background,
                        backgroundColor = MaterialTheme.colors.background,
                        focusedIndicatorColor = MaterialTheme.colors.background,
                        unfocusedIndicatorColor = MaterialTheme.colors.background,
                        disabledIndicatorColor = MaterialTheme.colors.background,
                    ),
                    textStyle = TextStyle(color = MaterialTheme.colors.onBackground),
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = {
                        Icon(

                            modifier = Modifier
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null,
                            tint = MaterialTheme.colors.onBackground,

                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear Text",
                            modifier = Modifier.clickable {
                                viewModel.onEvent(MealsEvent.OnClickClearText)
                            },
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                )
            }

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    viewModel.onEvent(MealsEvent.Refresh)
                }
            ) {

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Text(
                        text = "Categories",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.h2,
                        fontSize = 20.sp,
                    )
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 12.dp),
                        content = {
                            val categories: List<Categories> = categoriesState.value.categories
                            items(categories.size) { i ->
                                val category = categoriesState.value.categories[i]

                                CategoryItem(category = category) {
                                    viewModel.onEvent(MealsEvent.OnClickCategoryItem(it))
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Recipes",
                        modifier = Modifier.padding(top = 0.dp, start = 8.dp),
                        style = MaterialTheme.typography.h2,
                        fontSize = 20.sp,
                    )
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                    ) {
                        items(state.meals.size) { i ->
                            val meals = state.meals[i]
                            MealItem(
                                meals = meals,
                                navigator = navigator,
                                modifier = Modifier.clickable {
                                    // navigator.navigate(MealDetailsScreenDestination(meals))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FloatingMoreRecipesButton(billingPurchaseHelper: InAppPurchasesHelper) {
    val purchaseDone by billingPurchaseHelper.purchaseDone.collectAsState(false)
    val productName by billingPurchaseHelper.productName.collectAsState("")
    val purchaseStatus by billingPurchaseHelper.purchaseStatus.collectAsState("")

    Button(
        onClick = {
            billingPurchaseHelper.initializePurchase()
        },

        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface
        ),
        modifier = Modifier.wrapContentSize(),
        enabled = purchaseDone
    ) {
        Icon(
            Icons.Filled.More,
            contentDescription = "More Recipes",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(
            "Get More Recipes",
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }

    /*ExtendedFloatingActionButton(
        text = { Text(text = "Get More Recipes") },
        onClick = {
            // launch payments
            billingPurchaseHelper.makePurchase()
        },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = Color.White,
        icon = { Icon(Icons.Filled.More, "More Recipes") },
    )*/
}
