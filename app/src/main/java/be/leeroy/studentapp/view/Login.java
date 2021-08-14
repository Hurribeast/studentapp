package be.leeroy.studentapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.viewmodel.LoginViewModel;

public class Login extends AppCompatActivity {
    private EditText email, password;
    private TextView emailHeader, passwordHeader, register;
    private Button loginButton;

    private LoginViewModel viewModel;


    @Override
    protected void onStart() {
        super.onStart();

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
                String emailStringed = email.getText().toString();
                String passwordStringed = password.getText().toString();
                viewModel.loginUser(emailStringed, passwordStringed);
            }
        });


    }
}
