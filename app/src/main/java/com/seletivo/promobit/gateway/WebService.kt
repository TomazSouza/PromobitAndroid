package com.seletivo.promobit.gateway

import com.seletivo.promobit.db.Contact
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    @POST("contact")
    fun saveContact(@Body contact: Contact)

    @GET("contacts")
    fun getAllContacts()

}