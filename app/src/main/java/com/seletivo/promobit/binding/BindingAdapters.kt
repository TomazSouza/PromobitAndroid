package com.seletivo.promobit.binding

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.seletivo.promobit.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("enableDisableButtonCancel")
    fun enableDisableButtonCancel(view: View, isEnable: Boolean) {
        view.isEnabled = if (isEnable) {
            if (view is AppCompatButton) {
                view.setTextColor(ContextCompat.getColor(view.context, R.color.colorPrimary))
            }
            true
        } else {
            if (view is AppCompatButton) {
                view.setTextColor(ContextCompat.getColor(view.context, R.color.white))
            }
            false
        }
    }



}