package com.seletivo.promobit.gateway

import com.seletivo.promobit.db.ContactObservable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    @POST("contact")
    fun saveContact(@Body contactObservable: ContactObservable)

    @GET("contacts")
    fun getAllContacts()

}