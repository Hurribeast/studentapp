package be.leeroy.studentapp.view.main;

import android.os.Bundle;

import be.leeroy.studentapp.R;
import be.leeroy.studentapp.view.ExtendActivity;

public class MainActivity extends ExtendActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}