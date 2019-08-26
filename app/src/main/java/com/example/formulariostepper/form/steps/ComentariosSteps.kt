package com.example.formulariostepper.form.steps


import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.formulariostepper.R
import com.example.formulariostepper.fuente
import com.google.android.material.textfield.TextInputEditText
import ernestoyaquello.com.verticalstepperform.Step

class ComentariosSteps@JvmOverloads constructor(title: String, subtitle: String = "") :
    Step<String>(title, subtitle)  {
    private var textComent: TextInputEditText? = null
    private var ErrorString: String? = null

    override fun createStepContentLayout(): View {

        // We create this step view programmatically
        this.textComent = TextInputEditText(context)
        //this.textComent!!.setHint(R.string.form_hint_ape)
        this.textComent!!.focusable=View.FOCUSABLE_AUTO
        this.textComent!!.isSingleLine = false
        this.textComent!!.inputType= 12
        this.textComent!!.setTypeface(context.fuente())
        this.textComent!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                markAsCompletedOrUncompleted(true)
            }

            override fun afterTextChanged(s: Editable) {}
        })
        this.textComent!!.setOnEditorActionListener { v, actionId, event ->
            formView.goToNextStep(true)
            false
        }



        return this.textComent as TextInputEditText
    }
    override fun restoreStepData(data: String?) {
        if (this.textComent != null) {
            this.textComent!!.setText(data)
        }
    }

    override fun isStepDataValid(stepData: String?): IsDataValid {

            return Step.IsDataValid(true)
    }

    override fun getStepDataAsHumanReadableString(): String {
        val name = stepData
        return if (name == null || name.isEmpty())
            context.getString(R.string.form_empty_field)
        else
            name
    }

    override fun getStepData(): String {
        return textComent!!.text.toString()
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