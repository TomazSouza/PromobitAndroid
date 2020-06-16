package com.seletivo.promobit.di.module

import androidx.lifecycle.ViewModel
import com.seletivo.promobit.di.annotation.ViewModelKey
import com.seletivo.promobit.ui.form.ContactViewModel
import com.seletivo.promobit.ui.home.HomeViewModel
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
    @ViewModelKey(ContactViewModel::class)
    abstract fun bindContractViewModel(contactViewModel: ContactViewModel): ViewModel


}