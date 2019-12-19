package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.widget.EditText;

import java.util.List;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    // Do steps 5 - 11 within SettingsFragment
    // DONE (10) Implement OnSharedPreferenceChangeListener from SettingsFragment

    // DONE (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets the summary of the preference

    private void setPreferenceSummary(Preference preference, Object value) {
        //Edit text
        if(preference instanceof EditTextPreference) {
            EditTextPreference preferenceET = (EditTextPreference) preference;
            preference.setSummary(preferenceET.getText());
        }
        //list
        else if(preference instanceof ListPreference) {
            ListPreference preferenceList = (ListPreference) preference;
            //find which item given value is in the list
            int prefIndex = preferenceList.findIndexOfValue(value.toString());
            if (prefIndex >= 0) {
                //set the summary to that values given label
                preference.setSummary(preferenceList.getEntries()[prefIndex]);
            }
        }

    }
    // DONE (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource


    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_screen);
        // Do step 9 within onCreatePreference
        // DONE (9) Set the preference summary on each preference that isn't a CheckBoxPreference
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();

        //iterate through all preferences in our preference screen for this fragment
        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount();
        for (int i =0; i<count ;i++) {
            Preference pref = prefScreen.getPreference(i);
            if(!(pref instanceof CheckBoxPreference)) {
                //get the value of the key for current preference
                setPreferenceSummary(pref, sharedPreferences.getString(pref.getKey(), ""));
            }
        }
    }
    // DONE (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop

    // DONE (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart


    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    // DONE (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref_changed = findPreference(key);
        if (pref_changed != null) {
            if (!(pref_changed instanceof CheckBoxPreference)) {
                setPreferenceSummary(pref_changed, sharedPreferences.getString(key, ""));
            }
        }
    }
}
