package com.basgroup.turboservicebarcode.di

import com.basgroup.turboservicebarcode.data.Repository
import com.basgroup.turboservicebarcode.ui.foundItems.FoundItemsViewModel
import com.basgroup.turboservicebarcode.ui.login.LoginViewModel
import com.basgroup.turboservicebarcode.ui.pin.PinViewModel
import com.basgroup.turboservicebarcode.ui.scanMain.ScanMainViewModel
import com.basgroup.turboservicebarcode.ui.scanner.ScannerViewModel
import com.basgroup.turboservicebarcode.ui.warehouse.WarehouseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.dsl.module

fun injectFeature() = loadFeature
private val loadFeature by lazy {
    loadKoinModules(
        listOf(viewModelModule,fragmentModule,repositoryModule)
    )
}
val viewModelModule = module {
    viewModel { LoginViewModel(get(),get()) }
    viewModel { PinViewModel(get(),get()) }
    viewModel { ScanMainViewModel(get()) }
    viewModel { WarehouseViewModel(get(),get()) }
    viewModel { ScannerViewModel(get(),get()) }
    viewModel { FoundItemsViewModel(get(),get()) }
}
val fragmentModule = module {

}
val repositoryModule = module {
    single { Repository() }
}
