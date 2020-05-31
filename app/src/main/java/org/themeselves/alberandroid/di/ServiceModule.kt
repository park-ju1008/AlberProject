package org.themeselves.alberandroid.di

import dagger.Module
import dagger.Provides
import org.themeselves.alberandroid.data.source.remote.UserService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit):UserService{
        return retrofit.create(UserService::class.java)
    }
}