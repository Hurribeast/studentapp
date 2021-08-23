package be.leeroy.studentapp.view.connection;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.databinding.FragmentRegisterBinding;
import be.leeroy.studentapp.models.Option;
import be.leeroy.studentapp.models.School;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.utils.PreferencesUtils;
import be.leeroy.studentapp.utils.RegexValidation;
import be.leeroy.studentapp.view.ExtendFragment;
import be.leeroy.studentapp.view.main.MainActivity;
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
            School emptySchool = new School(0, "<Choose a school>");
            schools.add(0, emptySchool);

            ArrayAdapter<School> schoolsAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, schools);
            schoolsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            schoolsSpinner.setAdapter(schoolsAdapter);
        });

        viewModel.getToken().observe(getViewLifecycleOwner(), token -> {
            PreferencesUtils.set("token", token, getActivity());
            PreferencesUtils.set("userEmail", token, getActivity());
            navigateToActivity(MainActivity.class);
        });

        viewModel.getOptions().observe(getViewLifecycleOwner(), options -> {
            Option emptyOption = new Option("<Choose an option>");
            options.add(0, emptyOption);

            ArrayAdapter<Option> optionsAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, options);
            optionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            optionsSpinner.setAdapter(optionsAdapter);

            binding.registerOptionSpinner.setEnabled(true);
        });

        viewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if(error == Errors.EMAIL_EXIST) {
                binding.registerEmailInput.setError(error.getMessage());
            } else {
                displayError(error);
            }
        });

        binding.registerSchoolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                School school = (School) adapterView.getSelectedItem();
                if (school.getId() != 0) {
                    viewModel.loadOptions(school.getId());
                } else {
                    binding.registerOptionSpinner.setAdapter(null);
                    binding.registerOptionSpinner.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                binding.registerOptionSpinner.setEnabled(false);
            }
        });

        /* Birthday picker */
        binding.registerBirthdayInput.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog picker = new DatePickerDialog(getActivity(), (datePicker, year, month, day) -> {
                Calendar pickerDate = Calendar.getInstance();
                pickerDate.set(year, month, day);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.FRENCH);
                String formattedDate = simpleDateFormat.format(pickerDate.getTime());

                binding.registerBirthdayInput.setText(formattedDate);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            picker.show();
        });

        /* Back button */
        binding.registerBackButton.setOnClickListener(view -> navigateToBackFragment());

        /* Register button */
        binding.registerButton.setOnClickListener(view -> {
            String email = binding.registerEmailInput.getText().toString();
            String password = binding.registerPasswordInput.getText().toString();
            String lastname = binding.registerLastnameInput.getText().toString();
            String firstname = binding.registerFirstnameInput.getText().toString();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.FRENCH);
            Date birthday = null;

            try {
                birthday = simpleDateFormat.parse(binding.registerBirthdayInput.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Integer bloc = Integer.parseInt(binding.registerBlocInput.getText().toString());
            String optionname = ((Option) binding.registerOptionSpinner.getSelectedItem()).getName();
            Integer optionschool = ((School) binding.registerSchoolSpinner.getSelectedItem()).getId();
            String passwordToConfirm = binding.registerConfirmPasswordInput.getText().toString();

            if(validForm(email, password, passwordToConfirm, lastname, firstname, birthday, optionname, optionschool, bloc)) {
                viewModel.registerUser(email, password, lastname, firstname, birthday, optionname, optionschool, bloc);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.loadSchools();
    }

    private boolean validForm(String email, String password, String confirmationPassword, String last_name, String first_name, Date birthday, String option_name, Integer option_school, Integer bloc) {
        boolean isValid = true;
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

        if(last_name.equals("") || first_name.equals("") || option_name.equals("") || option_school == null || bloc == null) {
            isValid = false;
        }

        return isValid;
    }

    public School getSelectedItem(View v) {
        return (School) schoolsSpinner.getSelectedItem();
    }
}