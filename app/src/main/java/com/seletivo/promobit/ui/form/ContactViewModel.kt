package com.seletivo.promobit.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seletivo.promobit.repository.UserRepository
import javax.inject.Inject

class ContactViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}