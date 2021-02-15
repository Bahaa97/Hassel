package com.bahaa.haseel.module

import com.bahaa.haseel.screen.checkOut.CheckOutViewModelImp
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
   viewModel { CheckOutViewModelImp(get()) }
}
