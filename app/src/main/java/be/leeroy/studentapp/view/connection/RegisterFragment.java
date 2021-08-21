package be.leeroy.studentapp.view.connection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentRegisterBinding;
import be.leeroy.studentapp.models.Option;
import be.leeroy.studentapp.models.School;
import be.leeroy.studentapp.utils.RegexValidation;
import be.leeroy.studentapp.view.ExtendFragment;
import be.leeroy.studentapp.viewmodel.RegisterViewModel;

public class RegisterFragment extends ExtendFragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel viewModel;
    private Spinner schoolsSpinner;
    private Spinner optionsSpinner;

    public RegisterFragment() {

    }

    //TODO
    // Fix le problème de l'affichage des options
    // Ajouter la feature d'inscription

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        schoolsSpinner = binding.registerSchoolSpinner;
        optionsSpinner = binding.registerOptionSpinner;

        viewModel.getSchools().observe(getViewLifecycleOwner(), schools -> {
            ArrayAdapter<School> schoolsAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, schools);
            schoolsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            schoolsSpinner.setAdapter(schoolsAdapter);
            viewModel.loadOptions(getSelectedItem(this.getView()).getId());
        });

        viewModel.getOptions().observe(getViewLifecycleOwner(), options -> {
            ArrayAdapter<Option> optionsAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, options);
            optionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            optionsSpinner.setAdapter(optionsAdapter);
        });

        binding.registerSchoolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //viewModel.loadOptions(schoolSelected.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        binding.registerBackButton.setOnClickListener(view -> navigateToBackFragment());
        binding.registerButton.setOnClickListener(view -> {
            if(validForm()) {
                // Le code pour l'inscription vient içi
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.loadSchools();
    }

    private Boolean validForm() {
        Boolean isValid = true;
        String email = binding.registerEmailInput.getText().toString();
        String password = binding.registerPasswordInput.getText().toString();
        String confirmationPassword = binding.registerConfirmPasswordInput.getText().toString();
        String birthday = binding.registerBirthdayInput.getText().toString();

        if(email.equals("")) {
            binding.registerEmailInput.setError(getString(R.string.error_empty_email));
            isValid = false;
        } else {
            if(!RegexValidation.email(email)) {
                binding.registerEmailInput.setError(getString(R.string.error_email_format));
                isValid = false;
            } else {
                binding.registerEmailInput.setError(null);
            }
        }

        if(password.equals("")) {
            binding.registerPasswordInput.setError(getString(R.string.error_empty_password));
            isValid = false;
        } else {
            if(!password.equals(confirmationPassword)) {
                binding.registerPasswordInput.setError(getString(R.string.error_password_no_match));
                isValid = false;
            } else {
                binding.registerPasswordInput.setError(null);
            }
        }

        return isValid;
    }

    public School getSelectedItem(View v) {
        return (School) schoolsSpinner.getSelectedItem();
    }
}