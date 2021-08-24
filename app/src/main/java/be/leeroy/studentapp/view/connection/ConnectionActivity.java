package be.leeroy.studentapp.view.connection;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.view.ExtendActivity;

public class ConnectionActivity extends ExtendActivity {

    private long timeWhenBack = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
    }

    @Override
    public void onBackPressed() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.connection_fragment_container);
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment instanceof LoginFragment){
            /* Verify for a second back */
            if(System.currentTimeMillis() - timeWhenBack < 2000 ) {
                finishAndRemoveTask();
            } else {
                Toast.makeText(this, getString(R.string.press_secondTime), Toast.LENGTH_SHORT).show();
                timeWhenBack = System.currentTimeMillis();
            }
        } else {
            super.onBackPressed();
        }
    }
}