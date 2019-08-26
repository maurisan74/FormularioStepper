package com.example.formulariostepper.form.steps

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.formulariostepper.R
import com.example.formulariostepper.fuente
import com.google.android.material.textfield.TextInputEditText
import ernestoyaquello.com.verticalstepperform.Step

class NombreSteps @JvmOverloads constructor(title: String, subtitle: String = "") :
    Step<String>(title, subtitle)  {
    private var textNombre: TextInputEditText? = null
    private var ErrorString: String? = null

    override fun createStepContentLayout(): View {

        // We create this step view programmatically
        this.textNombre = TextInputEditText(context)
        this.textNombre!!.setHint(R.string.form_hint_nom)
        this.textNombre!!.isSingleLine = true
        this.textNombre!!.focusable
        this.textNombre!!.setTypeface(context.fuente())
        this.textNombre!!.focusable=View.FOCUSABLE_AUTO
        this.textNombre!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                markAsCompletedOrUncompleted(true)
            }

            override fun afterTextChanged(s: Editable) {}
        })
        this.textNombre!!.setOnEditorActionListener { v, actionId, event ->
            formView.goToNextStep(true)
            false
        }

        ErrorString =
            context.resources.getString(R.string.error_name_min_characters)

        return this.textNombre as TextInputEditText
    }
    override fun restoreStepData(data: String?) {
        if (this.textNombre != null) {
            this.textNombre!!.setText(data)
        }
    }

    override fun isStepDataValid(stepData: String?): IsDataValid {
        if (stepData!!.length < MIN_CHARACTERS_ALARM_NAME) {
            val titleError = String.format(ErrorString!!, MIN_CHARACTERS_ALARM_NAME)
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
        val text = textNombre!!.text
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
    companion object {

        private val MIN_CHARACTERS_ALARM_NAME = 3
    }
}