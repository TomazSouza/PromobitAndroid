package com.seletivo.promobit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seletivo.promobit.gateway.database.entity.ContactEntity
import com.seletivo.promobit.gateway.resource.Resource
import com.seletivo.promobit.repository.UserRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    var updatedList: LiveData<Resource<MutableList<ContactEntity>>>? = null

    fun getAllContacts(): LiveData<Resource<MutableList<ContactEntity>>> {
        return userRepository.getAllContacts()
    }

}