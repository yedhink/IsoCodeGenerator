package com.example.android.countrycodegenerator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.countrycodegenerator.R;

import java.util.*;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText code;
    TextView result;
    Map<String, String> countries = new HashMap<>();
    Map<String, String> isos = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        code = findViewById(R.id.txt);
        result = findViewById(R.id.result);
    }

    private void createCountryMap(){
        Locale locale = new Locale("en", "US", "WIN");
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put( iso, l.getDisplayCountry() );
        }
    }

    private void createIsoMap(){
        for (String iso : Locale.getISOCountries()) {
            isos.put(new Locale("", iso).getDisplayCountry(), iso.toUpperCase());
        }
    }

    public void iso(View V){
        String cd1 = code.getText().toString();
        if(cd1.length() > 2) {
            cd1 = Character.toUpperCase(cd1.charAt(0)) + cd1.substring(1).toLowerCase();
            createIsoMap();
            result.setText(isos.get(cd1));
        }
        else {
            cd1 = cd1.toUpperCase();
            createCountryMap();
            result.setText(countries.get(cd1));
        }
    }
}
