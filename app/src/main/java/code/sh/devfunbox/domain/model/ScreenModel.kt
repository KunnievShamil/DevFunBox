package code.sh.devfunbox.domain.model

import code.sh.devfunbox.core.ui.base.BaseScreen
import java.io.Serializable

data class ScreenModel(
    val name: String,
    val screen: BaseScreen
) : Serializable