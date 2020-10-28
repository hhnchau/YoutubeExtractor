package com.hanet.newpipe.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import com.hanet.newpipe.R;

import org.schabi.newpipe.extractor.localization.ContentCountry;
import org.schabi.newpipe.extractor.localization.Localization;

import java.util.Locale;


public class PipeLocalization {
    public static Localization getPreferredLocalization(final Context context) {
        final String contentLanguage = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.content_language_key),
                        context.getString(R.string.default_localization_key));

        if (contentLanguage.equals(context.getString(R.string.default_localization_key))) {
            return Localization.fromLocale(Locale.getDefault());
        }
        return Localization.fromLocalizationCode(contentLanguage);
    }

    public static ContentCountry getPreferredContentCountry(final Context context) {
        final String contentCountry = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.content_country_key),
                        context.getString(R.string.default_localization_key));
        if (contentCountry.equals(context.getString(R.string.default_localization_key))) {
            return new ContentCountry(Locale.getDefault().getCountry());
        }
        return new ContentCountry(contentCountry);
    }
}
