package be.leeroy.studentapp.view.starting;

import android.os.Bundle;

import com.auth0.android.jwt.JWT;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.utils.PreferencesUtils;
import be.leeroy.studentapp.view.ExtendActivity;
import be.leeroy.studentapp.view.connection.ConnectionActivity;
import be.leeroy.studentapp.view.main.MainActivity;

public class StartingActivity extends ExtendActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkSession();
    }

    private void checkSession() {
        String token = PreferencesUtils.getToken(this);
        if (token != null) {
            JWT jwtToken = new JWT(token);

            if(!jwtToken.isExpired(60))
                navigateToActivity(MainActivity.class);
            else
                navigateToActivity(ConnectionActivity.class);

        } else
            navigateToActivity(ConnectionActivity.class);
    }

}