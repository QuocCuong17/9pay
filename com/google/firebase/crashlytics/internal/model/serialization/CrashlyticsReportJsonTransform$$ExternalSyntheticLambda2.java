package com.google.firebase.crashlytics.internal.model.serialization;

import android.util.JsonReader;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes4.dex */
public final /* synthetic */ class CrashlyticsReportJsonTransform$$ExternalSyntheticLambda2 implements CrashlyticsReportJsonTransform.ObjectParser {
    public static final /* synthetic */ CrashlyticsReportJsonTransform$$ExternalSyntheticLambda2 INSTANCE = new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda2();

    private /* synthetic */ CrashlyticsReportJsonTransform$$ExternalSyntheticLambda2() {
    }

    @Override // com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform.ObjectParser
    public final Object parse(JsonReader jsonReader) {
        CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame parseEventFrame;
        parseEventFrame = CrashlyticsReportJsonTransform.parseEventFrame(jsonReader);
        return parseEventFrame;
    }
}
