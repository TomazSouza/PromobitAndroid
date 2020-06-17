package com.seletivo.promobit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.seletivo.promobit.db.ContactObservable
import com.seletivo.promobit.gateway.WebService
import com.seletivo.promobit.gateway.resource.*
import timber.log.Timber

class FetchSaveContact(
    private val webService: WebService,
    private val contactObservable: ContactObservable
) : Runnable {

    private val _liveData = MutableLiveData<Resource<Int>>()
    val liveData: LiveData<Resource<Int>> = _liveData

    override fun run() {
        _liveData.postValue(Resource.loading(null))
        val response = webService.saveContact(contactObservable).execute()
        when (val apiResponse = ApiResponse.create(response)) {
            is ApiSuccessResponse -> {
                Timber.e("ApiSuccessResponse")
                _liveData.postValue(Resource.success(apiResponse.body.idContact))
            }
            is ApiEmptyResponse -> {
                _liveData.postValue(Resource.error("failure request data response is empty", null))
            }
            is ApiErrorResponse -> {
                _liveData.postValue(Resource.error(apiResponse.errorMessage, null))
            }
        }
    }

}