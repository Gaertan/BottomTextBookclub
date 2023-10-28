package com.example.bottomtextbookclub.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    // the method is used to set the language at runtime
    public static Context setLocale(Context context, String language) {
        persist(context, language);
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        persistLanguage(context, language);

        Resources resources = context.getResources();
        resources.getConfiguration().locale = locale;
        resources.updateConfiguration(resources.getConfiguration(), resources.getDisplayMetrics());


        // updating the language for devices above android nougat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        }
        // for devices having lower version of android os
        return updateResourcesLegacy(context, language);
    }
    public static Context wrapContext(Context context) {
        Locale savedLocale = createLocaleFromSavedLanguage(context);
        if (savedLocale == null) {
            return context;
        }
        Locale.setDefault(savedLocale);

        Configuration newConfig = new Configuration();
        newConfig.setLocale(savedLocale);
        return context.createConfigurationContext(newConfig);
    }

    public static void persistLanguage(Context context, String language) {
        SharedPreferences preferences = context.getSharedPreferences("LanguagePrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    public static String getLanguage(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("LanguagePrefs", Context.MODE_PRIVATE);
        return preferences.getString(SELECTED_LANGUAGE, null);
    }
    private static Locale createLocaleFromSavedLanguage(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("mainPrefs", Context.MODE_PRIVATE);
        String selectedLanguage = preferences.getString("Locale.Helper.Selected.Language", null);

        if (selectedLanguage != null) {
            return new Locale(selectedLanguage);
        }

        return null;
    }
    private static void persist(Context context, String language) {
        SharedPreferences preferences = context.getSharedPreferences("mainPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    // the method is used update the language of application by creating
    // object of inbuilt Locale class and passing language argument to it
    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);
    }


    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }










}
