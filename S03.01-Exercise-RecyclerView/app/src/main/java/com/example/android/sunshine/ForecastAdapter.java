package com.example.android.sunshine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

    private String[] mWeatherData;

    public ForecastAdapter(){

    }

    @NonNull
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //inflate list item xml into view - need context, layout inflater
        Context context = viewGroup.getContext();
        int layoutIdItem = R.layout.forecast_list; //xml layout to inflate
        LayoutInflater inflater = LayoutInflater.from(context); //inflater to inflate layout

        View view = inflater.inflate(layoutIdItem, viewGroup, false);
        ForecastAdapterViewHolder weatherHolder = new ForecastAdapterViewHolder(view);

        return weatherHolder;
    }

    public void setWeatherData(String[] mWeatherData) {
        this.mWeatherData = mWeatherData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapterViewHolder forecastAdapterViewHolder, int i) {
        //attach data to each viewholder
        forecastAdapterViewHolder.mWeatherTextView.setText(mWeatherData[i]);

    }

    @Override
    public int getItemCount() {
        if (mWeatherData == null) {
            return 0;
        }
        return mWeatherData.length;
    }

    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView mWeatherTextView;

        ForecastAdapterViewHolder(View view){
            super(view);
            mWeatherTextView = (TextView) view.findViewById(R.id.tv_weather_data);
        }

    }

}
