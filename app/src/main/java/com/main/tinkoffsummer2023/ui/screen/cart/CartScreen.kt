package com.main.tinkoffsummer2023.ui.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.main.tinkoffsummer2023.R
import com.main.tinkoffsummer2023.ui.model.MockTempConstants
import com.main.tinkoffsummer2023.ui.model.Product
import com.main.tinkoffsummer2023.ui.screen.BaseCounter
import com.main.tinkoffsummer2023.ui.screen.BaseGreenButton
import com.main.tinkoffsummer2023.ui.screen.BaseTopAppBar
import com.main.tinkoffsummer2023.ui.theme.custom.CustomTheme
import com.main.tinkoffsummer2023.ui.theme.custom.baseLightPalette

@Composable
fun CartScreen() {
    //EmptyCartScreen()
    NotEmptyCartScreen()
}

@Composable
fun NotEmptyCartScreen(

) {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseTopAppBar(
                {
                    IconButton(onClick = {
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                {
                    Text(
                        text = "Корзина",
                        style = CustomTheme.typography.base,
                    )
                },
                {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            painterResource(id = R.drawable.trash),
                            "backIcon",
                            tint = CustomTheme.colors.tertiaryColor,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 6.dp)
            ) {
                item {
                    Text(
                        text = "Товары(99)",
                        style = CustomTheme.typography.heading,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                items(MockTempConstants.products) {
                    CartItem(it)
                }
            }
        }
    }

}

@Composable
fun CartItem(
    product: Product
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 24.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.main_icon),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(2.dp)
                )
                .size(128.dp),

            )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 8.dp),
        ) {
            Text(
                text = product.name,
                style = CustomTheme.typography.base,
            )
            BaseGreenButton(
                modifier = Modifier
                    .height(36.dp)
                    .width(150.dp),
                text = "${product.price} бонуса",
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    color = baseLightPalette.secondaryText
                )
            ) {

            }
            Box(modifier = Modifier.align(Alignment.End)) {
                BaseCounter(count = 99)
            }

        }
    }
}


@Composable
fun EmptyCartScreen() {
    Surface(color = CustomTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            BaseTopAppBar(
                {
                    IconButton(onClick = {
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                {
                    Text(
                        text = "Корзина",
                        style = CustomTheme.typography.base,
                    )
                },
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 56.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.empty_cart),
                    contentDescription = "",
                    modifier = Modifier
                        .size(180.dp)
                        .padding()
                )
                Text(
                    text = "В корзине пока пусто",
                    style = CustomTheme.typography.base
                )
                BaseGreenButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 46.dp, vertical = 8.dp)
                        .height(52.dp),
                    text = "Перейти в каталог"
                ) { }

            }
        }
    }
}

