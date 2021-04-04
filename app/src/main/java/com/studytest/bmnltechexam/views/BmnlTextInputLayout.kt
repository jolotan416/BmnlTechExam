package com.studytest.bmnltechexam.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.textfield.TextInputLayout
import com.studytest.bmnltechexam.databinding.BmnlTextInputLayoutBinding

class BmnlTextInputLayout @JvmOverloads constructor(
    context: Context,
    private val attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextInputLayout(context, attributeSet, defStyleAttr) {
    private var binding: BmnlTextInputLayoutBinding? = null

    var textValue: String? = null
        get() = binding?.textField?.text?.toString()
        set(value) {
            field = value

            binding?.textField?.setText(value)
        }

    init {
        inflateViews()
    }

    private fun inflateViews() {
        val inflater = LayoutInflater.from(context)

        binding = BmnlTextInputLayoutBinding.inflate(inflater, this)
    }

    fun setHintValue(hint: String) {
        this.hint = hint
    }

    fun setInputType(inputType: Int) {
        binding?.textField?.inputType = inputType
    }
}