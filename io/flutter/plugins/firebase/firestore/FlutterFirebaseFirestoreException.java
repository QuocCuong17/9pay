package io.flutter.plugins.firebase.firestore;

import com.facebook.internal.AnalyticsEvents;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestoreException;
import io.flutter.plugins.firebase.database.FlutterFirebaseDatabaseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class FlutterFirebaseFirestoreException extends Exception {
    private static final String ERROR_ABORTED = "The operation was aborted, typically due to a concurrency issue like transaction aborts, etc.";
    private static final String ERROR_ALREADY_EXISTS = "Some document that we attempted to create already exists.";
    private static final String ERROR_CANCELLED = "The operation was cancelled (typically by the caller).";
    private static final String ERROR_DATA_LOSS = "Unrecoverable data loss or corruption.";
    private static final String ERROR_DEADLINE_EXCEEDED = "Deadline expired before operation could complete. For operations that change the state of the system, this error may be returned even if the operation has completed successfully. For example, a successful response from a server could have been delayed long enough for the deadline to expire.";
    private static final String ERROR_FAILED_PRECONDITION = "Operation was rejected because the system is not in a state required for the operation's execution. If performing a query, ensure it has been indexed via the Firebase console.";
    private static final String ERROR_INTERNAL = "Internal errors. Means some invariants expected by underlying system has been broken. If you see one of these errors, something is very broken.";
    private static final String ERROR_INVALID_ARGUMENT = "Client specified an invalid argument. Note that this differs from failed-precondition. invalid-argument indicates arguments that are problematic regardless of the state of the system (e.g., an invalid field name).";
    private static final String ERROR_NOT_FOUND = "Some requested document was not found.";
    private static final String ERROR_OUT_OF_RANGE = "Operation was attempted past the valid range.";
    private static final String ERROR_PERMISSION_DENIED = "The caller does not have permission to execute the specified operation.";
    private static final String ERROR_RESOURCE_EXHAUSTED = "Some resource has been exhausted, perhaps a per-user quota, or perhaps the entire file system is out of space.";
    private static final String ERROR_UNAUTHENTICATED = "The request does not have valid authentication credentials for the operation.";
    private static final String ERROR_UNAVAILABLE = "The service is currently unavailable. This is a most likely a transient condition and may be corrected by retrying with a backoff.";
    private static final String ERROR_UNIMPLEMENTED = "Operation is not implemented or not supported/enabled.";
    private static final String ERROR_UNKNOWN = "Operation is not implemented or not supported/enabled.";
    private final String code;
    private final String message;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public FlutterFirebaseFirestoreException(FirebaseFirestoreException firebaseFirestoreException, Throwable th) {
        super(firebaseFirestoreException != null ? firebaseFirestoreException.getMessage() : "", th);
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        char c;
        String str6 = null;
        if (th == null || th.getMessage() == null) {
            str = ERROR_OUT_OF_RANGE;
        } else {
            String message = th.getMessage();
            str = ERROR_OUT_OF_RANGE;
            if (message.contains(":")) {
                Matcher matcher = Pattern.compile("([A-Z_]{3,25}):\\s(.*)").matcher(th.getMessage());
                if (matcher.find()) {
                    String trim = matcher.group(1).trim();
                    str2 = "out-of-range";
                    str3 = matcher.group(2).trim();
                    trim.hashCode();
                    switch (trim.hashCode()) {
                        case -1842427240:
                            if (trim.equals("DATA_LOSS")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1711692763:
                            if (trim.equals("INVALID_ARGUMENT")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1416305653:
                            if (trim.equals("PERMISSION_DENIED")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1031784143:
                            if (trim.equals("CANCELLED")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1025686472:
                            if (trim.equals("RESOURCE_EXHAUSTED")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case -849706474:
                            if (trim.equals("UNAUTHENTICATED")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case -476794961:
                            if (trim.equals("ABORTED")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case -376214182:
                            if (trim.equals("DEADLINE_EXCEEDED")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case 433141802:
                            if (trim.equals("UNKNOWN")) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        case 695165606:
                            if (trim.equals("OUT_OF_RANGE")) {
                                c = '\t';
                                break;
                            }
                            c = 65535;
                            break;
                        case 979228314:
                            if (trim.equals("FAILED_PRECONDITION")) {
                                c = '\n';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1023286998:
                            if (trim.equals("NOT_FOUND")) {
                                c = 11;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1353037501:
                            if (trim.equals("INTERNAL")) {
                                c = '\f';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1487498288:
                            if (trim.equals("UNAVAILABLE")) {
                                c = '\r';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1661336131:
                            if (trim.equals("ALREADY_EXISTS")) {
                                c = 14;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1854913705:
                            if (trim.equals("UNIMPLEMENTED")) {
                                c = 15;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    switch (c) {
                        case 0:
                            str3 = ERROR_DATA_LOSS;
                            str6 = "data-loss";
                            break;
                        case 1:
                            str3 = ERROR_INVALID_ARGUMENT;
                            str6 = "invalid-argument";
                            break;
                        case 2:
                            str6 = "permission-denied";
                            str3 = ERROR_PERMISSION_DENIED;
                            break;
                        case 3:
                            str3 = ERROR_CANCELLED;
                            str6 = AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED;
                            break;
                        case 4:
                            str6 = "resource-exhausted";
                            str3 = ERROR_RESOURCE_EXHAUSTED;
                            break;
                        case 5:
                            str6 = "unauthenticated";
                            str3 = ERROR_UNAUTHENTICATED;
                            break;
                        case 6:
                            str3 = ERROR_ABORTED;
                            str6 = "aborted";
                            break;
                        case 7:
                            str3 = ERROR_DEADLINE_EXCEEDED;
                            str6 = "deadline-exceeded";
                            break;
                        case '\b':
                            str6 = "unknown";
                            str3 = "Operation is not implemented or not supported/enabled.";
                            break;
                        case '\t':
                            str3 = str;
                            str6 = str2;
                            break;
                        case '\n':
                            str3 = str3.contains(FirebaseAnalytics.Param.INDEX) ? str3 : ERROR_FAILED_PRECONDITION;
                            str6 = "failed-precondition";
                            break;
                        case 11:
                            str3 = ERROR_NOT_FOUND;
                            str6 = "not-found";
                            break;
                        case '\f':
                            str3 = ERROR_INTERNAL;
                            str6 = "internal";
                            break;
                        case '\r':
                            str6 = "unavailable";
                            str3 = ERROR_UNAVAILABLE;
                            break;
                        case 14:
                            str3 = ERROR_ALREADY_EXISTS;
                            str6 = "already-exists";
                            break;
                        case 15:
                            str6 = "unimplemented";
                            str3 = "Operation is not implemented or not supported/enabled.";
                            break;
                        default:
                            str3 = null;
                            break;
                    }
                    if (str6 != null && firebaseFirestoreException != null) {
                        switch (AnonymousClass1.$SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[firebaseFirestoreException.getCode().ordinal()]) {
                            case 1:
                                str4 = ERROR_ABORTED;
                                str5 = "aborted";
                                break;
                            case 2:
                                str4 = ERROR_ALREADY_EXISTS;
                                str5 = "already-exists";
                                break;
                            case 3:
                                str4 = ERROR_CANCELLED;
                                str5 = AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED;
                                break;
                            case 4:
                                str4 = ERROR_DATA_LOSS;
                                str5 = "data-loss";
                                break;
                            case 5:
                                str4 = ERROR_DEADLINE_EXCEEDED;
                                str5 = "deadline-exceeded";
                                break;
                            case 6:
                                str4 = ((firebaseFirestoreException.getMessage() == null || !firebaseFirestoreException.getMessage().contains("query requires an index")) && !firebaseFirestoreException.getMessage().contains("ensure it has been indexed")) ? ERROR_FAILED_PRECONDITION : firebaseFirestoreException.getMessage();
                                str5 = "failed-precondition";
                                break;
                            case 7:
                                str4 = ERROR_INTERNAL;
                                str5 = "internal";
                                break;
                            case 8:
                                str4 = ERROR_INVALID_ARGUMENT;
                                str5 = "invalid-argument";
                                break;
                            case 9:
                                str4 = ERROR_NOT_FOUND;
                                str5 = "not-found";
                                break;
                            case 10:
                                str4 = str;
                                str5 = str2;
                                break;
                            case 11:
                                str5 = "permission-denied";
                                str4 = ERROR_PERMISSION_DENIED;
                                break;
                            case 12:
                                str5 = "resource-exhausted";
                                str4 = ERROR_RESOURCE_EXHAUSTED;
                                break;
                            case 13:
                                str5 = "unauthenticated";
                                str4 = ERROR_UNAUTHENTICATED;
                                break;
                            case 14:
                                str5 = "unavailable";
                                str4 = ERROR_UNAVAILABLE;
                                break;
                            case 15:
                                str5 = "unimplemented";
                                str4 = "Operation is not implemented or not supported/enabled.";
                                break;
                            case 16:
                                str4 = "Unknown error or an error from a different error domain.";
                                str5 = "unknown";
                                break;
                            default:
                                str4 = FlutterFirebaseDatabaseException.UNKNOWN_ERROR_MESSAGE;
                                str5 = "unknown";
                                break;
                        }
                    } else {
                        str4 = str3;
                        str5 = str6;
                    }
                    this.code = str5;
                    this.message = str4;
                }
            }
        }
        str2 = "out-of-range";
        str3 = null;
        if (str6 != null) {
        }
        str4 = str3;
        str5 = str6;
        this.code = str5;
        this.message = str4;
    }

    /* renamed from: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestoreException$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code;

        static {
            int[] iArr = new int[FirebaseFirestoreException.Code.values().length];
            $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code = iArr;
            try {
                iArr[FirebaseFirestoreException.Code.ABORTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.ALREADY_EXISTS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.CANCELLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.DATA_LOSS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.DEADLINE_EXCEEDED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.FAILED_PRECONDITION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.INTERNAL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.INVALID_ARGUMENT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.NOT_FOUND.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.OUT_OF_RANGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.PERMISSION_DENIED.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNAUTHENTICATED.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNAVAILABLE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNIMPLEMENTED.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNKNOWN.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    public String getCode() {
        return this.code;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
