package be.leeroy.studentapp.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import be.leeroy.studentapp.R
import be.leeroy.studentapp.databinding.FragmentChangePasswordBinding
import be.leeroy.studentapp.view.ExtendFragment
import be.leeroy.studentapp.viewmodel.ChangePasswordViewModel

class ChangePasswordFragment : ExtendFragment() {

    private lateinit var viewModel: ChangePasswordViewModel
    private lateinit var binding: FragmentChangePasswordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_change_password, container, false)
        viewModel = ViewModelProvider(this).get(ChangePasswordViewModel::class.java)
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)

        binding.changePasswordChangeButton.setOnClickListener{
            if(isValid())
                viewModel.changePassword(bearerAuth, binding.changePasswordPasswordInput.getText().toString(), binding.changePasswordOldPasswordInput.getText().toString())
        }

        viewModel.changed.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                Toast.makeText(context, R.string.password_changed, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, R.string.not_actual_password, Toast.LENGTH_SHORT).show()
            }
        })
        return root
    }

    fun isValid(): Boolean {
        var isValid : Boolean = true
        var password: String = binding.changePasswordPasswordInput.getText().toString()
        var passwordConfirmation: String = binding.changePasswordConfirmPasswordInput.getText().toString()

        if(password.equals("") || passwordConfirmation.equals("")) {
            binding.changePasswordPasswordInput.setError(getString(R.string.error_empty_password))
            isValid = false
        }
        else if(!password.equals(passwordConfirmation)) {
            binding.changePasswordConfirmPasswordInput.setError(getString(R.string.error_password_no_match))
            isValid = false
        } else if(password.equals(binding.changePasswordOldPasswordInput.getText().toString())) {
            binding.changePasswordPasswordInput.setError(getString(R.string.changing_same_password))
        }
        return isValid;
    }
}