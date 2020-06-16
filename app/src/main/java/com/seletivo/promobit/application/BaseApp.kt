package com.seletivo.promobit.application

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.seletivo.promobit.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BaseApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        AppInjector.init(this)

    }

    override fun activityInjector() = dispatchingAndroidInjector

}