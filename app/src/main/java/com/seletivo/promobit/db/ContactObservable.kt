package com.seletivo.promobit.db

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.seletivo.promobit.BR

open class ContactObservable : BaseObservable() {

    @field:SerializedName("name")
    @get:Bindable
    var name: String? = "João Carlos"
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @field:SerializedName("company")
    @get:Bindable
    var company: String? = "Microsoft"
        set(value) {
            field = value
            notifyPropertyChanged(BR.company)
        }

    @field:SerializedName("email")
    @get:Bindable
    var email: String? = "joao_carlos20@hotmail.com"
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @field:SerializedName("phone")
    @get:Bindable
    var phone: String? = "11955552314"
        set(value) {
            field = value
            notifyPropertyChanged(BR.phone)
        }

    @field:SerializedName("website")
    @get:Bindable
    var website: String? = "www.mywebsite.com.br"
        set(value) {
            field = value
            notifyPropertyChanged(BR.website)
        }

    @field:SerializedName("custom_note")
    @get:Bindable
    var customNote: String? = "Tudo pela Microsoft"
        set(value) {
            field = value
            notifyPropertyChanged(BR.customNote)
        }


}