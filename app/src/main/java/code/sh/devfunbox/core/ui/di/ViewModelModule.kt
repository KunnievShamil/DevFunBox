package code.sh.devfunbox.core.ui.di

import code.sh.devfunbox.domain.model.ScreenModel
import code.sh.devfunbox.feature.bezier_curves.BezierCurvesViewModel
import code.sh.devfunbox.feature.features.FeaturesViewModel
import code.sh.devfunbox.feature.root.RootViewModel
import code.sh.devfunbox.feature.snake.SnakeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        RootViewModel(
            rootRepository = get()
        )
    }

    viewModel { (screens: List<ScreenModel>) ->
        FeaturesViewModel(screens = screens)
    }

    viewModel {
        BezierCurvesViewModel()
    }

    viewModel {
        SnakeViewModel()
    }
}