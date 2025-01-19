package code.sh.devfunbox.feature.features

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import code.sh.devfunbox.core.ui.base.BaseScreen
import code.sh.devfunbox.domain.model.ScreenModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

class FeaturesScreen(private val screens: List<ScreenModel>) : BaseScreen() {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val viewModel: FeaturesViewModel = koinViewModel { parametersOf(screens) }
        val uiState by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.event.collect { event ->
                when (event) {
                    is FeaturesUiEvent.NavigateTo -> navigator.push(event.screen)
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn {
                itemsIndexed(uiState.screens) { index, screenModel ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    viewModel.onClickScreen(screen = screenModel.screen)
                                },
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            Text(
                                text = "${index + 1}",
                                fontSize = 18.sp
                            )

                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                text = screenModel.name,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}