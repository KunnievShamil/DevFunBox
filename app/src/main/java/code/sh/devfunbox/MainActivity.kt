package code.sh.devfunbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import code.sh.devfunbox.core.ui.theme.DevFunBoxTheme
import code.sh.devfunbox.feature.root.RootScreen
import code.sh.devfunbox.feature.snake.SnakeScreen

private const val NAVIGATOR_KEY = "AppContentNavigator"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppContent()
        }
    }
}

@Composable
private fun AppContent() {
    DevFunBoxTheme {
        BottomSheetNavigator {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.systemBars)
            ) {
                Navigator(
                    screen = SnakeScreen(),
                    key = NAVIGATOR_KEY
                )
            }
        }
    }
}