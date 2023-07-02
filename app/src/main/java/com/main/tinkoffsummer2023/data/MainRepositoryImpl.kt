package com.main.tinkoffsummer2023.data

import com.main.tinkoffsummer2023.data.remote.MainApi
import com.main.tinkoffsummer2023.domain.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: MainApi,
) : MainRepository {


}
