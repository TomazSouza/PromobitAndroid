package com.seletivo.promobit.gateway.vo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName
import com.seletivo.promobit.gateway.database.entity.ContactEntity

@JsonIgnoreProperties(ignoreUnknown = true)
class BaseResponse {
    @field:SerializedName("data")
    var contactList: MutableList<ContactEntity>? = null

    override fun toString(): String {
        return "BaseContact(contactList=$contactList)"
    }

}