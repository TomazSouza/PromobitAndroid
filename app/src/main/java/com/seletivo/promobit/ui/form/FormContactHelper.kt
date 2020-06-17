package com.seletivo.promobit.ui.form

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.seletivo.promobit.R
import com.seletivo.promobit.db.ContactObservable

import org.apache.commons.lang.StringUtils

class FormContactHelper(private val itemView: View?) : TextWatcher {

    private val editFieldNameId = itemView?.findViewById<TextInputEditText>(R.id.editFieldNameId)
    private val textInputLayNameId =
        itemView?.findViewById<TextInputLayout>(R.id.textInputLayNameId)

    private val editFieldCompanyId =
        itemView?.findViewById<TextInputEditText>(R.id.editFieldCompanyId)
    private val textInputLayCompanyId =
        itemView?.findViewById<TextInputLayout>(R.id.textInputLayCompanyId)

    private val editFieldEmailId = itemView?.findViewById<TextInputEditText>(R.id.editFieldEmailId)
    private val textInputLayEmailId =
        itemView?.findViewById<TextInputLayout>(R.id.textInputLayEmailId)

    private val editFieldPhoneId = itemView?.findViewById<TextInputEditText>(R.id.editFieldPhoneId)
    private val textInputLayPhoneId =
        itemView?.findViewById<TextInputLayout>(R.id.textInputLayPhoneId)

    private val editFieldSiteId = itemView?.findViewById<TextInputEditText>(R.id.editFieldSiteId)
    private val textInputLaySiteId =
        itemView?.findViewById<TextInputLayout>(R.id.textInputLaySiteId)

    private val editFieldCustomNoteId =
        itemView?.findViewById<TextInputEditText>(R.id.editFieldCustomNoteId)
    private val textInputLayCustomNoteId =
        itemView?.findViewById<TextInputLayout>(R.id.textInputLayCustomNoteId)


    private val btnSaveContactId = itemView?.findViewById<AppCompatButton>(R.id.btnSaveContactId)

    init {
        editFieldNameId?.addTextChangedListener(this)
        editFieldEmailId?.addTextChangedListener(this)
        editFieldCompanyId?.addTextChangedListener(this)
        editFieldPhoneId?.addTextChangedListener(this)
        editFieldSiteId?.addTextChangedListener(this)
        editFieldCustomNoteId?.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        btnSaveContactId?.isEnabled = (validateName() && validateCompany() &&
                validateEmail() && validatePhone()
                && validateSite() && validateCustomNote())

        if (validateName()) {
            textInputLayNameId?.isErrorEnabled = false
        }
        if (validateCompany()) {
            textInputLayCompanyId?.isErrorEnabled = false
        }
        if (validateEmail()) {
            textInputLayEmailId?.isErrorEnabled = false
        }
        if (validatePhone()) {
            textInputLayPhoneId?.isErrorEnabled = false
        }
        if (validateSite()) {
            textInputLaySiteId?.isErrorEnabled = false
        }
        if (validateCustomNote()) {
            textInputLayCustomNoteId?.isErrorEnabled = false
        }
    }

    private fun errorDisableTextInputLayout() {
        textInputLayEmailId?.isErrorEnabled = false
        textInputLayNameId?.isErrorEnabled = false
        textInputLayCompanyId?.isErrorEnabled = false
        textInputLayPhoneId?.isErrorEnabled = false
        textInputLaySiteId?.isErrorEnabled = false
        textInputLayCustomNoteId?.isErrorEnabled = false
    }

    private fun validateEmail(): Boolean {

        val fieldUser = editFieldEmailId?.text.toString().trim()

        if (StringUtils.isEmpty(fieldUser)) {
            textInputLayEmailId?.error = itemView?.context?.getString(R.string.is_empty_email)
            textInputLayEmailId?.isErrorEnabled = true
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(fieldUser).matches()) {
            textInputLayEmailId?.error =
                itemView?.context?.getString(R.string.preencha_corretamente)
            textInputLayEmailId?.isErrorEnabled = true
            return false
        }

        return true
    }

    private fun validateName(): Boolean {
        val fieldName = editFieldNameId?.text.toString().trim()

        if (StringUtils.isEmpty(fieldName) || fieldName.length < 6) {
            textInputLayNameId?.error = itemView?.context?.getString(R.string.is_empty_name)
            textInputLayNameId?.isErrorEnabled = true
            return false
        }

        return true
    }

    private fun validateCompany(): Boolean {
        val fieldCompany = editFieldCompanyId?.text.toString().trim()

        if (StringUtils.isEmpty(fieldCompany) || fieldCompany.length < 4) {
            textInputLayCompanyId?.error = itemView?.context?.getString(R.string.is_empty_company)
            textInputLayCompanyId?.isErrorEnabled = true
            return false
        }

        return true
    }

    private fun validateCustomNote(): Boolean {
        val fieldCustomNote = editFieldCustomNoteId?.text.toString().trim()

        if (StringUtils.isEmpty(fieldCustomNote)) {
            textInputLayCustomNoteId?.error =
                itemView?.context?.getString(R.string.is_empty_custom_note)
            textInputLayCustomNoteId?.isErrorEnabled = true
            return false
        }

        return true
    }

    private fun validatePhone(): Boolean {
        val fieldPhone = editFieldPhoneId?.text.toString().trim()

        if (StringUtils.isEmpty(fieldPhone)) {
            textInputLayPhoneId?.error = itemView?.context?.getString(R.string.is_empty_phone)
            textInputLayPhoneId?.isErrorEnabled = true
            return false
        }

        return true
    }

    private fun validateSite(): Boolean {

        val fieldSite = editFieldSiteId?.text.toString().trim()

        if (StringUtils.isEmpty(fieldSite) || fieldSite.length < 6) {
            textInputLaySiteId?.error = itemView?.context?.getString(R.string.is_empty_site)
            textInputLaySiteId?.isErrorEnabled = true
            return false
        }
        return true
    }

    fun getUser(): ContactObservable {
        return ContactObservable()
    }

}