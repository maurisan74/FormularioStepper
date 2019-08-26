package com.example.formulariostepper

import android.content.Context
import android.graphics.Typeface

class EmailValidator {
    companion object {
        @JvmStatic val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(.{1,})";
        fun isEmailValid(email: String): Boolean {
            return EMAIL_REGEX.toRegex().matches(email)
        }
    }
}

fun Context.fuente() =Typeface.createFromAsset(getAssets(),"roboto_regular.ttf")

