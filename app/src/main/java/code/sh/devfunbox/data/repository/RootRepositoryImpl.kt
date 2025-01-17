package code.sh.devfunbox.data.repository

import code.sh.devfunbox.core.storage.ScreenStorage
import code.sh.devfunbox.domain.model.ScreenModel
import code.sh.devfunbox.domain.repository.RootRepository

class RootRepositoryImpl(
    private val screenStorage: ScreenStorage
) : RootRepository {
    override fun getScreens(): List<ScreenModel> {
        return screenStorage.getScreens()
    }
}