package org.androidluckyguys.searchviewExample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.androidluckyguys.searchviewExample.R;
import org.androidluckyguys.searchviewExample.fragments.MainFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit();
        }
    }
}
