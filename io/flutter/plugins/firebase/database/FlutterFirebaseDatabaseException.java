package io.flutter.plugins.firebase.database;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class FlutterFirebaseDatabaseException extends Exception {
    private static final String MODULE = "firebase_database";
    public static final String UNKNOWN_ERROR_CODE = "unknown";
    public static final String UNKNOWN_ERROR_MESSAGE = "An unknown error occurred";
    private final Map<String, Object> additionalData;
    private final String code;
    private final String message;

    public FlutterFirebaseDatabaseException(String str, String str2, Map<String, Object> map) {
        this.code = str;
        this.message = str2;
        if (map != null) {
            this.additionalData = map;
        } else {
            this.additionalData = new HashMap();
        }
        this.additionalData.put("code", str);
        this.additionalData.put("message", str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FlutterFirebaseDatabaseException fromDatabaseError(DatabaseError databaseError) {
        String str;
        String str2;
        int code = databaseError.getCode();
        if (code == -25) {
            str = "write-cancelled";
            str2 = "The write was canceled by the user.";
        } else if (code == -24) {
            str = "network-error";
            str2 = "The operation could not be performed due to a network error.";
        } else if (code == -4) {
            str = "disconnected";
            str2 = "The operation had to be aborted due to a network disconnect.";
        } else if (code == -3) {
            str = "permission-denied";
            str2 = "Client doesn't have permission to access the desired data.";
        } else if (code == -2) {
            str = "failure";
            str2 = "The server indicated that this operation failed.";
        } else if (code != -1) {
            switch (code) {
                case -10:
                    str = "unavailable";
                    str2 = "The service is unavailable.";
                    break;
                case -9:
                    str = "overridden-by-set";
                    str2 = "The transaction was overridden by a subsequent set.";
                    break;
                case DatabaseError.MAX_RETRIES /* -8 */:
                    str = "max-retries";
                    str2 = "The transaction had too many retries.";
                    break;
                case -7:
                    str = "invalid-token";
                    str2 = "The supplied auth token was invalid.";
                    break;
                case -6:
                    str = "expired-token";
                    str2 = "The supplied auth token has expired.";
                    break;
                default:
                    str2 = UNKNOWN_ERROR_MESSAGE;
                    str = "unknown";
                    break;
            }
        } else {
            str = "data-stale";
            str2 = "The transaction needs to be run again with current data.";
        }
        if (str.equals("unknown")) {
            return unknown(databaseError.getMessage());
        }
        HashMap hashMap = new HashMap();
        hashMap.put("details", databaseError.getDetails());
        return new FlutterFirebaseDatabaseException(str, str2, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FlutterFirebaseDatabaseException fromDatabaseException(DatabaseException databaseException) {
        return fromDatabaseError(DatabaseError.fromException(databaseException));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FlutterFirebaseDatabaseException fromException(Exception exc) {
        if (exc == null) {
            return unknown();
        }
        return unknown(exc.getMessage());
    }

    static FlutterFirebaseDatabaseException unknown() {
        return unknown(null);
    }

    static FlutterFirebaseDatabaseException unknown(String str) {
        String str2;
        HashMap hashMap = new HashMap();
        if (str == null) {
            str = UNKNOWN_ERROR_MESSAGE;
        }
        if (str.contains("Index not defined, add \".indexOn\"")) {
            str = str.replaceFirst("java.lang.Exception: ", "");
            str2 = "index-not-defined";
        } else if (str.contains("Permission denied")) {
            str2 = "permission-denied";
            str = "Client doesn't have permission to access the desired data.";
        } else {
            str2 = "unknown";
        }
        return new FlutterFirebaseDatabaseException(str2, str, hashMap);
    }

    public String getCode() {
        return this.code;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public Map<String, Object> getAdditionalData() {
        return this.additionalData;
    }
}
