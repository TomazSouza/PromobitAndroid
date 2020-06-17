package com.seletivo.promobit.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.seletivo.promobit.db.ContactObservable
import com.seletivo.promobit.enum.Status
import com.seletivo.promobit.gateway.resource.Resource
import com.seletivo.promobit.repository.UserRepository
import timber.log.Timber
import javax.inject.Inject

class ContactViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val resultRequestObserver = ResultRequestObserver(userRepository)
    var contactSavedLiveData: MutableLiveData<Resource<Int>> = MutableLiveData()

    var t = Observer<Resource<Int>> { resource ->
        contactSavedLiveData.value = resource
    }


    fun setContact(contactObservable: ContactObservable) {
        Timber.d("contatos: ${contactObservable.name}")
        resultRequestObserver.saveContacts(contactObservable)

        resultRequestObserver.contactSaved.observeForever(t)
    }

    class ResultRequestObserver(private val userRepository: UserRepository) :
        Observer<Resource<Int>> {

        private var contactSavedLiveData: LiveData<Resource<Int>>? = null
        var contactSaved: MutableLiveData<Resource<Int>> = MutableLiveData()

        fun saveContacts(contactObservable: ContactObservable) {
            contactSavedLiveData = userRepository.saveContact(contactObservable)
            contactSavedLiveData?.observeForever(this)
        }

        override fun onChanged(result: Resource<Int>?) {
            when (result?.status) {
                Status.SUCCESS -> {
                    contactSaved.postValue(Resource.success(result.data))
                }
                Status.ERROR -> {
                    Timber.e("error: ${result.message}")
                }
                Status.LOADING -> {
                    // ignore
                    Timber.e("LOADING:")
                }
            }
        }

    }

}