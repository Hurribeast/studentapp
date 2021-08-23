package be.leeroy.studentapp.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import be.leeroy.studentapp.R
import be.leeroy.studentapp.databinding.FragmentChangePasswordBinding
import be.leeroy.studentapp.models.errors.Errors
import be.leeroy.studentapp.view.ExtendFragment
import be.leeroy.studentapp.viewmodel.ChangePasswordViewModel


class ChangePasswordFragment : ExtendFragment() {
    private lateinit var viewModel: ChangePasswordViewModel
    private lateinit var binding: FragmentChangePasswordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ChangePasswordViewModel::class.java)

        binding.changePasswordChangeButton.setOnClickListener{
            if(isValid())
                viewModel.changePassword(bearerAuth, binding.changePasswordPasswordInput.text.toString(), binding.changePasswordOldPasswordInput.text.toString())
        }

        binding.changePasswordBackButton.setOnClickListener{
            navigateToBackFragment()
        }

        viewModel.changed.observe(viewLifecycleOwner, {
            Toast.makeText(context, R.string.password_changed, Toast.LENGTH_SHORT).show()
            hideKeyboard(binding.root)
            navigateToFragment(binding.root, R.id.changePasswordFragment_to_feedFragment)
        })

        viewModel.error.observe(viewLifecycleOwner, {
            if(it == Errors.PASSWORD_INCORRECT) {
                binding.changePasswordOldPasswordInput.error = it.message
            }
        })
        return binding.root
    }

    private fun isValid(): Boolean {
        var isValid = true
        val oldPassword: String = binding.changePasswordOldPasswordInput.text.toString()
        val password: String = binding.changePasswordPasswordInput.text.toString()
        val passwordConfirmation: String = binding.changePasswordConfirmPasswordInput.text.toString()

        if(oldPassword == ""){
            binding.changePasswordOldPasswordInput.error = getString(R.string.error_empty_password)
            isValid = false
        } else {
            binding.changePasswordOldPasswordInput.error = null
        }

        when (password) {
            "" -> {
                binding.changePasswordPasswordInput.error = getString(R.string.error_empty_password)
                isValid = false
            }
            binding.changePasswordOldPasswordInput.text.toString() -> {
                binding.changePasswordPasswordInput.error = getString(R.string.changing_same_password)
                isValid = false
            }
            else -> {
                binding.changePasswordPasswordInput.error = null
            }
        }

        when {
            passwordConfirmation == "" -> {
                binding.changePasswordConfirmPasswordInput.error = getString(R.string.error_empty_password)
                isValid = false
            }
            password != passwordConfirmation -> {
                binding.changePasswordConfirmPasswordInput.error = getString(R.string.error_password_no_match)
                isValid = false
            }
            else -> {
                binding.changePasswordConfirmPasswordInput.error = null
            }
        }

        return isValid
    }
}