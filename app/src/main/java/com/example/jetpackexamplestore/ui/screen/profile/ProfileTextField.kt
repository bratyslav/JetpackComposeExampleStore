package com.example.jetpackexamplestore.ui.screen.profile

import android.content.Context
import com.example.jetpackexamplestore.R

enum class ProfileTextField {
    NAME, SURNAME, PHONE;

    fun getFieldName(context: Context): String {
        return when (this) {
            NAME -> context.getString(R.string.profile_name_fieldname)
            SURNAME -> context.getString(R.string.profile_surname_fieldname)
            PHONE -> context.getString(R.string.profile_phone_fieldname)
        }
    }
}