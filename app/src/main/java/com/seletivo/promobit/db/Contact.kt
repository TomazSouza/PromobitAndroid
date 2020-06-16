package com.seletivo.promobit.db

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.seletivo.promobit.BR

open class Contact : BaseObservable() {

    @get:Bindable
    var name: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var company: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.company)
        }

    @get:Bindable
    var email: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var phone: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.phone)
        }

    @get:Bindable
    var website: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.website)
        }

    @get:Bindable
    var custom_note: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.custom_note)
        }

}