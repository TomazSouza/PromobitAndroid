package com.seletivo.promobit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.seletivo.promobit.enums.OrderBy
import com.seletivo.promobit.gateway.database.entity.ContactEntity
import com.seletivo.promobit.gateway.resource.Resource
import com.seletivo.promobit.repository.UserRepository
import com.seletivo.promobit.util.livedata.AbsentLiveData
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _query = MutableLiveData<OrderBy>()

    val results: LiveData<Resource<MutableList<ContactEntity>>> = _query.switchMap { search ->
        if (search == null) {
            AbsentLiveData.create()
        } else {
            userRepository.getAllContactsOrderBy(search)
        }
    }

    fun query(orderBy: OrderBy) {
        _query.value = orderBy
    }

}