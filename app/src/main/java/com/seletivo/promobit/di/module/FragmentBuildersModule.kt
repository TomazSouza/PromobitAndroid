package com.seletivo.promobit.di.module

import com.seletivo.promobit.ui.form.FormFragment
import com.seletivo.promobit.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeContactFormFragment(): FormFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

}