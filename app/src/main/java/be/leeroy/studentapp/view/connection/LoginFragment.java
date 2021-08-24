package be.leeroy.studentapp.view.connection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentLoginBinding;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.utils.PreferencesUtils;
import be.leeroy.studentapp.utils.RegexValidation;
import be.leeroy.studentapp.view.ExtendFragment;
import be.leeroy.studentapp.view.main.MainActivity;
import be.leeroy.studentapp.viewmodel.LoginViewModel;

public class LoginFragment extends ExtendFragment {

    public LoginFragment() {
    }

    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.loginRegisterButton.setOnClickListener(view -> navigateToFragment(view, R.id.loginFragment_to_registerFragment));

        binding.loginLoginButton.setOnClickListener(view -> {
            if(validForm()) {
                viewModel.loginUser(binding.loginEmailInput.getText().toString(), binding.loginPasswordInput.getText().toString());
            }
        });

        viewModel.getToken().observe(getViewLifecycleOwner(), token -> {
            PreferencesUtils.set("token", token, requireActivity());
            PreferencesUtils.set("userEmail", binding.loginEmailInput.getText().toString(), requireActivity());
            navigateToActivity(MainActivity.class);
        });

        viewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if (error == Errors.PASSWORD_INCORRECT)
                binding.loginEmailInput.setError(error.getMessage());
            else
                displayError(error);
        });

        return binding.getRoot();
    }

    /*
     ******* UTILS
     */
    private Boolean validForm() {
        boolean valid = true;

        String emailToVerify = binding.loginEmailInput.getText().toString();
        String passwordToVerify = binding.loginPasswordInput.getText().toString();

        if(emailToVerify.equals("")) {
            binding.loginEmailInput.setError(getString(R.string.error_empty_email));
            valid = false;
        } else {
            if(!RegexValidation.email(emailToVerify)) {
                binding.loginEmailInput.setError(getString(R.string.error_email_format));
                valid = false;
            } else {
                binding.loginEmailInput.setError(null);
            }
        }

        if(passwordToVerify.equals("")) {
            binding.loginPasswordInput.setError(getString(R.string.error_empty_password));
            valid = false;
        } else {
            binding.loginPasswordInput.setError(null);
        }

        return valid;
    }
}