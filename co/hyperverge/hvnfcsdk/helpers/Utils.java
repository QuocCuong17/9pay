package co.hyperverge.hvnfcsdk.helpers;

import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes2.dex */
class Utils {
    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getErrorMessage(Throwable th) {
        LogUtils.d("Utils", "getErrorMessage() called with: exception = [" + th + "]");
        if (th == null) {
            return "Exception is null";
        }
        String message = th.getMessage();
        return (message == null || message.isEmpty()) ? "Exception has no message" : message;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String convertDate(String str) {
        LogUtils.d("Utils", "convertDate() called with: input = [" + str + "]");
        if (str == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyMMdd", Locale.US).format(new SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(str));
        } catch (Exception e) {
            LogUtils.e("Utils", "Error occurred with message [" + getErrorMessage(e) + " ]");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String trimDocumentID(String str) {
        LogUtils.d("Utils", "trimDocumentID() called with: input = [" + str + "]");
        if (str == null) {
            return null;
        }
        return str.length() > 9 ? str.substring(str.length() - 9) : str;
    }
}
