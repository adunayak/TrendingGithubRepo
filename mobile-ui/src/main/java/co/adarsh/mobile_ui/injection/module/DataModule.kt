package co.adarsh.mobile_ui.injection.module

import co.adarsh.data.ProjectsDataRepository
import co.adarsh.domain.repository.ProjectsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: ProjectsDataRepository) : ProjectsRepository
}