package com.main.tinkoffsummer2023.ui.screen.catalog.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.model.Category
import com.main.tinkoffsummer2023.ui.model.MockBackend
import com.main.tinkoffsummer2023.ui.screen.util.CustomTextField
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme


@Composable
private fun CategoryScreenActions(
    navController: NavController,
    viewAction: CategoryAction?,
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            is CategoryAction.NavigateToCategory -> navController.navigate("catalog?query=${null}")
            is CategoryAction.NavigateToCatalog -> navController.navigate("catalog?query=${viewAction.query}")
        }
    }
}


@Composable
fun CategoryScreen(
    navController: NavController,
    viewModel: CategoryViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    LaunchedEffect(viewModel) {
        viewModel.event(CategoryEvent.OnLoad)
    }

    Content(
        state,
        viewModel::event
    )

    CategoryScreenActions(
        navController,
        action
    )
}

@Composable
fun Content(
    state: CategoryViewState,
    eventHandler: (CategoryEvent) -> Unit,
) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {

            TopAppBar(
                backgroundColor = CustomTheme.colors.secondaryBackground,
                elevation = 8.dp,
            ) {
                Box(
                    modifier = Modifier.padding(
                        horizontal = CustomTheme.padding.horizontal
                    )
                ) {
                    CustomTextField(
                        leadingIcon = {
                            Icon(
                                painterResource(id = R.drawable.search_green),
                                null,
                                tint = CustomTheme.colors.secondaryBackground,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        },
                        trailingIcon = null,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colors.surface,
                                RoundedCornerShape(percent = 40)
                            )
                            .padding(4.dp)
                            .height(30.dp),
                        fontSize = 16.sp,
                        placeholderText = "Искать на СкороХод",
                        value = state.query,
                        onValueChange = { eventHandler.invoke(CategoryEvent.OnQueryChange(it)) },
                        keyboardOptions = KeyboardOptions().copy(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                eventHandler.invoke(CategoryEvent.OnSearchClick(state.query))
                            }
                        ),
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp),
                ) {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 0.dp),
                            ) {
                                Text(
                                    text = "Каталог",
                                    style = CustomTheme.typography.heading
                                )
                            }
                        }
                        items(MockBackend.categories) {
                            CategoryItem(it) {
                                eventHandler.invoke(
                                    CategoryEvent.OnCategoryItemClick(it.id)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    onClick: () -> Unit,
) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(category.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.main_icon),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .clickable {
                    onClick.invoke()
                }
        )
        Text(
            text = category.name,
            style = CustomTheme.typography.base,
        )
    }
}


