package org.themeselves.alberandroid.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.themeselves.alberandroid.ui.login.LoginFragment


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun loginFragment(): LoginFragment
}