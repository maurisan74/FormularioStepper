package com.example.formulariostepper.form.steps

import android.text.Editable
import android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
import android.text.TextWatcher
import android.view.View
import com.example.formulariostepper.EmailValidator
import com.example.formulariostepper.R
import com.google.android.material.textfield.TextInputEditText
import ernestoyaquello.com.verticalstepperform.Step

class CorreoStep @JvmOverloads constructor(title: String, subtitle: String = "") :
    Step<String>(title, subtitle)  {
    private var textCorreo: TextInputEditText? = null
   //rivate var ErrorString: String? = null

    override fun createStepContentLayout(): View {

        // We create this step view programmatically
        this.textCorreo = TextInputEditText(context)
        this.textCorreo!!.focusable=View.FOCUSABLE
        this.textCorreo!!.setHint(R.string.form_hint_correo)
        this.textCorreo!!.inputType=TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        this.textCorreo!!.isSingleLine = true
        this.textCorreo!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                markAsCompletedOrUncompleted(true)
            }

            override fun afterTextChanged(s: Editable) {}
        })
        this.textCorreo!!.setOnEditorActionListener { v, actionId, event ->
            formView.goToNextStep(true)
            false
        }

      //  ErrorString =
      //      context.resources.getString(R.string.error_name_min_characters)

        return this.textCorreo as TextInputEditText
    }
    override fun restoreStepData(data: String?) {
        if (this.textCorreo != null) {
            this.textCorreo!!.setText(data)
        }
    }

    override fun isStepDataValid(stepData: String?): IsDataValid {
        if (!EmailValidator.isEmailValid(stepData.toString())) {
            val titleError = String.format("Email No Valido")
            return Step.IsDataValid(false, titleError)
        } else {
            return Step.IsDataValid(true)
        }
    }

    override fun getStepDataAsHumanReadableString(): String {
        val name = stepData
        return if (name == null || name.isEmpty())
            context.getString(R.string.form_empty_field)
        else
            name
    }

    override fun getStepData(): String {
        val text = textCorreo!!.text
        return text?.toString() ?: ""
    }

    override fun onStepMarkedAsCompleted(animated: Boolean) {
        //NADA
    }

    override fun onStepOpened(animated: Boolean) {
        //NADA
    }

    override fun onStepMarkedAsUncompleted(animated: Boolean) {
        //NADA
    }

    override fun onStepClosed(animated: Boolean) {
        //NADA
    }

}