package code.sh.devfunbox.core.di

import code.sh.devfunbox.core.repository.repositoryModule
import code.sh.devfunbox.core.storage.di.storageModule
import code.sh.devfunbox.core.ui.di.viewModelModule

val appModule = listOf(
    storageModule,
    repositoryModule,
    viewModelModule,
)