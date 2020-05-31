package org.themeselves.alberandroid.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.themeselves.alberandroid.ui.login.LoginViewModel

@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel):ViewModel
}