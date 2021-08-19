package be.leeroy.studentapp.view.connection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import be.leeroy.studentapp.databinding.FragmentRegisterBinding;
import be.leeroy.studentapp.viewmodel.RegisterViewModel;

public class RegisterFragment extends ExtendFragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel viewModel;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding.registerBackButton.setOnClickListener(view -> navigateToBackFragment());
        //binding.registerButton.setOnClickListener(view ->navigateToFragment()); -> Créer une vue pour inscription validée

        return binding.getRoot();
    }

    private Boolean validForm() {
        Boolean isValid = true;
        String email = binding.registerEmailInput.getText().toString();
        String password = binding.registerPasswordInput.getText().toString();
        String confirmationPassword = binding.registerConfirmPasswordInput.getText().toString();
        String lastname = binding.registerLastnameInput.getText().toString();
        String firstname = binding.registerFirstnameInput.getText().toString();
        String birthday = binding.registerBirthdayInput.getText().toString();


        return isValid;
    }
}