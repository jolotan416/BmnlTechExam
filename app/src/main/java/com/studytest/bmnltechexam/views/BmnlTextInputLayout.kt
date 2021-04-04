package com.studytest.bmnltechexam.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.textfield.TextInputLayout
import com.studytest.bmnltechexam.R
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
        retrieveAttributes()
    }

    private fun inflateViews() {
        val inflater = LayoutInflater.from(context)

        binding = BmnlTextInputLayoutBinding.inflate(inflater, this)
    }

    private fun retrieveAttributes() {
        val attributes =
            resources.obtainAttributes(attributeSet ?: return, R.styleable.BmnlTextInputLayout)
        hint = attributes.getString(R.styleable.BmnlTextInputLayout_hintValue)
        textValue = attributes.getString(R.styleable.BmnlTextInputLayout_textValue)

        attributes.recycle()
    }
}