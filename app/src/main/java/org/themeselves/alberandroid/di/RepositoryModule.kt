package org.themeselves.alberandroid.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import org.themeselves.alberandroid.data.source.UserDataSource
import org.themeselves.alberandroid.data.source.UserRepoImpl
import org.themeselves.alberandroid.data.source.UserRepository
import org.themeselves.alberandroid.data.source.remote.UserService
import org.themeselves.alberandroid.data.source.remote.UserRemoteDataSource
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [RepositoryModuleBinds::class])
class RepositoryModule{

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalDataSource

    @Singleton
    @RemoteDataSource
    @Provides
    fun provideUserRemoteDataSource(
        userService: UserService
    ): UserDataSource {
        return UserRemoteDataSource(userService)
    }
}

@Module
abstract class RepositoryModuleBinds {

    @Singleton
    @Binds
    abstract fun bindUserRepository(repo: UserRepoImpl): UserRepository
}