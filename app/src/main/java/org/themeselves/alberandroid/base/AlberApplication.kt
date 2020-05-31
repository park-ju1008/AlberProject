package org.themeselves.alberandroid.base

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import org.themeselves.alberandroid.di.DaggerApplicationComponent

class AlberApplication :DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerApplicationComponent.factory().create(applicationContext)
    }

}