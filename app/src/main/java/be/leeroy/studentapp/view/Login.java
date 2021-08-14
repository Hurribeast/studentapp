package be.leeroy.studentapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import be.leeroy.studentapp.MainActivity;
import be.leeroy.studentapp.R;
import be.leeroy.studentapp.models.NetworkError;
import be.leeroy.studentapp.utils.PreferencesUtils;
import be.leeroy.studentapp.utils.RegexValidation;
import be.leeroy.studentapp.viewmodel.LoginViewModel;
public class Login extends AppCompatActivity {
    private EditText email, password;
    private TextView emailHeader, passwordHeader, register;
    private Button loginButton;

    private LoginViewModel viewModel;


    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        email = findViewById(R.id.login_form_email);
        password = findViewById(R.id.login_form_password);
        emailHeader = findViewById(R.id.login_form_email_header);
        passwordHeader = findViewById(R.id.login_form_password_header);
        loginButton = findViewById(R.id.login_form_login_button);
        register = findViewById(R.id.login_form_register_header);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validForm()) {
                    String emailStringed = email.getText().toString();
                    String passwordStringed = password.getText().toString();
                    viewModel.loginUser(emailStringed, passwordStringed);
                }
            }
        });

        viewModel.getToken().observe(this, token -> {
            PreferencesUtils.setToken(token, this);
            toMainActivity();
        });

        viewModel.getError().observe(this, this::displayError);
    }

    private void toMainActivity() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
    private void displayError(NetworkError error) {
        if(error != null) {
            Toast.makeText(this, error.getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void checkSession() {
        if(PreferencesUtils.getToken(this) != null) {
            toMainActivity();
        }
    }
    private Boolean validForm() {
        String emailToVerify = email.getText().toString();
        String passwordToVerify = password.getText().toString();

        if(emailToVerify.equals("")) {
            emailHeader.setError(getString(R.string.error_empty_email));
            return false;
        } else {
            if(!RegexValidation.email(emailToVerify)) {
                emailHeader.setError(getString(R.string.error_email_format));
                return false;
            } else {
                emailHeader.setError(null);
            }
        }

        if(passwordToVerify.equals("")) {
            passwordHeader.setError(getString(R.string.error_empty_password));
            return false;
        } else {
            passwordHeader.setError(null);
        }
        return true;
    }
}
