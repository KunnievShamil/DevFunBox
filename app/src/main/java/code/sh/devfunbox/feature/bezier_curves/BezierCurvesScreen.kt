package code.sh.devfunbox.feature.bezier_curves

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import code.sh.devfunbox.core.ui.base.BaseScreen
import org.koin.compose.viewmodel.koinViewModel

class BezierCurvesScreen : BaseScreen() {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val viewModel: BezierCurvesViewModel = koinViewModel()
        val uiState by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.event.collect { event ->
                when (event) {
                    is BezierCurvesUiEvent.NavigateTo -> navigator.push(event.screen)
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.title,
                textAlign = TextAlign.Center
            )
        }
    }
}