package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    TextView mDisplayWeatherTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mDisplayWeatherTV = findViewById(R.id.weather_data_detail_tv);
        // TODO (2) Display the weather forecast that was passed from MainActivity
        Intent parentIntent = getIntent();

        if(parentIntent.hasExtra("Weather")) {
            Bundle extras = parentIntent.getExtras();
            String weatherData = extras.getString("Weather");
            mDisplayWeatherTV.setText(weatherData);
        }


    }
}