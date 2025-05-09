package com.google.firebase.crashlytics.internal.settings;

import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public interface SettingsProvider {
    Task<Settings> getSettingsAsync();

    Settings getSettingsSync();
}
