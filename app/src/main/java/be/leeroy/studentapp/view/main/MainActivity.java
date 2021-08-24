package be.leeroy.studentapp.view.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.view.ExtendActivity;

public class MainActivity extends ExtendActivity {

    private long timeWhenBack = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment instanceof FeedFragment){
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