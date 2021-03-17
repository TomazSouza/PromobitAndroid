package com.seletivo.promobit.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seletivo.promobit.di.annotation.ViewModelKey
import com.seletivo.promobit.ui.form.FormViewModel
import com.seletivo.promobit.ui.home.HomeViewModel
import com.seletivo.promobit.util.factory.MyViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FormViewModel::class)
    abstract fun bindContractViewModel(formViewModel: FormViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}