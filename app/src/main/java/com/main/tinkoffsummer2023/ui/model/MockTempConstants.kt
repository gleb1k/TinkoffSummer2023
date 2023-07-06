package com.main.tinkoffsummer2023.ui.model

import kotlinx.collections.immutable.persistentListOf

object MockTempConstants {
    val categories = persistentListOf<Category>(
        Category(
            1,
            "котята",
            ""
        ),
        Category(
            2,
            "щенята",
            ""
        ),
        Category(
            3,
            "огурцы",
            ""
        ),
        Category(
            4,
            "помидоры",
            ""
        ),
        Category(
            4,
            "шаурмы",
            ""
        ),
    )

    val products = persistentListOf<Product>(
        Product(
            1,
            "Котик",
            20,
            4.5,
            "Хороший приятный котик супер топчик",
            ""
        ),
        Product(
            2,
            "Песик",
            25,
            0.3,
            "Хороший песик, срет в тапки",
            ""
        ),
        Product(
            3,
            "Огурец",
            100,
            3.2,
            "огурец зеленый колючий в пупырышку",
            ""
        ),
        Product(
            4,
            "Помидор",
            1000,
            1.0,
            "Помидор красный пахне як помидор",
            ""
        ),
    )
}


