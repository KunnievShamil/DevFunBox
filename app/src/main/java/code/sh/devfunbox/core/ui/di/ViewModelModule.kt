package code.sh.devfunbox.core.ui.di

import code.sh.devfunbox.feature.features.FeaturesViewModel
import code.sh.devfunbox.feature.root.RootViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        RootViewModel(
            rootRepository = get()
        )
    }

    viewModel {
        FeaturesViewModel()
    }
}