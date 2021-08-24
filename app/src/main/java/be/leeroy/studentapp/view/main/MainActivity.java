package be.leeroy.studentapp.view.main;

import android.os.Bundle;
import android.util.Log;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.view.ExtendActivity;

public class MainActivity extends ExtendActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int count = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("debug", "COUNT : " + count);
    }
}