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
import java.util.regex.Matcher
import java.util.regex.Pattern

class FormContactHelper(private val itemView: View?) : TextWatcher {

    private val mEditPassword = itemView?.findViewById<TextInputEditText>(R.id.editFieldNameId)
    private val mEditUser = itemView?.findViewById<TextInputEditText>(R.id.editFieldEmailId)
    private val mBtnLogin = itemView?.findViewById<AppCompatButton>(R.id.btnSaveContactId)

    private val textInputLayoutPassword = itemView?.findViewById<TextInputLayout>(R.id.textInputLayNameId)
    private val textInputLayoutEmail = itemView?.findViewById<TextInputLayout>(R.id.textInputLayEmailId)

    init {
        mEditUser?.addTextChangedListener(this)
        mEditPassword?.addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (validateUser() && validatePassword()) {
            mBtnLogin?.isEnabled = true
            textInputLayoutEmail?.isErrorEnabled = false
            textInputLayoutPassword?.isErrorEnabled = false
        } else {
            mBtnLogin?.isEnabled = false
        }
    }

    private fun validateUser(): Boolean {

        val fieldUser = mEditUser?.text.toString().trim()

        if (StringUtils.isEmpty(fieldUser)) {
            textInputLayoutPassword?.isErrorEnabled = false

            textInputLayoutEmail?.error = itemView?.context?.getString(R.string.preencha_email)
            textInputLayoutEmail?.isErrorEnabled = true
            return false
        }

        if (fieldUser.contains("@") && !Patterns.EMAIL_ADDRESS.matcher(fieldUser).matches()) {
            textInputLayoutPassword?.isErrorEnabled = false

            textInputLayoutEmail?.error =
                itemView?.context?.getString(R.string.preencha_corretamente)
            textInputLayoutEmail?.isErrorEnabled = true
            return false
        }

        if (!fieldUser.contains("@")) {
            textInputLayoutPassword?.isErrorEnabled = false

            val pattern: Pattern = Pattern.compile("[^0-9]")
            val matcher: Matcher = pattern.matcher(fieldUser)
            val number: String = matcher.replaceAll("")

            if (!false) {
                textInputLayoutEmail?.error =
                    itemView?.context?.getString(R.string.preencha_cpf_corretamente)
                textInputLayoutEmail?.isErrorEnabled = true
                return false
            }

            return true
        }

        textInputLayoutEmail?.isErrorEnabled = false

        return true
    }

    private fun validatePassword(): Boolean {

        val fieldPassword = mEditPassword?.text.toString().trim()

        if (StringUtils.isEmpty(fieldPassword)) {
            textInputLayoutEmail?.isErrorEnabled = false

            textInputLayoutPassword?.error = itemView?.context?.getString(R.string.preencha_senha)
            textInputLayoutPassword?.isErrorEnabled = true
            return false
        }

        if (!"ValidatePassword".isBlank()) {
            textInputLayoutEmail?.isErrorEnabled = false

            textInputLayoutPassword?.error = itemView?.context?.getString(R.string.regras_senha)
            textInputLayoutPassword?.isErrorEnabled = true
            return false
        }

        textInputLayoutPassword?.isErrorEnabled = false
        return true
    }

    fun getUser(): ContactObservable {
        return ContactObservable()
    }

}