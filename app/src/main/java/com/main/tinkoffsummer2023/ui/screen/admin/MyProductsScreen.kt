package com.main.tinkoffsummer2023.ui.screen.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.model.MockBackend
import com.main.tinkoffsummer2023.ui.model.Product
import com.main.tinkoffsummer2023.ui.screen.util.BaseGreenButton
import com.main.tinkoffsummer2023.ui.screen.util.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme

@Composable
fun MyProductsScreen(
    navController: NavController,
    viewModel: MyProductsViewModel = hiltViewModel(),
) {
    val myProducts = MockBackend.adminProducts

    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseTopAppBar(
                {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                {
                    Text(
                        text = "Мои товары",
                        style = CustomTheme.typography.base,
                    )
                },
            )
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter,
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    items(myProducts) {
                        ProductItem(
                            it,
                            { navController.navigate("product/${it.id}") },
                            { }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(90.dp))
                    }
                }
            }
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.padding(
                    vertical = CustomTheme.padding.horizontal
                ),
            ) {
                BaseGreenButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .padding(start = 24.dp),
                    text = "Создать карточку",
                    textStyle = CustomTheme.typography.baseWhite,
                ) {

                }

            }
        }
    }
}

@Composable
private fun ProductItem(
    product: Product,
    onImageClick: () -> Unit,
    onButtonClick: () -> Unit,
) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.images[0])
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(2.dp)
                )
                .clickable {
                    onImageClick.invoke()
                }
                .fillMaxWidth()
        )
        Text(
            text = product.name,
            style = CustomTheme.typography.base,
        )
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = CustomTheme.colors.secondaryBackground
            )
        ) {
            Text(
                text = "Редактировать",
                fontWeight = FontWeight(700),
                fontFamily = FontFamily(Font(R.font.montserrat_regular)),
                fontSize = 13.sp,
                color = CustomTheme.colors.secondaryText
            )

        }
    }
}


