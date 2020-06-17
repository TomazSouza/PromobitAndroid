package com.seletivo.promobit.gateway

import androidx.lifecycle.LiveData
import com.seletivo.promobit.db.ContactObservable
import com.seletivo.promobit.gateway.resource.ApiResponse
import com.seletivo.promobit.gateway.vo.BaseResponse
import com.seletivo.promobit.gateway.vo.SavedResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    @POST("contact")
    fun saveContact(@Body contactObservable: ContactObservable): Call<SavedResponse>

    @GET("contacts")
    fun getAllContacts(): LiveData<ApiResponse<BaseResponse>>

}