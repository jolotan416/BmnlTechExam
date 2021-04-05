package com.studytest.bmnltechexam.views

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.view.setPadding
import com.google.android.material.textfield.TextInputLayout
import com.studytest.bmnltechexam.databinding.BmnlTextInputLayoutBinding

class BmnlTextInputLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
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

    override fun setOnFocusChangeListener(onFocusChangeListener: OnFocusChangeListener?) {
        super.setOnFocusChangeListener(onFocusChangeListener)

        binding?.textField?.onFocusChangeListener = onFocusChangeListener
    }

    fun setHintValue(hint: String) {
        this.hint = hint
    }

    fun setInputType(inputType: Int) {
        binding?.textField?.inputType = inputType
    }

    fun addTextChangeListener(textWatcher: TextWatcher) {
        binding?.textField?.addTextChangedListener(textWatcher)
    }

    private fun inflateViews() {
        val inflater = LayoutInflater.from(context)

        binding = BmnlTextInputLayoutBinding.inflate(inflater, this)
        setPadding(0)
    }
}