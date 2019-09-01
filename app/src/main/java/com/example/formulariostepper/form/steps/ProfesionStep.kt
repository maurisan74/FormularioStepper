package com.example.formulariostepper.form.steps

import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.get
import com.example.formulariostepper.R
import ernestoyaquello.com.verticalstepperform.Step

class ProfesionStep @JvmOverloads constructor(title: String, subtitle: String = "") :
    Step<RadioGroup>(title, subtitle) {

    private var bAlarm: RadioGroup? = null
    private var vProfeSteps: View? = null
    private var radioGroup: RadioGroup? = null

    override fun createStepContentLayout(): View {

        val inflater = LayoutInflater.from(context)
        vProfeSteps = inflater.inflate(R.layout.profesion_datos, null, false)
        radioGroup = vProfeSteps!!.findViewById(R.id.radioGroup)
        return vProfeSteps!!
    }
    override fun isStepDataValid(stepData: RadioGroup): IsDataValid {
        return IsDataValid(true)
    }

    override fun restoreStepData(data: RadioGroup) {
        bAlarm = radioGroup
    }

    override fun getStepDataAsHumanReadableString(): String {
        radioGroup!!.visibility=View.VISIBLE
        var sSeleccion=""
        val id: Int = radioGroup!!.checkedRadioButtonId
        if (id==0){
            sSeleccion=radioGroup!![0].toString()
        }
        val checkedRadioButton = radioGroup?.findViewById(radioGroup!!.checkedRadioButtonId) as? RadioButton
        checkedRadioButton?.let {
            if (checkedRadioButton.isChecked)sSeleccion=checkedRadioButton.text.toString()
        }
        return sSeleccion
    }

    override fun onStepOpened(animated: Boolean) {
        // NADA
    }

    override fun onStepClosed(animated: Boolean) {
        // NADA
    }

    override fun onStepMarkedAsCompleted(animated: Boolean) {
        // NADA
    }

    override fun onStepMarkedAsUncompleted(animated: Boolean) {
        // NADA
    }

    override fun getStepData(): RadioGroup? {
        return radioGroup
    }
}