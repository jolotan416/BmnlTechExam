package com.studytest.bmnltechexam.developer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studytest.bmnltechexam.data.developer.Company
import com.studytest.bmnltechexam.data.developer.Developer

class DeveloperFormViewModel : ViewModel() {
    companion object {
        private val EMAIL_REGEX = "\\w+@[a-zA-Z_]+?(\\.[a-zA-Z]{2,3})+".toRegex()
        private const val PHONE_NUMBER_LENGTH = 11
        private const val MINIMUM_NAME_LENGTH = 3
    }

    var developerId: String = ""

    private val mIsNameValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData(true)
    }

    private val mIsEmailValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData(true)
    }

    private val mIsPhoneNumberValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData(true)
    }

    private val mIsCompanyNameValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData(true)
    }

    private val mDeveloper: MutableLiveData<Developer?> by lazy {
        MutableLiveData<Developer?>(null)
    }

    val isNameValid: LiveData<Boolean> = mIsNameValid
    val isEmailValid: LiveData<Boolean> = mIsEmailValid
    val isPhoneNumberValid: LiveData<Boolean> = mIsPhoneNumberValid
    val isCompanyNameValid: LiveData<Boolean> = mIsCompanyNameValid
    val developer: LiveData<Developer?> = mDeveloper

    fun initialize(developer: Developer?) {
        developerId = developer?.id ?: ""
        mDeveloper.value = developer
    }

    fun validateName(name: String?, isFromTextChange: Boolean = false): Boolean {
        val isValid = name?.length ?: 0 >= MINIMUM_NAME_LENGTH
        configureValidationLiveData(mIsNameValid, isValid, isFromTextChange)

        return isValid
    }

    fun validateEmail(email: String?, isFromTextChange: Boolean = false): Boolean {
        val isValid = email?.matches(EMAIL_REGEX) ?: false
        configureValidationLiveData(mIsEmailValid, isValid, isFromTextChange)

        return isValid
    }

    fun validatePhoneNumber(phoneNumber: String?, isFromTextChange: Boolean = false): Boolean {
        val isValid = phoneNumber?.length == PHONE_NUMBER_LENGTH
        configureValidationLiveData(mIsPhoneNumberValid, isValid, isFromTextChange)

        return isValid
    }

    fun validateCompanyName(companyName: String?, isFromTextChange: Boolean = false): Boolean {
        val isValid = companyName?.length ?: 0 >= MINIMUM_NAME_LENGTH
        configureValidationLiveData(mIsCompanyNameValid, isValid, isFromTextChange)

        return isValid
    }

    fun validateForm(name: String?, email: String?, phoneNumber: String?, companyName: String?) {
        var isValid = validateName(name, true)
        isValid = validateEmail(email, true) && isValid
        isValid = validatePhoneNumber(phoneNumber, true) && isValid
        isValid = validateCompanyName(companyName, true) && isValid

        mDeveloper.value = if (isValid) Developer(
            developerId,
            name!!,
            email!!,
            phoneNumber!!,
            Company(companyName!!)
        ) else null
    }

    private fun configureValidationLiveData(
        liveData: MutableLiveData<Boolean>,
        isValid: Boolean,
        isFromTextChange: Boolean
    ) {
        if (isValid && isFromTextChange) {
            liveData.value = true
        } else if (!isValid && !isFromTextChange) {
            liveData.value = false
        }
    }
}