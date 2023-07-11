package com.main.tinkoffsummer2023.ui.model

import kotlinx.collections.immutable.persistentListOf

object MockBackend {
    val categories = persistentListOf<Category>(
        Category(
            1,
            "Одежда",
            "https://sun9-35.userapi.com/impg/NuGL_jS39YSKHybPgyT-r495OfBS4ESZa-PF1w/i0OHZP2_tAw.jpg?size=707x570&quality=96&sign=acae15615bd0d5e6ea1d70354159bc9c&type=album"
        ),
        Category(
            2,
            "Дом",
            "https://sun9-27.userapi.com/impg/mDRNXPGaJ22dHjo8VSW05CES9HsaJMizS6PKpw/VSaNxDzv_6U.jpg?size=744x570&quality=96&sign=fca330024e0757ff067a81d68c758b9b&type=album"
        ),
        Category(
            3,
            "Красота",
            "https://sun9-27.userapi.com/impg/mDRNXPGaJ22dHjo8VSW05CES9HsaJMizS6PKpw/VSaNxDzv_6U.jpg?size=744x570&quality=96&sign=fca330024e0757ff067a81d68c758b9b&type=album"
        ),
        Category(
            4,
            "Акссесуары",
            "https://sun9-57.userapi.com/impg/MrVFL5n7mcDEiiNYtx1BRUpA7DVEQxFm9PmhRw/AnTvVbSKt3M.jpg?size=744x570&quality=96&sign=441b148d0accde806d9026aa8b9fbc31&type=album"
        ),
        Category(
            5,
            "Электроника",
            "https://sun9-19.userapi.com/impg/JFDp-8G_u1-brq4sweUkZyyrnp_FOMockY2QYA/WzaX3U1Yqtc.jpg?size=744x570&quality=96&sign=eddab290b6c603d76399993261d387cc&type=album"
        ),
        Category(
            6,
            "Игрушки",
            "https://sun9-60.userapi.com/impg/FS0I1hztJjaMJR0_Nc-OFX7ZaZuqrBdO64l7Pg/iGI21WmiRqI.jpg?size=744x570&quality=96&sign=7b87407674c4e9858b17f80031c4a330&type=album"
        ),
        Category(
            5,
            "Зоотовары",
            "https://sun9-3.userapi.com/impg/-NbYB0FPlbIw65oOc6StypNuIsFSd9oyznnbWg/roOJ4sSN18s.jpg?size=768x768&quality=95&sign=019f7f22e7a4bee1334e342740796bd4&type=album"
        ),
    )

    val products = persistentListOf<Product>(
        Product(
            1,
            "Крем с Алоэ",
            20,
            4.5,
            "Крем на основе алоэ вера представляет собой увлажняющий и успокаивающий косметический продукт, созданный с использованием экстракта растения алоэ вера. Алоэ вера, известная своими лечебными свойствами, обладает множеством преимуществ для здоровья и кожи." +
                    "Одно из главных преимуществ алоэ вера для кожи - это его успокаивающие свойства. Крем с алоэ вера может помочь снять раздражение, воспаление и покраснение кожи, особенно в случае ожогов, солнечных ожогов или других раздражений. Он также может помочь уменьшить видимость покраснений и акне.",
            listOf(
                "https://sun9-16.userapi.com/impg/zzvVoDqHno-olPf6kuMv5q4oRFYw4jfdYlnJKA/7ASQOnURyc4.jpg?size=747x749&quality=96&sign=8731724b1d72778bb2d59cef23cdabe0&type=album",
                "https://sun9-54.userapi.com/impg/K1rOfZUXF0NmRs3OiCJPDxHCavEFORAVSM7c8w/ppFb0HhuS5A.jpg?size=750x750&quality=96&sign=70c6456ca9416340c1b4aa32a78b9c1a&type=album",
                "https://sun9-21.userapi.com/impg/CAzjba9uDhT4b5E27uBI1Re7cd72va10lLs5Sg/oQG64MKfbeI.jpg?size=750x750&quality=96&sign=c2d431b86786f8136ae685abdea71db0&type=album"
            )
        ),
        Product(
            2,
            "Крем \"Интенсивное увлажнение\"",
            50,
            3.6,
            "Крем на основе алоэ вера представляет собой увлажняющий и успокаивающий косметический продукт, созданный с использованием экстракта растения алоэ вера. Алоэ вера, известная своими лечебными свойствами, обладает множеством преимуществ для здоровья и кожи." +
                    "Одно из главных преимуществ алоэ вера для кожи - это его успокаивающие свойства. Крем с алоэ вера может помочь снять раздражение, воспаление и покраснение кожи, особенно в случае ожогов, солнечных ожогов или других раздражений. Он также может помочь уменьшить видимость покраснений и акне.",
            listOf(
                "https://sun9-53.userapi.com/impg/Guf4Oa6UpV2-TSI3h5klzkqq9dXXdKBwe_ixtg/vY_lIupS0IU.jpg?size=750x750&quality=96&sign=7f9c7cd789f6c0d63959a36d84eb5626&type=album",
                "https://sun9-38.userapi.com/impg/1S-rwD7UlIM-j4Xewgup9b5y0q-JXLhZaFUJ_g/ihp4lGV-XxU.jpg?size=733x733&quality=96&sign=cba0959de4ccf9acf93d7dd4f2c8906d&type=album",
                "https://sun9-67.userapi.com/impg/ILgcmkvvp_x4pyoPCq-_ZWYPJ9smZO0liGTJ_Q/JIRzI8Bab44.jpg?size=744x750&quality=96&sign=b3eba02f1899c5080c0eea7b4da99ae8&type=album"
            )
        ),
        Product(
            3,
            "Антивозрастной крем",
            53,
            3.2,
            "Крем на основе алоэ вера представляет собой увлажняющий и успокаивающий косметический продукт, созданный с использованием экстракта растения алоэ вера. Алоэ вера, известная своими лечебными свойствами, обладает множеством преимуществ для здоровья и кожи." +
                    "Одно из главных преимуществ алоэ вера для кожи - это его успокаивающие свойства. Крем с алоэ вера может помочь снять раздражение, воспаление и покраснение кожи, особенно в случае ожогов, солнечных ожогов или других раздражений. Он также может помочь уменьшить видимость покраснений и акне.",
            listOf(
                "https://sun9-67.userapi.com/impg/ILgcmkvvp_x4pyoPCq-_ZWYPJ9smZO0liGTJ_Q/JIRzI8Bab44.jpg?size=744x750&quality=96&sign=b3eba02f1899c5080c0eea7b4da99ae8&type=album",
                "https://sun9-53.userapi.com/impg/Guf4Oa6UpV2-TSI3h5klzkqq9dXXdKBwe_ixtg/vY_lIupS0IU.jpg?size=750x750&quality=96&sign=7f9c7cd789f6c0d63959a36d84eb5626&type=album",
                "https://sun9-38.userapi.com/impg/1S-rwD7UlIM-j4Xewgup9b5y0q-JXLhZaFUJ_g/ihp4lGV-XxU.jpg?size=733x733&quality=96&sign=cba0959de4ccf9acf93d7dd4f2c8906d&type=album",

            )
        ),
        Product(
            4,
            "Лак \"Добрый\"",
            999,
            4.2,
            "Другими полезными свойствами крема на основе алоэ вера являются его охлаждающий эффект и способность снизить воспаление. Это делает его идеальным средством для использования после солнечных ванн, бритья или в качестве успокаивающего ухода после насыщенного дня.",
            listOf(
                "https://sun9-38.userapi.com/impg/1S-rwD7UlIM-j4Xewgup9b5y0q-JXLhZaFUJ_g/ihp4lGV-XxU.jpg?size=733x733&quality=96&sign=cba0959de4ccf9acf93d7dd4f2c8906d&type=album",
                "https://sun9-55.userapi.com/impg/iljpEVPAtqNqXQdW2I9ly_TFNilpBgdDUV265Q/KGa_I1XBuA8.jpg?size=479x478&quality=95&sign=d4e9c3671104b66e977a661a5dfa9da0&type=album",
                "https://sun9-63.userapi.com/impg/KvcrHspBi7XJC0TtmzjQMyHOwUnQ2C415K0Oig/2RqFX_WY1cI.jpg?size=810x1080&quality=95&sign=f727193e0e38f0370d83fdc063b24dcb&type=album"
            )
        ),
        Product(
            5,
            "Крем \"Влажность\"",
            50,
            3.6,
            "Крем на основе алоэ вера представляет собой увлажняющий и успокаивающий косметический продукт, созданный с использованием экстракта растения алоэ вера. Алоэ вера, известная своими лечебными свойствами, обладает множеством преимуществ для здоровья и кожи." +
                    "Одно из главных преимуществ алоэ вера для кожи - это его успокаивающие свойства. Крем с алоэ вера может помочь снять раздражение, воспаление и покраснение кожи, особенно в случае ожогов, солнечных ожогов или других раздражений. Он также может помочь уменьшить видимость покраснений и акне.",
            listOf(
                "https://sun9-53.userapi.com/impg/Guf4Oa6UpV2-TSI3h5klzkqq9dXXdKBwe_ixtg/vY_lIupS0IU.jpg?size=750x750&quality=96&sign=7f9c7cd789f6c0d63959a36d84eb5626&type=album",
                "https://sun9-38.userapi.com/impg/1S-rwD7UlIM-j4Xewgup9b5y0q-JXLhZaFUJ_g/ihp4lGV-XxU.jpg?size=733x733&quality=96&sign=cba0959de4ccf9acf93d7dd4f2c8906d&type=album",
                "https://sun9-67.userapi.com/impg/ILgcmkvvp_x4pyoPCq-_ZWYPJ9smZO0liGTJ_Q/JIRzI8Bab44.jpg?size=744x750&quality=96&sign=b3eba02f1899c5080c0eea7b4da99ae8&type=album"
            )
        ),
        Product(
            6,
            "WD-40",
            10,
            4.9,
            "Крем на основе алоэ вера представляет собой увлажняющий и успокаивающий косметический продукт, созданный с использованием экстракта растения алоэ вера. Алоэ вера, известная своими лечебными свойствами, обладает множеством преимуществ для здоровья и кожи." +
                    "Одно из главных преимуществ алоэ вера для кожи - это его успокаивающие свойства. Крем с алоэ вера может помочь снять раздражение, воспаление и покраснение кожи, особенно в случае ожогов, солнечных ожогов или других раздражений. Он также может помочь уменьшить видимость покраснений и акне.",
            listOf(
                "https://sun9-67.userapi.com/impg/ILgcmkvvp_x4pyoPCq-_ZWYPJ9smZO0liGTJ_Q/JIRzI8Bab44.jpg?size=744x750&quality=96&sign=b3eba02f1899c5080c0eea7b4da99ae8&type=album",
                "https://sun9-53.userapi.com/impg/Guf4Oa6UpV2-TSI3h5klzkqq9dXXdKBwe_ixtg/vY_lIupS0IU.jpg?size=750x750&quality=96&sign=7f9c7cd789f6c0d63959a36d84eb5626&type=album",
                "https://sun9-38.userapi.com/impg/1S-rwD7UlIM-j4Xewgup9b5y0q-JXLhZaFUJ_g/ihp4lGV-XxU.jpg?size=733x733&quality=96&sign=cba0959de4ccf9acf93d7dd4f2c8906d&type=album",

                )
        ),
        Product(
            7,
            "Лак \"Добрый\"",
            60,
            4.1,
            "Другими полезными свойствами крема на основе алоэ вера являются его охлаждающий эффект и способность снизить воспаление. Это делает его идеальным средством для использования после солнечных ванн, бритья или в качестве успокаивающего ухода после насыщенного дня.",
            listOf(
                "https://sun9-38.userapi.com/impg/1S-rwD7UlIM-j4Xewgup9b5y0q-JXLhZaFUJ_g/ihp4lGV-XxU.jpg?size=733x733&quality=96&sign=cba0959de4ccf9acf93d7dd4f2c8906d&type=album",
                "https://sun9-55.userapi.com/impg/iljpEVPAtqNqXQdW2I9ly_TFNilpBgdDUV265Q/KGa_I1XBuA8.jpg?size=479x478&quality=95&sign=d4e9c3671104b66e977a661a5dfa9da0&type=album",
                "https://sun9-63.userapi.com/impg/KvcrHspBi7XJC0TtmzjQMyHOwUnQ2C415K0Oig/2RqFX_WY1cI.jpg?size=810x1080&quality=95&sign=f727193e0e38f0370d83fdc063b24dcb&type=album"
            )
        ),
    )

    val adminProducts = arrayListOf<Product>(
        Product(
            5,
            "Крем \"Влажность\"",
            50,
            3.6,
            "Крем на основе алоэ вера представляет собой увлажняющий и успокаивающий косметический продукт, созданный с использованием экстракта растения алоэ вера. Алоэ вера, известная своими лечебными свойствами, обладает множеством преимуществ для здоровья и кожи." +
                    "Одно из главных преимуществ алоэ вера для кожи - это его успокаивающие свойства. Крем с алоэ вера может помочь снять раздражение, воспаление и покраснение кожи, особенно в случае ожогов, солнечных ожогов или других раздражений. Он также может помочь уменьшить видимость покраснений и акне.",
            listOf(
                "https://sun9-53.userapi.com/impg/Guf4Oa6UpV2-TSI3h5klzkqq9dXXdKBwe_ixtg/vY_lIupS0IU.jpg?size=750x750&quality=96&sign=7f9c7cd789f6c0d63959a36d84eb5626&type=album",
                "https://sun9-38.userapi.com/impg/1S-rwD7UlIM-j4Xewgup9b5y0q-JXLhZaFUJ_g/ihp4lGV-XxU.jpg?size=733x733&quality=96&sign=cba0959de4ccf9acf93d7dd4f2c8906d&type=album",
                "https://sun9-67.userapi.com/impg/ILgcmkvvp_x4pyoPCq-_ZWYPJ9smZO0liGTJ_Q/JIRzI8Bab44.jpg?size=744x750&quality=96&sign=b3eba02f1899c5080c0eea7b4da99ae8&type=album"
            )
        ),
        Product(
            6,
            "WD-40",
            10,
            4.9,
            "Крем на основе алоэ вера представляет собой увлажняющий и успокаивающий косметический продукт, созданный с использованием экстракта растения алоэ вера. Алоэ вера, известная своими лечебными свойствами, обладает множеством преимуществ для здоровья и кожи." +
                    "Одно из главных преимуществ алоэ вера для кожи - это его успокаивающие свойства. Крем с алоэ вера может помочь снять раздражение, воспаление и покраснение кожи, особенно в случае ожогов, солнечных ожогов или других раздражений. Он также может помочь уменьшить видимость покраснений и акне.",
            listOf(
                "https://sun9-67.userapi.com/impg/ILgcmkvvp_x4pyoPCq-_ZWYPJ9smZO0liGTJ_Q/JIRzI8Bab44.jpg?size=744x750&quality=96&sign=b3eba02f1899c5080c0eea7b4da99ae8&type=album",
                "https://sun9-53.userapi.com/impg/Guf4Oa6UpV2-TSI3h5klzkqq9dXXdKBwe_ixtg/vY_lIupS0IU.jpg?size=750x750&quality=96&sign=7f9c7cd789f6c0d63959a36d84eb5626&type=album",
                "https://sun9-38.userapi.com/impg/1S-rwD7UlIM-j4Xewgup9b5y0q-JXLhZaFUJ_g/ihp4lGV-XxU.jpg?size=733x733&quality=96&sign=cba0959de4ccf9acf93d7dd4f2c8906d&type=album",

                )
        ),
        Product(
            7,
            "Лак \"Добрый\"",
            60,
            4.1,
            "Другими полезными свойствами крема на основе алоэ вера являются его охлаждающий эффект и способность снизить воспаление. Это делает его идеальным средством для использования после солнечных ванн, бритья или в качестве успокаивающего ухода после насыщенного дня.",
            listOf(
                "https://sun9-38.userapi.com/impg/1S-rwD7UlIM-j4Xewgup9b5y0q-JXLhZaFUJ_g/ihp4lGV-XxU.jpg?size=733x733&quality=96&sign=cba0959de4ccf9acf93d7dd4f2c8906d&type=album",
                "https://sun9-55.userapi.com/impg/iljpEVPAtqNqXQdW2I9ly_TFNilpBgdDUV265Q/KGa_I1XBuA8.jpg?size=479x478&quality=95&sign=d4e9c3671104b66e977a661a5dfa9da0&type=album",
                "https://sun9-63.userapi.com/impg/KvcrHspBi7XJC0TtmzjQMyHOwUnQ2C415K0Oig/2RqFX_WY1cI.jpg?size=810x1080&quality=95&sign=f727193e0e38f0370d83fdc063b24dcb&type=album"
            )
        ),
    )

    val usersDataBase = arrayListOf<User>()

    val ordersDataBase = arrayListOf<Order>()

    val cartProductDataBase = arrayListOf<CartProduct>()
}


