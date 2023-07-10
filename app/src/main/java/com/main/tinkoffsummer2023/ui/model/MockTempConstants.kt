package com.main.tinkoffsummer2023.ui.model

import kotlinx.collections.immutable.persistentListOf

object MockTempConstants {
    val categories = persistentListOf<Category>(
        Category(
            1,
            "котята",
            "https://sun9-27.userapi.com/impg/45GI6ELfUBWgELV-RmqmRerLKLL9_69-c_rgbg/TlSjsRGMM7Q.jpg?size=1260x1234&quality=95&sign=7864f610d92dea23cb8767587369aee4&type=album"
        ),
        Category(
            2,
            "щенята",
            "https://sun9-28.userapi.com/impg/FQGvxe3Hos5Fzi95kyAebdD6A2_aTXFiz1o0Yw/qbYj_SCq-_k.jpg?size=621x698&quality=95&sign=017e399c690c958b09dde0e6606bd7b3&type=album"
        ),
        Category(
            3,
            "огурцы",
            "https://sun9-3.userapi.com/impg/-NbYB0FPlbIw65oOc6StypNuIsFSd9oyznnbWg/roOJ4sSN18s.jpg?size=768x768&quality=95&sign=019f7f22e7a4bee1334e342740796bd4&type=album"
        ),
        Category(
            4,
            "помидоры",
            "https://sun9-3.userapi.com/impg/-NbYB0FPlbIw65oOc6StypNuIsFSd9oyznnbWg/roOJ4sSN18s.jpg?size=768x768&quality=95&sign=019f7f22e7a4bee1334e342740796bd4&type=album"
        ),
        Category(
            4,
            "бананы",
            "https://sun9-3.userapi.com/impg/-NbYB0FPlbIw65oOc6StypNuIsFSd9oyznnbWg/roOJ4sSN18s.jpg?size=768x768&quality=95&sign=019f7f22e7a4bee1334e342740796bd4&type=album"
        ),
        Category(
            4,
            "люди",
            "https://sun9-3.userapi.com/impg/-NbYB0FPlbIw65oOc6StypNuIsFSd9oyznnbWg/roOJ4sSN18s.jpg?size=768x768&quality=95&sign=019f7f22e7a4bee1334e342740796bd4&type=album"
        ),
    )

    val products = persistentListOf<Product>(
        Product(
            1,
            "Котик",
            20,
            4.5,
            "Хороший приятный котик супер топчик",
            listOf(
                "https://sun9-58.userapi.com/impg/GP1eS-2r_--hEqwcCMHoOyVCJFhyw9OsPokxBQ/-bI5jEqtKyI.jpg?size=640x640&quality=95&sign=da5c208b80258b9d05028f2f628edd81&type=album",
                "https://sun9-55.userapi.com/impg/iljpEVPAtqNqXQdW2I9ly_TFNilpBgdDUV265Q/KGa_I1XBuA8.jpg?size=479x478&quality=95&sign=d4e9c3671104b66e977a661a5dfa9da0&type=album"
            )
        ),
        Product(
            2,
            "Песик",
            25,
            0.3,
            "Хороший песик, срет в тапки",
            listOf(
                "https://sun9-49.userapi.com/impg/CNVz8IInT71LD82ZD8x4r11h-MUEgyJeHix-vA/pDXM40nwNG0.jpg?size=756x734&quality=96&sign=f7487aa4eb9bd43e540ac6d1d78da346&type=album",
                "https://sun9-55.userapi.com/impg/iljpEVPAtqNqXQdW2I9ly_TFNilpBgdDUV265Q/KGa_I1XBuA8.jpg?size=479x478&quality=95&sign=d4e9c3671104b66e977a661a5dfa9da0&type=album",
                "https://sun9-63.userapi.com/impg/KvcrHspBi7XJC0TtmzjQMyHOwUnQ2C415K0Oig/2RqFX_WY1cI.jpg?size=810x1080&quality=95&sign=f727193e0e38f0370d83fdc063b24dcb&type=album"
            )
        ),
        Product(
            3,
            "Крем с Алоэ, Греция, пл. упаковка 200 мл",
            100,
            3.2,
            "огурец зеленый колючий в пупырышку",
            listOf(
                "https://sun9-1.userapi.com/impg/rpL3Nz8W8_EP5ynd9TCZiPMCJJc2JA7uJmjX3g/GzJkalRmIGA.jpg?size=422x422&quality=96&sign=d25110c016d3610d379a3577bd4b9256&type=album",
                "https://sun9-55.userapi.com/impg/iljpEVPAtqNqXQdW2I9ly_TFNilpBgdDUV265Q/KGa_I1XBuA8.jpg?size=479x478&quality=95&sign=d4e9c3671104b66e977a661a5dfa9da0&type=album",
                "https://sun9-63.userapi.com/impg/KvcrHspBi7XJC0TtmzjQMyHOwUnQ2C415K0Oig/2RqFX_WY1cI.jpg?size=810x1080&quality=95&sign=f727193e0e38f0370d83fdc063b24dcb&type=album"
            )
        ),
        Product(
            4,
            "Помидор",
            1000,
            1.0,
            "Помидор красный пахне як помидор",
            listOf(
                "https://sun9-1.userapi.com/impg/rpL3Nz8W8_EP5ynd9TCZiPMCJJc2JA7uJmjX3g/GzJkalRmIGA.jpg?size=422x422&quality=96&sign=d25110c016d3610d379a3577bd4b9256&type=album",
                "https://sun9-55.userapi.com/impg/iljpEVPAtqNqXQdW2I9ly_TFNilpBgdDUV265Q/KGa_I1XBuA8.jpg?size=479x478&quality=95&sign=d4e9c3671104b66e977a661a5dfa9da0&type=album",
                "https://sun9-63.userapi.com/impg/KvcrHspBi7XJC0TtmzjQMyHOwUnQ2C415K0Oig/2RqFX_WY1cI.jpg?size=810x1080&quality=95&sign=f727193e0e38f0370d83fdc063b24dcb&type=album"
            )
        ),
    )

    val orders = persistentListOf<Order>(
        Order(
            1,
            "1 мая",
            "10 мая",
            "В сборке",

            Product(
                1,
                "Котик",
                20,
                4.5,
                "Хороший приятный котик супер топчик",
                listOf(
                    "https://sun9-58.userapi.com/impg/GP1eS-2r_--hEqwcCMHoOyVCJFhyw9OsPokxBQ/-bI5jEqtKyI.jpg?size=640x640&quality=95&sign=da5c208b80258b9d05028f2f628edd81&type=album",
                    "https://sun9-55.userapi.com/impg/iljpEVPAtqNqXQdW2I9ly_TFNilpBgdDUV265Q/KGa_I1XBuA8.jpg?size=479x478&quality=95&sign=d4e9c3671104b66e977a661a5dfa9da0&type=album"
                )
            )
        ),
        Order(
            2,
            "29 июня",
            "1 августа",
            "Получен",

            products[3]
        ),
    )

    val cartProduct = listOf<CartProduct>()
}


