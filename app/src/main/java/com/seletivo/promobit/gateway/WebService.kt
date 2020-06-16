package com.seletivo.promobit.gateway

import androidx.lifecycle.LiveData
import com.seletivo.promobit.db.ContactObservable
import com.seletivo.promobit.gateway.resource.ApiResponse
import com.seletivo.promobit.gateway.vo.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    @POST("contact")
    fun saveContact(@Body contactObservable: ContactObservable)

    @GET("contacts")
    fun getAllContacts(): LiveData<ApiResponse<BaseResponse>>

}