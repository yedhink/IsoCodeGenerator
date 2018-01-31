package com.example.android.countrycodegenerator;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.countrycodegenerator.R;

import java.util.*;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText code;
    TextView result;
    Map<String, String> countries = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
    Map<String, String> isos = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        code = findViewById(R.id.txt);
        result = findViewById(R.id.result);
    }

    //Creates TreeMap for Key as ISO code and Value as Country name
    private void createCountryMap() {
        Locale locale = new Locale("en", "US", "WIN");
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(iso, l.getDisplayCountry());
        }
    }

    //Creates TreeMap for Key as Country name and Value as ISO code
    private void createIsoMap() {
        for (String iso : Locale.getISOCountries()) {
            isos.put(new Locale("", iso).getDisplayCountry(), iso.toUpperCase());
        }
    }

    //Displays output when button is clicked
    public void iso(View V) {
        String cd1 = code.getText().toString();
        if (cd1.length() > 2) {
            //cd1 = Character.toUpperCase(cd1.charAt(0)) + cd1.substring(1).toLowerCase();
            createIsoMap();
            result.setText(isos.get(cd1));
        } else {
            cd1 = cd1.toUpperCase();
            createCountryMap();
            result.setText(countries.get(cd1));
        }
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
