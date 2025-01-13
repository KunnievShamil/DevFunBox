package code.sh.devfunbox.feature.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import code.sh.devfunbox.core.ui.base.BaseScreen
import code.sh.devfunbox.feature.features.FeaturesUiEvent
import org.koin.compose.viewmodel.koinViewModel

class RootScreen : BaseScreen() {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val viewModel: RootViewModel = koinViewModel()

        LaunchedEffect(Unit) {
            viewModel.event.collect { event ->
                when (event) {
                    is FeaturesUiEvent.NavigateTo -> navigator.push(event.screen)
                }
            }
        }
    }
}