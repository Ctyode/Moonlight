package org.flamie.moon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.flamie.moon.util.Dimen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dimen.init(this);
        setContentView(new MoonView(this));
    }

}
