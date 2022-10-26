package com.flexcode.yummy.presentation.meals_screen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexcode.yummy.domain.use_case.GetCategoriesUseCase
import com.flexcode.yummy.domain.use_case.GetMealsByCategoryUseCase
import com.flexcode.yummy.domain.use_case.GetMealsUseCase
import com.flexcode.yummy.utils.Constants.DELAY_TIME
import com.flexcode.yummy.utils.Resource
import com.flexcode.yummy.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getMealsByCategoryUseCase: GetMealsByCategoryUseCase
) : ViewModel() {

    private val _categoryState = mutableStateOf(CategoriesState())
    val categoryState: State<CategoriesState> = _categoryState

    var state by mutableStateOf(MealsState())

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    init {
        getMeals()
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { result ->
                when (result) {
                    is Resource.Success ->
                        _categoryState.value = result.data?.let {
                            categoryState.value.copy(
                                categories = it
                            )
                        }!!
                    is Resource.Loading -> {
                        _categoryState.value = categoryState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _categoryState.value = categoryState.value.copy(
                            isLoading = true,
                            categories = result.data!!
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: MealsEvent) {
        when (event) {
            is MealsEvent.Refresh -> getMeals(fetchFromRemote = true)
            is MealsEvent.OnSearchMeal -> {
                state = state.copy(searchMeal = event.meal)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(DELAY_TIME)
                    getMeals()
                }
            }
            is MealsEvent.OnClickCategoryItem -> {

                getMealsByCategoryUseCase(event.category).onEach {

                    state = state.copy(meals = it)
                }.launchIn(viewModelScope)
            }

            is MealsEvent.OnClickClearText -> {

                state = state.copy(searchMeal = "")
                getMeals()
            }
        }
    }

    private fun getMeals(
        meal: String = state.searchMeal.lowercase(),
        fetchFromRemote: Boolean = false
    ) {

        viewModelScope.launch {
            getMealsUseCase(meal, fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { meals ->
                                state = state.copy(
                                    meals = meals
                                )
                            }
                        }
                        is Resource.Error -> {
                            result.data?.let { meals ->
                                state = state.copy(
                                    meals = meals
                                )
                            }
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    result.message ?: "Unknown Error"
                                )
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = true)
                        }
                    }
                }
        }
    }
}
