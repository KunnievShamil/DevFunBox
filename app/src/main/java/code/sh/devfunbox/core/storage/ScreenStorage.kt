package code.sh.devfunbox.core.storage

import code.sh.devfunbox.domain.model.ScreenModel
import code.sh.devfunbox.feature.bezier_curves.BezierCurvesScreen
import code.sh.devfunbox.feature.dvd_screensaver.DvdSaverScreen

object ScreenStorage {
    fun getScreens(): List<ScreenModel> = listOf(
        ScreenModel(
            name = "Кривые безье",
            screen = BezierCurvesScreen()
        ),
        ScreenModel(
            name = "Заставка DVD",
            screen = DvdSaverScreen()
        )
    )
}