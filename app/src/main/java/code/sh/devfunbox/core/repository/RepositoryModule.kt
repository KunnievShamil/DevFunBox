package code.sh.devfunbox.core.repository

import code.sh.devfunbox.data.repository.RootRepositoryImpl
import code.sh.devfunbox.domain.repository.RootRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<RootRepository> {
        RootRepositoryImpl()
    }
}
