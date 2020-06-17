package com.seletivo.promobit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.seletivo.promobit.db.ContactObservable
import com.seletivo.promobit.gateway.WebService
import com.seletivo.promobit.gateway.database.ContactDb
import com.seletivo.promobit.gateway.database.dao.ContactDao
import com.seletivo.promobit.gateway.database.entity.ContactEntity
import com.seletivo.promobit.gateway.resource.ApiResponse
import com.seletivo.promobit.gateway.resource.ApiSuccessResponse
import com.seletivo.promobit.gateway.resource.NetworkBoundResource
import com.seletivo.promobit.gateway.resource.Resource
import com.seletivo.promobit.gateway.vo.BaseResponse
import com.seletivo.promobit.util.async.AppExecutors
import com.seletivo.promobit.util.livedata.AbsentLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val webService: WebService,
    private val appExecutors: AppExecutors,
    private val contactDb: ContactDb,
    private val contactDao: ContactDao
) : ViewModel() {

    fun getAllContacts(): LiveData<Resource<MutableList<ContactEntity>>> {
        return object :
            NetworkBoundResource<MutableList<ContactEntity>, BaseResponse>(appExecutors) {
            override fun saveCallResult(item: BaseResponse) {
                contactDb.runInTransaction {
                    contactDao.insertContact(item.contactList!!)
                }
            }

            override fun shouldFetch(data: MutableList<ContactEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<MutableList<ContactEntity>> {
                return contactDao.getAllContacts().switchMap {
                    if (it == null) {
                        AbsentLiveData.create()
                    } else {
                        contactDao.getAllContacts()
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<BaseResponse>> {
                return webService.getAllContacts()
            }

            override fun processResponse(response: ApiSuccessResponse<BaseResponse>): BaseResponse {
                return response.body
            }
        }.asLiveData()
    }

    fun saveContact(contactObservable: ContactObservable): LiveData<Resource<Int>> {
        val fetchSave = FetchSaveContact(webService, contactObservable)
        appExecutors.networkIO().execute(fetchSave)
        return fetchSave.liveData
    }

}