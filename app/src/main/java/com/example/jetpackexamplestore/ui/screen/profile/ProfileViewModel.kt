package com.example.jetpackexamplestore.ui.screen.profile

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.annotation.ExperimentalCoilApi
import com.example.jetpackexamplestore.isNumeric
import com.example.jetpackexamplestore.store.Store
import com.example.jetpackexamplestore.store.entities.Customer
import com.example.jetpackexamplestore.store.entities.Seller
import com.example.jetpackexamplestore.ui.MainActivity

class ProfileViewModel @OptIn(ExperimentalFoundationApi::class,
    androidx.compose.ui.unit.ExperimentalUnitApi::class,
    androidx.compose.animation.ExperimentalAnimationApi::class,
    coil.annotation.ExperimentalCoilApi::class
) constructor(private val mainActivity: MainActivity) {

    private val _name = MutableLiveData("")
    private val _surname = MutableLiveData("")
    private val _phoneNum = MutableLiveData("")
    private val _areFieldsNotFilled = MutableLiveData(false)
    val name: LiveData<String> = _name
    val surname: LiveData<String> = _surname
    val phoneNum: LiveData<String> = _phoneNum
    val areFieldsNotFilled: LiveData<Boolean> = _areFieldsNotFilled

    fun loadProfile(): ProfileViewModel {
        val customer = Store.customer ?: return this
        _name.value = customer.name
        _surname.value = customer.surname
        _phoneNum.value = customer.phone
        return this
    }

    // TODO: Should return true if saved successfully, otherwise false
    @ExperimentalAnimationApi
    fun saveButtonOnClick() {
        val name = _name.value ?: return
        val surname = _surname.value ?: return
        val phoneNum = _phoneNum.value ?: return

        if (name.isNotEmpty() && surname.isNotEmpty() && phoneNum.isNotEmpty()) {
            val customer = Customer(name, surname, phoneNum)
            Store.updateCustomerProfile(customer)
        } else {
            _areFieldsNotFilled.value = true
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    fun showToast(message: String) {
        // TODO: move to MainActivity
        Toast.makeText(
            mainActivity.applicationContext,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun textFieldOnValueChange(textField: ProfileTextField, text: String) {
        if (text.isNotEmpty()) {
            // If is whitespace at the start
            if (text.length <= 1 && text.last().isWhitespace()) {
                return
            }
            // If are multiple whitespaces
            val prevCharIndex = text.length - 2
            if (prevCharIndex >= 0 && text[prevCharIndex].isWhitespace() && text.last().isWhitespace()) {
                return
            }
            // If is tab
            if (text.last() == '\n') {
                return
            }
        }
        when (textField) {
            ProfileTextField.NAME -> {
                if (text.length < MAX_NAME_LENGTH) {
                    _name.value = text
                }
            }
            ProfileTextField.SURNAME -> {
                if (text.length < MAX_SURNAME_LENGTH) {
                    _surname.value = text
                }
            }
            ProfileTextField.PHONE -> {
                if (text.isNumeric() && text.length < MAX_PHONE_NUM_LENGTH) {
                    _phoneNum.value = text
                }
            }
        }
    }

    companion object {
        const val MAX_NAME_LENGTH = 20
        const val MAX_SURNAME_LENGTH = 24
        const val MAX_PHONE_NUM_LENGTH = 15
    }

}