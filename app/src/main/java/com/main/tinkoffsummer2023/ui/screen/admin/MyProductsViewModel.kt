package com.main.tinkoffsummer2023.ui.screen.admin

import androidx.lifecycle.ViewModel
import com.main.tinkoffsummer2023.domain.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyProductsViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

}
