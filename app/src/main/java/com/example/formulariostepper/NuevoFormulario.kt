package com.example.formulariostepper

import android.app.Activity
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.formulariostepper.form.steps.ComentariosSteps
import com.example.formulariostepper.form.steps.CorreoStep
import com.example.formulariostepper.form.steps.NombreSteps
import com.example.formulariostepper.form.steps.ProfesionStep
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormView
import ernestoyaquello.com.verticalstepperform.listener.StepperFormListener

class NuevoFormulario : AppCompatActivity(), StepperFormListener,
    DialogInterface.OnClickListener {
    private var DNomStep: NombreSteps? = null
    private var DComenStep : ComentariosSteps? = null
    private var DCorreoStep: CorreoStep? = null
    private var DProfesionStep: ProfesionStep? = null

    private var progressDialog: ProgressDialog? = null
    private var verticalStepperForm: VerticalStepperFormView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_nuevo)

        val stepTitles = resources.getStringArray(R.array.steps_titles)
        DNomStep = NombreSteps(stepTitles[0])
        DCorreoStep = CorreoStep(stepTitles[1])
        DProfesionStep = ProfesionStep(stepTitles[2])
        DComenStep = ComentariosSteps(stepTitles[3])

        verticalStepperForm = findViewById(R.id.stepper_form)
        verticalStepperForm?.setup(this, DNomStep, DCorreoStep, DProfesionStep, DComenStep )!!.init()

    }
    override fun onClick(dialogInterface: DialogInterface, which: Int) {
        when (which) {

            -1 -> finish()

            -2 -> verticalStepperForm!!.cancelFormCompletionOrCancellationAttempt()
        }
    }
    override fun onCompletedForm() {
       val dataSavingThread = saveData()

        progressDialog = ProgressDialog(this)
        progressDialog!!.setCancelable(true)
        progressDialog!!.show()
        progressDialog!!.setTitle(getString(R.string.form_sending_data_message))
        progressDialog!!.setOnCancelListener {
            try {
                dataSavingThread.interrupt()
            } catch (e: RuntimeException) {
            } finally {
                verticalStepperForm!!.cancelFormCompletionOrCancellationAttempt()
            }
        }
    }

    override fun onCancelledForm() {
       //NADA
    }

    private fun saveData(): Thread {

        // Fake data saving effect
        val thread = Thread(Runnable {
            try {
                Thread.sleep(1500)
                val intent = intent
                setResult(Activity.RESULT_OK, intent)
                intent.putExtra("datosPersonales", DNomStep!!.getStepData())

                finish()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        })
        thread.start()

        return thread
    }
}

