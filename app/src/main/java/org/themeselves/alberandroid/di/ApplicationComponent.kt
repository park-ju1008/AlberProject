package org.themeselves.alberandroid.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import org.themeselves.alberandroid.base.AlberApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ServiceModule::class,
        ApplicationModule::class,
        FragmentModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<AlberApplication> {

    override fun inject(application: AlberApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}