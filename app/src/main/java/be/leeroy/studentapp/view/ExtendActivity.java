package be.leeroy.studentapp.view;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import be.leeroy.studentapp.R;

public class ExtendActivity extends AppCompatActivity {

    public void navigateToActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
