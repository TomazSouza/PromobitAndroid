package com.seletivo.promobit.gateway.database.entity

import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.seletivo.promobit.gateway.database.DbTypeConverters

@TypeConverters(DbTypeConverters::class)
@Entity(primaryKeys = ["userId"])
class Contact(
    @field:SerializedName("name")
    var name: String? = "",
    @field:SerializedName("company")
    var company: String? = "",
    @field:SerializedName("email")
    var email: String? = "",
    @field:SerializedName("phone")
    var phone: String? = "",
    @field:SerializedName("website")
    var website: String? = "",
    @field:SerializedName("custom_note")
    var customNote: String? = "",
    @field:SerializedName("photo")
    var photo: String? = ""
) {

    @field:SerializedName("id")
    var id: Int = 0

    override fun toString(): String {
        return "Contact(name=$name, company=$company, email=$email, phone=$phone, website=$website, customNote=$customNote, photo=$photo, id=$id)"
    }

}