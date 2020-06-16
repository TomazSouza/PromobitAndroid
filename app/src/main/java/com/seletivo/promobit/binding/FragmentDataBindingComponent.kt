package com.seletivo.promobit.binding

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import com.seletivo.promobit.binding.FragmentBindingAdapters

class FragmentDataBindingComponent(fragment: Fragment) : DataBindingComponent {
    private val adapter = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters(): FragmentBindingAdapters {
        return adapter
    }
}