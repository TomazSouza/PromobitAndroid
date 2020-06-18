package com.seletivo.promobit.ui.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.seletivo.promobit.gateway.resource.Resource
import com.seletivo.promobit.repository.UserRepository
import javax.inject.Inject

class FormViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var contactSavedLiveData: MutableLiveData<Resource<Int>> = MutableLiveData()

    private var savedObserver = Observer<Resource<Int>> { resource ->
        contactSavedLiveData.value = resource
    }

    fun setContact(contactObservable: ContactObservable) {
        userRepository.saveContact(savedObserver, contactObservable)
    }

}