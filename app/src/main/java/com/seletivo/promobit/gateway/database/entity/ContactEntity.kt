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
    @field:SerializedName("photo")
    var photo: String,
    @field:SerializedName("new")
    var newContact: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0

    override fun toString(): String {
        return "ContactEntity(id=$id, name='$name', company='$company', photo='$photo', newContact=$newContact, userId=$userId)"
    }

}