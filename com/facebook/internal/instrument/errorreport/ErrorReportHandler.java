package com.facebook.internal.instrument.errorreport;

import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.InstrumentUtility;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ErrorReportHandler.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\u0013\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0007¢\u0006\u0002\u0010\nJ\u0012\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007J\b\u0010\u000e\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/internal/instrument/errorreport/ErrorReportHandler;", "", "()V", "MAX_ERROR_REPORT_NUM", "", "enable", "", "listErrorReportFiles", "", "Ljava/io/File;", "()[Ljava/io/File;", "save", "msg", "", "sendErrorReports", "facebook-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ErrorReportHandler {
    public static final ErrorReportHandler INSTANCE = new ErrorReportHandler();
    private static final int MAX_ERROR_REPORT_NUM = 1000;

    private ErrorReportHandler() {
    }

    @JvmStatic
    public static final void save(String msg) {
        try {
            new ErrorReportData(msg).save();
        } catch (Exception unused) {
        }
    }

    @JvmStatic
    public static final void enable() {
        if (FacebookSdk.getAutoLogAppEventsEnabled()) {
            sendErrorReports();
        }
    }

    @JvmStatic
    public static final void sendErrorReports() {
        if (Utility.isDataProcessingRestricted()) {
            return;
        }
        File[] listErrorReportFiles = listErrorReportFiles();
        final ArrayList arrayList = new ArrayList();
        for (File file : listErrorReportFiles) {
            ErrorReportData errorReportData = new ErrorReportData(file);
            if (errorReportData.isValid()) {
                arrayList.add(errorReportData);
            }
        }
        CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.facebook.internal.instrument.errorreport.ErrorReportHandler$$ExternalSyntheticLambda2
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int sendErrorReports$lambda$0;
                sendErrorReports$lambda$0 = ErrorReportHandler.sendErrorReports$lambda$0((ErrorReportData) obj, (ErrorReportData) obj2);
                return sendErrorReports$lambda$0;
            }
        });
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < arrayList.size() && i < 1000; i++) {
            jSONArray.put(arrayList.get(i));
        }
        InstrumentUtility.sendReports("error_reports", jSONArray, new GraphRequest.Callback() { // from class: com.facebook.internal.instrument.errorreport.ErrorReportHandler$$ExternalSyntheticLambda0
            @Override // com.facebook.GraphRequest.Callback
            public final void onCompleted(GraphResponse graphResponse) {
                ErrorReportHandler.sendErrorReports$lambda$2(arrayList, graphResponse);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int sendErrorReports$lambda$0(ErrorReportData errorReportData, ErrorReportData o2) {
        Intrinsics.checkNotNullExpressionValue(o2, "o2");
        return errorReportData.compareTo(o2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendErrorReports$lambda$2(ArrayList validReports, GraphResponse response) {
        Intrinsics.checkNotNullParameter(validReports, "$validReports");
        Intrinsics.checkNotNullParameter(response, "response");
        try {
            if (response.getError() == null) {
                JSONObject jsonObject = response.getJsonObject();
                boolean z = true;
                if (jsonObject == null || !jsonObject.getBoolean("success")) {
                    z = false;
                }
                if (z) {
                    Iterator it = validReports.iterator();
                    while (it.hasNext()) {
                        ((ErrorReportData) it.next()).clear();
                    }
                }
            }
        } catch (JSONException unused) {
        }
    }

    @JvmStatic
    public static final File[] listErrorReportFiles() {
        File instrumentReportDir = InstrumentUtility.getInstrumentReportDir();
        if (instrumentReportDir == null) {
            return new File[0];
        }
        File[] listFiles = instrumentReportDir.listFiles(new FilenameFilter() { // from class: com.facebook.internal.instrument.errorreport.ErrorReportHandler$$ExternalSyntheticLambda1
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                boolean listErrorReportFiles$lambda$3;
                listErrorReportFiles$lambda$3 = ErrorReportHandler.listErrorReportFiles$lambda$3(file, str);
                return listErrorReportFiles$lambda$3;
            }
        });
        Intrinsics.checkNotNullExpressionValue(listFiles, "reportDir.listFiles { di…OR_REPORT_PREFIX)))\n    }");
        return listFiles;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean listErrorReportFiles$lambda$3(File file, String name) {
        Intrinsics.checkNotNullExpressionValue(name, "name");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("^%s[0-9]+.json$", Arrays.copyOf(new Object[]{InstrumentUtility.ERROR_REPORT_PREFIX}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return new Regex(format).matches(name);
    }
}
