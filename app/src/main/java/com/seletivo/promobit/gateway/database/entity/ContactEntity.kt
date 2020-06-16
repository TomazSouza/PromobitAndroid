package com.seletivo.promobit.gateway.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

import com.seletivo.promobit.gateway.database.DbTypeConverters

@Entity
data class ContactEntity(
    @field:SerializedName("id")
    var id: Int,
    @field:SerializedName("name")
    var name: String,
    @field:SerializedName("company")
    var company: String,
    @field:SerializedName("email")
    var email: String,
    @field:SerializedName("phone")
    var phone: String,
    @field:SerializedName("website")
    var website: String,
    @field:SerializedName("custom_note")
    var customNote: String,
    @field:SerializedName("photo")
    var photo: String
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0
}