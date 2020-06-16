package com.seletivo.promobit.di.component

import android.app.Application
import com.seletivo.promobit.application.BaseApp
import com.seletivo.promobit.di.module.AppModule
import com.seletivo.promobit.di.module.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, MainActivityModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(baseApp: BaseApp)

}