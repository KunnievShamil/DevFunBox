package code.sh.devfunbox.core.storage.di

import code.sh.devfunbox.core.storage.ScreenStorage
import org.koin.dsl.module

val storageModule = module {
    factory<ScreenStorage> {
        ScreenStorage
    }
}