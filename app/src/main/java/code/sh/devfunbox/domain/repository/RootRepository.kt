package code.sh.devfunbox.domain.repository

import code.sh.devfunbox.domain.model.ScreenModel

interface RootRepository {
    fun getScreens(): List<ScreenModel>
}