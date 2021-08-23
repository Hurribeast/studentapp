package be.leeroy.studentapp.view.connection;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.dataaccess.mappers.SchoolMapper;
import be.leeroy.studentapp.databinding.FragmentRegisterBinding;
import be.leeroy.studentapp.models.Option;
import be.leeroy.studentapp.models.School;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.utils.PreferencesUtils;
import be.leeroy.studentapp.utils.RegexValidation;
import be.leeroy.studentapp.view.ExtendFragment;
import be.leeroy.studentapp.view.main.FeedFragment;
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

        viewModel.getError().observe(getViewLifecycleOwner(), error -> {
           if(error == Errors.EMAIL_EXIST) {
               binding.registerEmailInput.setError(error.getMessage());
           } else {
               displayError(error);
           }
        });

        viewModel.getSchools().observe(getViewLifecycleOwner(), schools -> {
            School emptySchool = new School(0, "<Choose a school>");
            schools.add(0, emptySchool);

            ArrayAdapter<School> schoolsAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, schools);
            schoolsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            schoolsSpinner.setAdapter(schoolsAdapter);
        });

        viewModel.getToken().observe(getViewLifecycleOwner(), token -> {
            PreferencesUtils.set("token", token, getActivity());
            PreferencesUtils.set("email", token, getActivity());
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

        viewModel.getError().observe(getViewLifecycleOwner(), this::displayError);

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

        binding.registerBackButton.setOnClickListener(view -> navigateToBackFragment());
        binding.registerButton.setOnClickListener(view -> {
            String email = binding.registerEmailInput.getText().toString();
            String password = binding.registerPasswordInput.getText().toString();
            String lastname = binding.registerLastnameInput.getText().toString();
            String firstname = binding.registerFirstnameInput.getText().toString();
            Date birthday = new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH).parse(binding.registerBirthdayInput.getText().toString());
            Integer bloc = Integer.parseInt(binding.registerBlocInput.getText().toString());
            String optionname = ((Option) binding.registerOptionSpinner.getSelectedItem()).getName();
            Integer optionschool = ((School) binding.registerSchoolSpinner.getSelectedItem()).getId();

            if(validForm()) {
                // Le code pour l'inscription vient içi
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