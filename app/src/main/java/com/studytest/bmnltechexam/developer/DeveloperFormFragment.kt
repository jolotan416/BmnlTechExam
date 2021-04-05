package com.studytest.bmnltechexam.developer

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.studytest.bmnltechexam.R
import com.studytest.bmnltechexam.data.RequestState
import com.studytest.bmnltechexam.data.developer.Developer
import com.studytest.bmnltechexam.databinding.FragmentDeveloperFormBinding
import com.studytest.bmnltechexam.views.BmnlToolbarFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeveloperFormFragment(private val developerPageCallback: DeveloperPageCallback) :
    Fragment(R.layout.fragment_developer_form), TextWatcher {
    companion object {
        const val TAG = "DeveloperFormFragment"
    }

    private lateinit var binding: FragmentDeveloperFormBinding

    private val developersViewModel: DevelopersViewModel by activityViewModels()
    private val developerFormViewModel: DeveloperFormViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDeveloperFormBinding.bind(view)
        configureViews()
        initializeViewModel()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun afterTextChanged(value: Editable?) {
        binding.apply {
            developerFormViewModel.validateForm(
                nameTextField.textValue, emailTextField.textValue,
                phoneNumberTextField.textValue, companyNameTextField.textValue
            )
        }
    }

    private fun configureViews() {
        configureToolbar()
        configureTextFields()
    }

    private fun configureToolbar() {
        val toolbarData = BmnlToolbarFragment.ToolbarData(
            hasBackButton = true,
            title = getString(R.string.add_contact)
        )
        val arguments = Bundle().apply {
            putParcelable(BmnlToolbarFragment.TOOLBAR_DATA, toolbarData)
        }

        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(binding.toolbarContainer.id, BmnlToolbarFragment::class.java, arguments)
        }
    }

    private fun configureTextFields() {
        binding.apply {
            nameTextField.apply {
                setOnFocusChangeListener { view, b ->
                    if (!view.hasFocus()) {
                        developerFormViewModel.validateName(textValue)
                    }
                }
                addTextChangeListener(this@DeveloperFormFragment)
            }

            emailTextField.apply {
                setOnFocusChangeListener { view, b ->
                    if (!view.hasFocus()) {
                        developerFormViewModel.validateEmail(textValue)
                    }
                }
                addTextChangeListener(this@DeveloperFormFragment)
            }

            phoneNumberTextField.apply {
                setOnFocusChangeListener { view, b ->
                    if (!view.hasFocus()) {
                        developerFormViewModel.validatePhoneNumber(textValue)
                    }
                }
                addTextChangeListener(this@DeveloperFormFragment)
            }

            companyNameTextField.apply {
                setOnFocusChangeListener { view, b ->
                    if (!view.hasFocus()) {
                        developerFormViewModel.validateCompanyName(textValue)
                    }
                }
                addTextChangeListener(this@DeveloperFormFragment)
            }
        }
    }

    private fun initializeViewModel() {
        developersViewModel.apply {
            updateDeveloperRequestState.observe(viewLifecycleOwner) { requestState ->
                if (requestState != RequestState.SUCCESSFUL) return@observe

                parentFragmentManager.popBackStack()
                if (developerFormViewModel.developerId.isBlank()) {
                    developerPageCallback.showPage(DeveloperPage.DEVELOPER_DETAILS)
                }
            }

            displayedDeveloper.observe(viewLifecycleOwner) { developer ->
                developerFormViewModel.initialize(developer)
                binding.developer = developer

                displayedDeveloper.removeObservers(viewLifecycleOwner)
            }
        }

        developerFormViewModel.apply {
            isNameValid.observe(viewLifecycleOwner) { isNameValid ->
                binding.nameTextField.error =
                    if (!isNameValid) getString(R.string.name_error) else null
            }

            isEmailValid.observe(viewLifecycleOwner) { isEmailValid ->
                binding.emailTextField.error =
                    if (!isEmailValid) getString(R.string.email_error) else null
            }

            isPhoneNumberValid.observe(viewLifecycleOwner) { isPhoneNumberValid ->
                binding.phoneNumberTextField.error =
                    if (!isPhoneNumberValid) getString(R.string.phone_number_error) else null
            }

            isCompanyNameValid.observe(viewLifecycleOwner) { isCompanyNameValid ->
                binding.companyNameTextField.error =
                    if (!isCompanyNameValid) getString(R.string.company_name_error) else null
            }

            developer.observe(viewLifecycleOwner) { developer ->
                binding.apply {
                    val isValid = developer != null
                    isFormValid = isValid

                    configureSubmitButton(isValid, developer)
                }
            }
        }
    }

    private fun configureSubmitButton(isFormValid: Boolean, developer: Developer?) {
        val typedValue = TypedValue()
        val typedArray = requireContext().obtainStyledAttributes(
            typedValue.data,
            intArrayOf(R.attr.colorAccent)
        )
        val colorAccent = typedArray.getColor(0, 0)
        typedArray.recycle()

        val buttonColor = if (isFormValid) colorAccent else ContextCompat.getColor(
            requireContext(),
            R.color.hintGray
        )

        binding.submitButton.apply {
            backgroundTintList = ColorStateList.valueOf(buttonColor)
            setOnClickListener {
                developersViewModel.createDeveloper(developer ?: return@setOnClickListener)
            }
        }
    }
}