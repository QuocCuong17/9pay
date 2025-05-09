package co.hyperverge.hyperkyc.utils.extensions;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.Log;
import android.webkit.MimeTypeMap;
import co.hyperverge.hyperkyc.webCore.ui.WebCoreVM;
import co.hyperverge.hyperlogger.HyperLogger;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;

/* compiled from: FileExts.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u0004\u0018\u00010\u0003H\u0000\u001a\u0013\u0010\u0004\u001a\u00020\u0005*\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\u0010\u0007\u001a\u001c\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0000\u001a\u0015\u0010\u000e\u001a\u00020\u0005*\u00020\u0005H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\u0005H\u0000\u001a\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\u0016\u0010\u0017\u001a\u00020\u0006*\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\u0015\u0010\u0018\u001a\u0004\u0018\u00010\u0013*\u0004\u0018\u00010\u0003H\u0000¢\u0006\u0002\u0010\u0019\u001a\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\n2\u0006\u0010\u001b\u001a\u00020\u001cH\u0000\u001a\u000e\u0010\u001d\u001a\u0004\u0018\u00010\u0005*\u00020\u0003H\u0000\u001a&\u0010\u001e\u001a\u0004\u0018\u00010\u0005*\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005H\u0000\u001a&\u0010\"\u001a\u0004\u0018\u00010\u0005*\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005H\u0000\u001a\u000e\u0010#\u001a\u0004\u0018\u00010\u0005*\u00020\u0005H\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"asBitmaps", "", "Landroid/graphics/Bitmap;", "Ljava/io/File;", "asSizeLabel", "", "", "(Ljava/lang/Long;)Ljava/lang/String;", "copyFileTo", "", "Landroid/net/Uri;", "contentResolver", "Landroid/content/ContentResolver;", "destFile", "encodeFileToBase64", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateCustomUri", "getBitmapFromPDF", "pageNum", "", "getFileExtension", "getFileMimeType", "getFileName", "getFileSizeKB", "getPDFPageCount", "(Ljava/io/File;)Ljava/lang/Integer;", "getPathFromUri", "context", "Landroid/content/Context;", "mimeType", "saveToCache", "dirName", "filename", "data", "saveToInternalStorage", "toMimeType", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FileExtsKt {
    private static final String asSizeLabel$format(int i, Long l, String str) {
        DecimalFormat decimalFormat = new DecimalFormat(str);
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        Intrinsics.checkNotNull(l);
        return decimalFormat.format(Float.valueOf(((float) l.longValue()) / i));
    }

    public static final /* synthetic */ String getFileExtension(Uri uri, ContentResolver contentResolver) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        String fileMimeType = getFileMimeType(uri, contentResolver);
        if (fileMimeType != null) {
            return MimeTypeMap.getSingleton().getExtensionFromMimeType(fileMimeType);
        }
        return null;
    }

    public static final /* synthetic */ String getFileMimeType(Uri uri, ContentResolver contentResolver) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        if (uri != null) {
            return contentResolver.getType(uri);
        }
        return null;
    }

    public static final /* synthetic */ String mimeType(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(FilesKt.getExtension(file));
    }

    public static final /* synthetic */ String toMimeType(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
    }

    public static final /* synthetic */ String generateCustomUri(String str) {
        String str2;
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        Class<?> cls2;
        String className;
        String substringAfterLast$default;
        String className2;
        String str3 = null;
        if (str != null) {
            str2 = WebCoreVM.CUSTOM_SCHEME + new File(str).getName();
        } else {
            str2 = null;
        }
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str4 = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (str == null || (cls = str.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str5 = "getFileUri() called with: filePath = " + str + ", returning: " + str2;
        if (str5 == null) {
            str5 = "null ";
        }
        sb.append(str5);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        if (str != null && (cls2 = str.getClass()) != null) {
                            str3 = cls2.getCanonicalName();
                        }
                        if (str3 != null) {
                            str4 = str3;
                        }
                    } else {
                        str4 = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                    if (matcher2.find()) {
                        str4 = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                    }
                    if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str4 = str4.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str6 = "getFileUri() called with: filePath = " + str + ", returning: " + str2;
                    if (str6 == null) {
                        str6 = "null ";
                    }
                    sb2.append(str6);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str4, sb2.toString());
                }
            }
        }
        return str2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0261, code lost:
    
        if (r13 != null) goto L103;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0403  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7, types: [T] */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r13v10, types: [T] */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r1v11, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v40, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v12, types: [T] */
    /* JADX WARN: Type inference failed for: r3v22, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v24, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r9v10, types: [T] */
    /* JADX WARN: Type inference failed for: r9v23, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v25, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object encodeFileToBase64(String str, Continuation<? super String> continuation) {
        FileExtsKt$encodeFileToBase64$1 fileExtsKt$encodeFileToBase64$1;
        int i;
        ?? canonicalName;
        Class<?> cls;
        String str2;
        Object m1202constructorimpl;
        Object obj;
        Object obj2;
        ?? canonicalName2;
        Class<?> cls2;
        String str3;
        String className;
        File file;
        FileExtsKt$encodeFileToBase64$1 fileExtsKt$encodeFileToBase64$12;
        File file2;
        String str4;
        ?? r13;
        String str5;
        Object m1202constructorimpl2;
        ?? r11;
        Class<?> cls3;
        String str6;
        String className2;
        String substringAfterLast$default;
        File file3;
        Class<?> cls4;
        String className3;
        String className4;
        String mimeTypeFromExtension;
        if (continuation instanceof FileExtsKt$encodeFileToBase64$1) {
            fileExtsKt$encodeFileToBase64$1 = (FileExtsKt$encodeFileToBase64$1) continuation;
            if ((fileExtsKt$encodeFileToBase64$1.label & Integer.MIN_VALUE) != 0) {
                fileExtsKt$encodeFileToBase64$1.label -= Integer.MIN_VALUE;
                Object obj3 = fileExtsKt$encodeFileToBase64$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = fileExtsKt$encodeFileToBase64$1.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj3);
                    HyperLogger.Level level = HyperLogger.Level.DEBUG;
                    HyperLogger companion = HyperLogger.INSTANCE.getInstance();
                    StringBuilder sb = new StringBuilder();
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
                    if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                        canonicalName = (str == null || (cls = str.getClass()) == null) ? 0 : cls.getCanonicalName();
                        if (canonicalName == 0) {
                            canonicalName = "N/A";
                        }
                    }
                    objectRef.element = canonicalName;
                    Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef.element);
                    if (matcher.find()) {
                        ?? replaceAll = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(replaceAll, "replaceAll(\"\")");
                        objectRef.element = replaceAll;
                    }
                    if (((String) objectRef.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                        str2 = (String) objectRef.element;
                    } else {
                        str2 = ((String) objectRef.element).substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    sb.append(str2);
                    sb.append(" - ");
                    String str7 = "encodeFileToBase64() called with: filePath = " + str;
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb.append(str7);
                    sb.append(' ');
                    sb.append("");
                    companion.log(level, sb.toString());
                    if (!CoreExtsKt.isRelease()) {
                        try {
                            Result.Companion companion2 = Result.INSTANCE;
                            Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                        } catch (Throwable th) {
                            Result.Companion companion3 = Result.INSTANCE;
                            m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                            m1202constructorimpl = "";
                        }
                        String packageName = (String) m1202constructorimpl;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                            obj = coroutine_suspended;
                            obj2 = "N/A";
                            if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                                StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                                if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == 0) {
                                    canonicalName2 = (str == null || (cls2 = str.getClass()) == null) ? 0 : cls2.getCanonicalName();
                                    if (canonicalName2 == 0) {
                                        canonicalName2 = obj2;
                                    }
                                }
                                objectRef2.element = canonicalName2;
                                Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef2.element);
                                if (matcher2.find()) {
                                    ?? replaceAll2 = matcher2.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(replaceAll2, "replaceAll(\"\")");
                                    objectRef2.element = replaceAll2;
                                }
                                if (((String) objectRef2.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                    str3 = (String) objectRef2.element;
                                } else {
                                    str3 = ((String) objectRef2.element).substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb2 = new StringBuilder();
                                String str8 = "encodeFileToBase64() called with: filePath = " + str;
                                if (str8 == null) {
                                    str8 = "null ";
                                }
                                sb2.append(str8);
                                sb2.append(' ');
                                sb2.append("");
                                Log.println(3, str3, sb2.toString());
                            }
                            file = new File(str);
                            if (file.exists() || !file.isFile()) {
                                return str;
                            }
                            HyperLogger.Level level2 = HyperLogger.Level.DEBUG;
                            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                            StringBuilder sb3 = new StringBuilder();
                            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                            if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null) {
                                fileExtsKt$encodeFileToBase64$12 = fileExtsKt$encodeFileToBase64$1;
                                file2 = file;
                                str4 = "Throwable().stackTrace";
                            } else {
                                fileExtsKt$encodeFileToBase64$12 = fileExtsKt$encodeFileToBase64$1;
                                file2 = file;
                                str4 = "Throwable().stackTrace";
                                String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                r13 = substringAfterLast$default2;
                            }
                            String canonicalName3 = (str == null || (cls4 = str.getClass()) == null) ? null : cls4.getCanonicalName();
                            r13 = canonicalName3 == null ? obj2 : canonicalName3;
                            objectRef3.element = r13;
                            Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef3.element);
                            if (matcher3.find()) {
                                ?? replaceAll3 = matcher3.replaceAll("");
                                Intrinsics.checkNotNullExpressionValue(replaceAll3, "replaceAll(\"\")");
                                objectRef3.element = replaceAll3;
                            }
                            if (((String) objectRef3.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                str5 = (String) objectRef3.element;
                            } else {
                                str5 = ((String) objectRef3.element).substring(0, 23);
                                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
                            }
                            sb3.append(str5);
                            sb3.append(" - ");
                            sb3.append("encodeFileToBase64() file exists");
                            sb3.append(' ');
                            sb3.append("");
                            companion4.log(level2, sb3.toString());
                            if (!CoreExtsKt.isRelease()) {
                                try {
                                    Result.Companion companion5 = Result.INSTANCE;
                                    Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                                    Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                                    m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                                } catch (Throwable th2) {
                                    Result.Companion companion6 = Result.INSTANCE;
                                    m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                                }
                                if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                                    m1202constructorimpl2 = "";
                                }
                                String packageName2 = (String) m1202constructorimpl2;
                                if (CoreExtsKt.isDebug()) {
                                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                                        Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                        StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                        Intrinsics.checkNotNullExpressionValue(stackTrace4, str4);
                                        StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                        if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                            String canonicalName4 = (str == null || (cls3 = str.getClass()) == null) ? null : cls3.getCanonicalName();
                                            r11 = canonicalName4 == null ? obj2 : canonicalName4;
                                        } else {
                                            r11 = substringAfterLast$default;
                                        }
                                        objectRef4.element = r11;
                                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher((CharSequence) objectRef4.element);
                                        if (matcher4.find()) {
                                            ?? replaceAll4 = matcher4.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(replaceAll4, "replaceAll(\"\")");
                                            objectRef4.element = replaceAll4;
                                        }
                                        if (((String) objectRef4.element).length() <= 23 || Build.VERSION.SDK_INT >= 26) {
                                            str6 = (String) objectRef4.element;
                                        } else {
                                            str6 = ((String) objectRef4.element).substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        Log.println(3, str6, "encodeFileToBase64() file exists ");
                                    }
                                }
                            }
                            CoroutineDispatcher io2 = Dispatchers.getIO();
                            File file4 = file2;
                            FileExtsKt$encodeFileToBase64$fileBytes$1 fileExtsKt$encodeFileToBase64$fileBytes$1 = new FileExtsKt$encodeFileToBase64$fileBytes$1(file4, null);
                            FileExtsKt$encodeFileToBase64$1 fileExtsKt$encodeFileToBase64$13 = fileExtsKt$encodeFileToBase64$12;
                            fileExtsKt$encodeFileToBase64$13.L$0 = file4;
                            fileExtsKt$encodeFileToBase64$13.label = 1;
                            obj3 = BuildersKt.withContext(io2, fileExtsKt$encodeFileToBase64$fileBytes$1, fileExtsKt$encodeFileToBase64$13);
                            Object obj4 = obj;
                            if (obj3 == obj4) {
                                return obj4;
                            }
                            file3 = file4;
                        }
                    }
                    obj = coroutine_suspended;
                    obj2 = "N/A";
                    file = new File(str);
                    if (file.exists()) {
                    }
                    return str;
                }
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                file3 = (File) fileExtsKt$encodeFileToBase64$1.L$0;
                ResultKt.throwOnFailure(obj3);
                String encodeToString = Base64.encodeToString((byte[]) obj3, 2);
                mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(FilesKt.getExtension(file3));
                if (mimeTypeFromExtension == null) {
                    mimeTypeFromExtension = "application/octet-stream";
                }
                return "data:" + mimeTypeFromExtension + ";df:" + FilesKt.getNameWithoutExtension(file3) + ";base64," + encodeToString;
            }
        }
        fileExtsKt$encodeFileToBase64$1 = new FileExtsKt$encodeFileToBase64$1(continuation);
        Object obj32 = fileExtsKt$encodeFileToBase64$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = fileExtsKt$encodeFileToBase64$1.label;
        if (i != 0) {
        }
        String encodeToString2 = Base64.encodeToString((byte[]) obj32, 2);
        mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(FilesKt.getExtension(file3));
        if (mimeTypeFromExtension == null) {
        }
        return "data:" + mimeTypeFromExtension + ";df:" + FilesKt.getNameWithoutExtension(file3) + ";base64," + encodeToString2;
    }

    public static final /* synthetic */ String asSizeLabel(Long l) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        Class<?> cls2;
        String className;
        String substringAfterLast$default;
        String className2;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        String str2 = null;
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (l == null || (cls = l.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str3 = l + ".asSizeLabel() called";
        if (str3 == null) {
            str3 = "null ";
        }
        sb.append(str3);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        if (l != null && (cls2 = l.getClass()) != null) {
                            str2 = cls2.getCanonicalName();
                        }
                        if (str2 != null) {
                            str = str2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = l + ".asSizeLabel() called";
                    if (str4 == null) {
                        str4 = "null ";
                    }
                    sb2.append(str4);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        int pow = (int) Math.pow(1000.0d, 2.0d);
        int pow2 = (int) Math.pow(1000.0d, 2.0d);
        int pow3 = (int) Math.pow(1000.0d, 3.0d);
        if (l == null) {
            return "";
        }
        if (RangesKt.intRangeContains((ClosedRange<Integer>) RangesKt.until(0, 100), l.longValue())) {
            return l + " KB";
        }
        if (RangesKt.intRangeContains((ClosedRange<Integer>) RangesKt.until(100, pow2), l.longValue())) {
            String asSizeLabel$format = asSizeLabel$format(1000, l, "0.## MB");
            Intrinsics.checkNotNullExpressionValue(asSizeLabel$format, "kbs.format(\"0.## MB\")");
            return asSizeLabel$format;
        }
        if (RangesKt.intRangeContains((ClosedRange<Integer>) RangesKt.until(pow2, pow3), l.longValue())) {
            String asSizeLabel$format2 = asSizeLabel$format(pow, l, "0.### GB");
            Intrinsics.checkNotNullExpressionValue(asSizeLabel$format2, "mbs.format(\"0.### GB\")");
            return asSizeLabel$format2;
        }
        return l + " KB";
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x0149, code lost:
    
        if (r0 == null) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ String getPathFromUri(Uri uri, Context context) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(context, "context");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (uri == null || (cls = uri.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = uri + ".getPathFromUri() called with: context = " + context;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName2 = (uri == null || (cls2 = uri.getClass()) == null) ? null : cls2.getCanonicalName();
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = uri + ".getPathFromUri() called with: context = " + context;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        Cursor query = uri != null ? context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null) : null;
        if (query == null) {
            return null;
        }
        int columnIndexOrThrow = query.getColumnIndexOrThrow("_data");
        query.moveToFirst();
        String string = query.getString(columnIndexOrThrow);
        query.close();
        return string;
    }

    /* JADX WARN: Code restructure failed: missing block: B:97:0x0149, code lost:
    
        if (r0 == null) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ String getFileName(Uri uri, ContentResolver contentResolver) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String scheme;
        String path;
        Cursor query;
        int columnIndex;
        String className2;
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (uri == null || (cls = uri.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = uri + ".getFileName() called with: contentResolver = " + contentResolver;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName2 = (uri == null || (cls2 = uri.getClass()) == null) ? null : cls2.getCanonicalName();
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = uri + ".getFileName() called with: contentResolver = " + contentResolver;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        if (uri == null || (scheme = uri.getScheme()) == null) {
            return null;
        }
        int hashCode = scheme.hashCode();
        if (hashCode == 3143036) {
            if (scheme.equals("file") && (path = uri.getPath()) != null) {
                return StringsKt.substringAfterLast$default(path, RemoteSettings.FORWARD_SLASH_STRING, (String) null, 2, (Object) null);
            }
            return null;
        }
        if (hashCode != 951530617 || !scheme.equals(FirebaseAnalytics.Param.CONTENT) || (query = contentResolver.query(uri, null, null, null, null)) == null) {
            return null;
        }
        Cursor cursor = query;
        try {
            Cursor cursor2 = cursor;
            if (cursor2.moveToFirst() && (columnIndex = cursor2.getColumnIndex("_display_name")) != -1) {
                String string = cursor2.getString(columnIndex);
                CloseableKt.closeFinally(cursor, null);
                return string;
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursor, null);
            return null;
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(cursor, th2);
                throw th3;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:0x0149, code lost:
    
        if (r0 == null) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ long getFileSizeKB(Uri uri, ContentResolver contentResolver) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String className2;
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (uri == null || (cls = uri.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = uri + ".getFileSizeKB() called with: contentResolver = " + contentResolver;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName2 = (uri == null || (cls2 = uri.getClass()) == null) ? null : cls2.getCanonicalName();
                    }
                    str = canonicalName2;
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = uri + ".getFileSizeKB() called with: contentResolver = " + contentResolver;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        if (uri == null) {
            return 0L;
        }
        AssetFileDescriptor openAssetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, PDPageLabelRange.STYLE_ROMAN_LOWER);
        try {
            AssetFileDescriptor assetFileDescriptor = openAssetFileDescriptor;
            long length = assetFileDescriptor != null ? assetFileDescriptor.getLength() / 1000 : 0L;
            CloseableKt.closeFinally(openAssetFileDescriptor, null);
            return length;
        } finally {
        }
    }

    public static final /* synthetic */ void copyFileTo(Uri uri, ContentResolver contentResolver, File destFile) {
        String canonicalName;
        Object m1202constructorimpl;
        String className;
        String substringAfterLast$default;
        String className2;
        Intrinsics.checkNotNullParameter(uri, "<this>");
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(destFile, "destFile");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        String str = "N/A";
        if (stackTraceElement == null || (className2 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = uri.getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        if (matcher.find()) {
            canonicalName = matcher.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str2 = uri + ".copyFileTo() called with: contentResolver = " + contentResolver + ", destFile = " + destFile;
        if (str2 == null) {
            str2 = "null ";
        }
        sb.append(str2);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        Class<?> cls2 = uri.getClass();
                        String canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                        if (canonicalName2 != null) {
                            str = canonicalName2;
                        }
                    } else {
                        str = substringAfterLast$default;
                    }
                    Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str);
                    if (matcher2.find()) {
                        str = matcher2.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str, "replaceAll(\"\")");
                    }
                    if (str.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str = str.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = uri + ".copyFileTo() called with: contentResolver = " + contentResolver + ", destFile = " + destFile;
                    if (str3 == null) {
                        str3 = "null ";
                    }
                    sb2.append(str3);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, str, sb2.toString());
                }
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        FileOutputStream openInputStream = contentResolver.openInputStream(uri);
        try {
            InputStream inputStream = openInputStream;
            openInputStream = fileOutputStream;
            try {
                Intrinsics.checkNotNull(inputStream);
                ByteStreamsKt.copyTo$default(inputStream, openInputStream, 0, 2, null);
                CloseableKt.closeFinally(openInputStream, null);
                CloseableKt.closeFinally(openInputStream, null);
            } finally {
            }
        } finally {
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(21:1|(2:(1:227)(1:224)|(1:226))|7|(1:9)|10|(1:14)|15|(1:17)|18|(6:184|185|186|(1:188)|189|(11:191|(9:193|(2:(1:217)(1:214)|(1:216))|199|(1:201)|202|(1:206)|207|(1:209)|210)|21|22|23|(4:25|26|27|28)|176|177|178|98|99))|20|21|22|23|(0)|176|177|178|98|99|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x040d, code lost:
    
        if (r8 != null) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x01f3, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x01f4, code lost:
    
        r20 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x03d5, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x03d6, code lost:
    
        r20 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x022c, code lost:
    
        if (r9 != null) goto L99;
     */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0531  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0569  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01bc A[Catch: Exception -> 0x01f3, OutOfMemoryError -> 0x03d5, TRY_LEAVE, TryCatch #7 {Exception -> 0x01f3, OutOfMemoryError -> 0x03d5, blocks: (B:23:0x01ac, B:25:0x01bc), top: B:22:0x01ac }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ List asBitmaps(File file) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String str2;
        ArrayList arrayList;
        String str3;
        String str4;
        String str5;
        Object m1202constructorimpl2;
        String str6;
        String str7;
        Class<?> cls3;
        Matcher matcher;
        String localizedMessage;
        String className2;
        Class<?> cls4;
        String className3;
        String str8;
        String str9;
        String str10;
        Object m1202constructorimpl3;
        String str11;
        Class<?> cls5;
        String className4;
        String substringAfterLast$default;
        Class<?> cls6;
        String className5;
        int pageCount;
        int i;
        String className6;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className6 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (file == null || (cls = file.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str12 = "";
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        Unit unit = Unit.INSTANCE;
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str13 = file + ".asBitmaps() called";
        if (str13 == null) {
            str13 = "null ";
        }
        sb.append(str13);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        String str14 = "co.hyperverge";
        if (!CoreExtsKt.isRelease()) {
            try {
                Result.Companion companion2 = Result.INSTANCE;
                Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                m1202constructorimpl = "";
            }
            String packageName = (String) m1202constructorimpl;
            if (CoreExtsKt.isDebug()) {
                Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                str = "N/A";
                if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                    StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                    if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                        canonicalName2 = (file == null || (cls2 = file.getClass()) == null) ? null : cls2.getCanonicalName();
                        if (canonicalName2 == null) {
                            canonicalName2 = str;
                        }
                    }
                    Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                    if (matcher3.find()) {
                        canonicalName2 = matcher3.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        canonicalName2 = canonicalName2.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb2 = new StringBuilder();
                    String str15 = file + ".asBitmaps() called";
                    if (str15 == null) {
                        str15 = "null ";
                    }
                    sb2.append(str15);
                    sb2.append(' ');
                    sb2.append("");
                    Log.println(3, canonicalName2, sb2.toString());
                }
                ArrayList arrayList2 = new ArrayList();
                PdfRenderer pdfRenderer = new PdfRenderer(ParcelFileDescriptor.open(file, 268435456));
                pageCount = pdfRenderer.getPageCount();
                i = 0;
                while (i < pageCount) {
                    PdfRenderer.Page openPage = pdfRenderer.openPage(i);
                    int i2 = pageCount;
                    str2 = str14;
                    try {
                        Bitmap createBitmap = Bitmap.createBitmap(openPage.getWidth(), openPage.getHeight(), Bitmap.Config.ARGB_8888);
                        openPage.render(createBitmap, null, null, 1);
                        arrayList2.add(createBitmap);
                        openPage.close();
                        i++;
                        pageCount = i2;
                        str14 = str2;
                    } catch (Exception e) {
                        e = e;
                        Exception exc = e;
                        HyperLogger.Level level2 = HyperLogger.Level.ERROR;
                        HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb3 = new StringBuilder();
                        StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
                        if (stackTraceElement3 == null || (className5 = stackTraceElement3.getClassName()) == null) {
                            str8 = "Throwable().stackTrace";
                            arrayList = arrayList2;
                        } else {
                            str8 = "Throwable().stackTrace";
                            arrayList = arrayList2;
                            str9 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        String canonicalName3 = (file == null || (cls6 = file.getClass()) == null) ? null : cls6.getCanonicalName();
                        str9 = canonicalName3 == null ? str : canonicalName3;
                        Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
                        if (matcher4.find()) {
                            str9 = matcher4.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str9, "replaceAll(\"\")");
                        }
                        Unit unit3 = Unit.INSTANCE;
                        if (str9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str9 = str9.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb3.append(str9);
                        sb3.append(" - ");
                        String str16 = "asBitmaps: failed with " + exc.getMessage();
                        if (str16 == null) {
                            str16 = "null ";
                        }
                        sb3.append(str16);
                        sb3.append(' ');
                        Exception exc2 = exc;
                        String localizedMessage2 = exc2.getLocalizedMessage();
                        if (localizedMessage2 != null) {
                            str10 = '\n' + localizedMessage2;
                        } else {
                            str10 = "";
                        }
                        sb3.append(str10);
                        companion4.log(level2, sb3.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
                        } catch (Throwable th2) {
                            Result.Companion companion6 = Result.INSTANCE;
                            m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                            m1202constructorimpl3 = "";
                        }
                        String packageName2 = (String) m1202constructorimpl3;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) str2, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace4, str8);
                                StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
                                if (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                                    String canonicalName4 = (file == null || (cls5 = file.getClass()) == null) ? null : cls5.getCanonicalName();
                                    str11 = canonicalName4 == null ? str : canonicalName4;
                                } else {
                                    str11 = substringAfterLast$default;
                                }
                                Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str11);
                                if (matcher5.find()) {
                                    str11 = matcher5.replaceAll("");
                                    Intrinsics.checkNotNullExpressionValue(str11, "replaceAll(\"\")");
                                }
                                Unit unit4 = Unit.INSTANCE;
                                if (str11.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                    str11 = str11.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str11, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb4 = new StringBuilder();
                                String str17 = "asBitmaps: failed with " + exc.getMessage();
                                if (str17 == null) {
                                    str17 = "null ";
                                }
                                sb4.append(str17);
                                sb4.append(' ');
                                String localizedMessage3 = exc2.getLocalizedMessage();
                                if (localizedMessage3 != null) {
                                    str12 = '\n' + localizedMessage3;
                                }
                                sb4.append(str12);
                                Log.println(6, str11, sb4.toString());
                            }
                        }
                        return arrayList;
                    } catch (OutOfMemoryError e2) {
                        e = e2;
                        arrayList = arrayList2;
                        HyperLogger.Level level3 = HyperLogger.Level.ERROR;
                        HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
                        StringBuilder sb5 = new StringBuilder();
                        StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
                        if (stackTraceElement5 == null || (className3 = stackTraceElement5.getClassName()) == null) {
                            str3 = "Throwable().stackTrace";
                        } else {
                            str3 = "Throwable().stackTrace";
                            str4 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                        }
                        String canonicalName5 = (file == null || (cls4 = file.getClass()) == null) ? null : cls4.getCanonicalName();
                        str4 = canonicalName5 == null ? str : canonicalName5;
                        Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
                        if (matcher6.find()) {
                            str4 = matcher6.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
                        }
                        Unit unit5 = Unit.INSTANCE;
                        if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            str4 = str4.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        sb5.append(str4);
                        sb5.append(" - ");
                        sb5.append("asBitmaps: failed with OOM");
                        sb5.append(' ');
                        OutOfMemoryError outOfMemoryError = e;
                        String localizedMessage4 = outOfMemoryError.getLocalizedMessage();
                        if (localizedMessage4 != null) {
                            str5 = '\n' + localizedMessage4;
                        } else {
                            str5 = "";
                        }
                        sb5.append(str5);
                        companion7.log(level3, sb5.toString());
                        CoreExtsKt.isRelease();
                        try {
                            Result.Companion companion8 = Result.INSTANCE;
                            Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                            Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                            m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
                        } catch (Throwable th3) {
                            Result.Companion companion9 = Result.INSTANCE;
                            m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
                        }
                        if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                            m1202constructorimpl2 = "";
                        }
                        String packageName3 = (String) m1202constructorimpl2;
                        if (CoreExtsKt.isDebug()) {
                            Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
                            if (StringsKt.contains$default((CharSequence) packageName3, (CharSequence) str2, false, 2, (Object) null)) {
                                StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
                                Intrinsics.checkNotNullExpressionValue(stackTrace6, str3);
                                StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
                                if (stackTraceElement6 == null || (className2 = stackTraceElement6.getClassName()) == null) {
                                    str6 = null;
                                } else {
                                    str6 = null;
                                    String substringAfterLast$default2 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
                                    if (substringAfterLast$default2 != null) {
                                        str7 = substringAfterLast$default2;
                                        matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                        if (matcher.find()) {
                                            str7 = matcher.replaceAll("");
                                            Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
                                        }
                                        Unit unit6 = Unit.INSTANCE;
                                        if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                                            str7 = str7.substring(0, 23);
                                            Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                        }
                                        StringBuilder sb6 = new StringBuilder();
                                        sb6.append("asBitmaps: failed with OOM");
                                        sb6.append(' ');
                                        localizedMessage = outOfMemoryError.getLocalizedMessage();
                                        if (localizedMessage != null) {
                                            str12 = '\n' + localizedMessage;
                                        }
                                        sb6.append(str12);
                                        Log.println(6, str7, sb6.toString());
                                    }
                                }
                                String canonicalName6 = (file == null || (cls3 = file.getClass()) == null) ? str6 : cls3.getCanonicalName();
                                str7 = canonicalName6 == null ? str : canonicalName6;
                                matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
                                if (matcher.find()) {
                                }
                                Unit unit62 = Unit.INSTANCE;
                                if (str7.length() > 23) {
                                    str7 = str7.substring(0, 23);
                                    Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
                                }
                                StringBuilder sb62 = new StringBuilder();
                                sb62.append("asBitmaps: failed with OOM");
                                sb62.append(' ');
                                localizedMessage = outOfMemoryError.getLocalizedMessage();
                                if (localizedMessage != null) {
                                }
                                sb62.append(str12);
                                Log.println(6, str7, sb62.toString());
                            }
                        }
                        return arrayList;
                    }
                }
                str2 = str14;
                pdfRenderer.close();
                arrayList = arrayList2;
                return arrayList;
            }
        }
        str = "N/A";
        ArrayList arrayList22 = new ArrayList();
        PdfRenderer pdfRenderer2 = new PdfRenderer(ParcelFileDescriptor.open(file, 268435456));
        pageCount = pdfRenderer2.getPageCount();
        i = 0;
        while (i < pageCount) {
        }
        str2 = str14;
        pdfRenderer2.close();
        arrayList = arrayList22;
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x03c7, code lost:
    
        if (r8 != null) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x01ed, code lost:
    
        if (r8 != null) goto L90;
     */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0373  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Integer getPDFPageCount(File file) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        String str;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String str2;
        String str3;
        String str4;
        Object m1202constructorimpl2;
        String str5;
        Class<?> cls3;
        String className2;
        String substringAfterLast$default;
        Class<?> cls4;
        String className3;
        CharSequence charSequence;
        String str6;
        String str7;
        String str8;
        Object m1202constructorimpl3;
        String canonicalName3;
        String str9;
        Class<?> cls5;
        Matcher matcher;
        String str10;
        String localizedMessage;
        String className4;
        Class<?> cls6;
        String className5;
        String className6;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className6 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (file == null || (cls = file.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str11 = "";
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str12 = file + ".getPDFPageCount() called";
        if (str12 == null) {
            str12 = "null ";
        }
        sb.append(str12);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        try {
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    str = "N/A";
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            canonicalName2 = (file == null || (cls2 = file.getClass()) == null) ? null : cls2.getCanonicalName();
                            if (canonicalName2 == null) {
                                canonicalName2 = str;
                            }
                        }
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher3.find()) {
                            canonicalName2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str13 = file + ".getPDFPageCount() called";
                        if (str13 == null) {
                            str13 = "null ";
                        }
                        sb2.append(str13);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    return Integer.valueOf(new PdfRenderer(ParcelFileDescriptor.open(file, 268435456)).getPageCount());
                }
            }
            return Integer.valueOf(new PdfRenderer(ParcelFileDescriptor.open(file, 268435456)).getPageCount());
        } catch (Exception e) {
            HyperLogger.Level level2 = HyperLogger.Level.ERROR;
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className5 = stackTraceElement3.getClassName()) == null) {
                charSequence = "co.hyperverge";
                str6 = "Throwable().stackTrace";
            } else {
                charSequence = "co.hyperverge";
                str6 = "Throwable().stackTrace";
                str7 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            String canonicalName4 = (file == null || (cls6 = file.getClass()) == null) ? null : cls6.getCanonicalName();
            str7 = canonicalName4 == null ? str : canonicalName4;
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
            if (matcher4.find()) {
                str7 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
            }
            if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str7 = str7.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb3.append(str7);
            sb3.append(" - ");
            String str14 = "getPDFPageCount: failed with " + e.getMessage();
            if (str14 == null) {
                str14 = "null ";
            }
            sb3.append(str14);
            sb3.append(' ');
            Exception exc = e;
            String localizedMessage2 = exc.getLocalizedMessage();
            if (localizedMessage2 != null) {
                str8 = '\n' + localizedMessage2;
            } else {
                str8 = "";
            }
            sb3.append(str8);
            companion4.log(level2, sb3.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion5 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion6 = Result.INSTANCE;
                m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                m1202constructorimpl3 = "";
            }
            String packageName2 = (String) m1202constructorimpl3;
            if (!CoreExtsKt.isDebug()) {
                return null;
            }
            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
            if (!StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                return null;
            }
            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace4, str6);
            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
            if (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                canonicalName3 = (file == null || (cls5 = file.getClass()) == null) ? null : cls5.getCanonicalName();
                if (canonicalName3 == null) {
                    str9 = str;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
                    if (matcher.find()) {
                        str9 = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str9, "replaceAll(\"\")");
                    }
                    if (str9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str9 = str9.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb4 = new StringBuilder();
                    str10 = "getPDFPageCount: failed with " + e.getMessage();
                    if (str10 == null) {
                        str10 = "null ";
                    }
                    sb4.append(str10);
                    sb4.append(' ');
                    localizedMessage = exc.getLocalizedMessage();
                    if (localizedMessage != null) {
                        str11 = '\n' + localizedMessage;
                    }
                    sb4.append(str11);
                    Log.println(6, str9, sb4.toString());
                    return null;
                }
            }
            str9 = canonicalName3;
            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
            if (matcher.find()) {
            }
            if (str9.length() > 23) {
                str9 = str9.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            StringBuilder sb42 = new StringBuilder();
            str10 = "getPDFPageCount: failed with " + e.getMessage();
            if (str10 == null) {
            }
            sb42.append(str10);
            sb42.append(' ');
            localizedMessage = exc.getLocalizedMessage();
            if (localizedMessage != null) {
            }
            sb42.append(str11);
            Log.println(6, str9, sb42.toString());
            return null;
        } catch (OutOfMemoryError e2) {
            HyperLogger.Level level3 = HyperLogger.Level.ERROR;
            HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb5 = new StringBuilder();
            StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
            StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
            if (stackTraceElement5 == null || (className3 = stackTraceElement5.getClassName()) == null) {
                str2 = "Throwable().stackTrace";
            } else {
                str2 = "Throwable().stackTrace";
                str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            String canonicalName5 = (file == null || (cls4 = file.getClass()) == null) ? null : cls4.getCanonicalName();
            str3 = canonicalName5 == null ? str : canonicalName5;
            Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
            if (matcher5.find()) {
                str3 = matcher5.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
            }
            if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str3 = str3.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb5.append(str3);
            sb5.append(" - ");
            sb5.append("getPDFPageCount: failed with OOM");
            sb5.append(' ');
            OutOfMemoryError outOfMemoryError = e2;
            String localizedMessage3 = outOfMemoryError.getLocalizedMessage();
            if (localizedMessage3 != null) {
                str4 = '\n' + localizedMessage3;
            } else {
                str4 = "";
            }
            sb5.append(str4);
            companion7.log(level3, sb5.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion8 = Result.INSTANCE;
                Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
            } catch (Throwable th3) {
                Result.Companion companion9 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String packageName3 = (String) m1202constructorimpl2;
            if (!CoreExtsKt.isDebug()) {
                return null;
            }
            Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
            if (!StringsKt.contains$default((CharSequence) packageName3, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                return null;
            }
            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace6, str2);
            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
            if (stackTraceElement6 == null || (className2 = stackTraceElement6.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                String canonicalName6 = (file == null || (cls3 = file.getClass()) == null) ? null : cls3.getCanonicalName();
                str5 = canonicalName6 == null ? str : canonicalName6;
            } else {
                str5 = substringAfterLast$default;
            }
            Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
            if (matcher6.find()) {
                str5 = matcher6.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
            }
            if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str5 = str5.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append("getPDFPageCount: failed with OOM");
            sb6.append(' ');
            String localizedMessage4 = outOfMemoryError.getLocalizedMessage();
            if (localizedMessage4 != null) {
                str11 = '\n' + localizedMessage4;
            }
            sb6.append(str11);
            Log.println(6, str5, sb6.toString());
            return null;
        }
        str = "N/A";
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x03f2, code lost:
    
        if (r9 != null) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0218, code lost:
    
        if (r15 != null) goto L90;
     */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x03a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ Bitmap getBitmapFromPDF(File file, int i) {
        String canonicalName;
        Class<?> cls;
        Object m1202constructorimpl;
        CharSequence charSequence;
        String str;
        String canonicalName2;
        Class<?> cls2;
        String className;
        String str2;
        String str3;
        String str4;
        Object m1202constructorimpl2;
        String str5;
        Class<?> cls3;
        String className2;
        String substringAfterLast$default;
        Class<?> cls4;
        String className3;
        String str6;
        String str7;
        String str8;
        Object m1202constructorimpl3;
        String canonicalName3;
        String str9;
        Class<?> cls5;
        Matcher matcher;
        String str10;
        String localizedMessage;
        String className4;
        Class<?> cls6;
        String className5;
        String className6;
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className6 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className6, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            canonicalName = (file == null || (cls = file.getClass()) == null) ? null : cls.getCanonicalName();
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str11 = "";
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        Unit unit = Unit.INSTANCE;
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str12 = file + ".getBitmapFromPDF() called with: pageNum = " + i;
        if (str12 == null) {
            str12 = "null ";
        }
        sb.append(str12);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        try {
            if (!CoreExtsKt.isRelease()) {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                    Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                    m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName = (String) m1202constructorimpl;
                if (CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    charSequence = "co.hyperverge";
                    str = "N/A";
                    if (StringsKt.contains$default((CharSequence) packageName, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            canonicalName2 = (file == null || (cls2 = file.getClass()) == null) ? null : cls2.getCanonicalName();
                            if (canonicalName2 == null) {
                                canonicalName2 = str;
                            }
                        }
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher3.find()) {
                            canonicalName2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        String str13 = file + ".getBitmapFromPDF() called with: pageNum = " + i;
                        if (str13 == null) {
                            str13 = "null ";
                        }
                        sb2.append(str13);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    }
                    PdfRenderer pdfRenderer = new PdfRenderer(ParcelFileDescriptor.open(file, 268435456));
                    pdfRenderer.getPageCount();
                    PdfRenderer.Page openPage = pdfRenderer.openPage(i);
                    Bitmap createBitmap = Bitmap.createBitmap(openPage.getWidth(), openPage.getHeight(), Bitmap.Config.ARGB_8888);
                    openPage.render(createBitmap, null, null, 1);
                    openPage.close();
                    pdfRenderer.close();
                    return createBitmap;
                }
            }
            PdfRenderer pdfRenderer2 = new PdfRenderer(ParcelFileDescriptor.open(file, 268435456));
            pdfRenderer2.getPageCount();
            PdfRenderer.Page openPage2 = pdfRenderer2.openPage(i);
            Bitmap createBitmap2 = Bitmap.createBitmap(openPage2.getWidth(), openPage2.getHeight(), Bitmap.Config.ARGB_8888);
            openPage2.render(createBitmap2, null, null, 1);
            openPage2.close();
            pdfRenderer2.close();
            return createBitmap2;
        } catch (Exception e) {
            HyperLogger.Level level2 = HyperLogger.Level.ERROR;
            HyperLogger companion4 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb3 = new StringBuilder();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className5 = stackTraceElement3.getClassName()) == null) {
                str6 = "Throwable().stackTrace";
            } else {
                str6 = "Throwable().stackTrace";
                str7 = StringsKt.substringAfterLast$default(className5, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            String canonicalName4 = (file == null || (cls6 = file.getClass()) == null) ? null : cls6.getCanonicalName();
            str7 = canonicalName4 == null ? str : canonicalName4;
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str7);
            if (matcher4.find()) {
                str7 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str7, "replaceAll(\"\")");
            }
            Unit unit3 = Unit.INSTANCE;
            if (str7.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str7 = str7.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str7, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb3.append(str7);
            sb3.append(" - ");
            String str14 = "getBitmapFromPDF: failed with " + e.getMessage();
            if (str14 == null) {
                str14 = "null ";
            }
            sb3.append(str14);
            sb3.append(' ');
            Exception exc = e;
            String localizedMessage2 = exc.getLocalizedMessage();
            if (localizedMessage2 != null) {
                str8 = '\n' + localizedMessage2;
            } else {
                str8 = "";
            }
            sb3.append(str8);
            companion4.log(level2, sb3.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion5 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl3 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
            } catch (Throwable th2) {
                Result.Companion companion6 = Result.INSTANCE;
                m1202constructorimpl3 = Result.m1202constructorimpl(ResultKt.createFailure(th2));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl3)) {
                m1202constructorimpl3 = "";
            }
            String packageName2 = (String) m1202constructorimpl3;
            if (!CoreExtsKt.isDebug()) {
                return null;
            }
            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
            if (!StringsKt.contains$default((CharSequence) packageName2, charSequence, false, 2, (Object) null)) {
                return null;
            }
            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace4, str6);
            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
            if (stackTraceElement4 == null || (className4 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                canonicalName3 = (file == null || (cls5 = file.getClass()) == null) ? null : cls5.getCanonicalName();
                if (canonicalName3 == null) {
                    str9 = str;
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
                    if (matcher.find()) {
                        str9 = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str9, "replaceAll(\"\")");
                    }
                    Unit unit4 = Unit.INSTANCE;
                    if (str9.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str9 = str9.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb4 = new StringBuilder();
                    str10 = "getBitmapFromPDF: failed with " + e.getMessage();
                    if (str10 == null) {
                        str10 = "null ";
                    }
                    sb4.append(str10);
                    sb4.append(' ');
                    localizedMessage = exc.getLocalizedMessage();
                    if (localizedMessage != null) {
                        str11 = '\n' + localizedMessage;
                    }
                    sb4.append(str11);
                    Log.println(6, str9, sb4.toString());
                    return null;
                }
            }
            str9 = canonicalName3;
            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str9);
            if (matcher.find()) {
            }
            Unit unit42 = Unit.INSTANCE;
            if (str9.length() > 23) {
                str9 = str9.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str9, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            StringBuilder sb42 = new StringBuilder();
            str10 = "getBitmapFromPDF: failed with " + e.getMessage();
            if (str10 == null) {
            }
            sb42.append(str10);
            sb42.append(' ');
            localizedMessage = exc.getLocalizedMessage();
            if (localizedMessage != null) {
            }
            sb42.append(str11);
            Log.println(6, str9, sb42.toString());
            return null;
        } catch (OutOfMemoryError e2) {
            HyperLogger.Level level3 = HyperLogger.Level.ERROR;
            HyperLogger companion7 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb5 = new StringBuilder();
            StackTraceElement[] stackTrace5 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace5, "Throwable().stackTrace");
            StackTraceElement stackTraceElement5 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace5);
            if (stackTraceElement5 == null || (className3 = stackTraceElement5.getClassName()) == null) {
                str2 = "Throwable().stackTrace";
            } else {
                str2 = "Throwable().stackTrace";
                str3 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null);
            }
            String canonicalName5 = (file == null || (cls4 = file.getClass()) == null) ? null : cls4.getCanonicalName();
            str3 = canonicalName5 == null ? str : canonicalName5;
            Matcher matcher5 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str3);
            if (matcher5.find()) {
                str3 = matcher5.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str3, "replaceAll(\"\")");
            }
            Unit unit5 = Unit.INSTANCE;
            if (str3.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str3 = str3.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb5.append(str3);
            sb5.append(" - ");
            sb5.append("getBitmapFromPDF: failed with OOM");
            sb5.append(' ');
            OutOfMemoryError outOfMemoryError = e2;
            String localizedMessage3 = outOfMemoryError.getLocalizedMessage();
            if (localizedMessage3 != null) {
                str4 = '\n' + localizedMessage3;
            } else {
                str4 = "";
            }
            sb5.append(str4);
            companion7.log(level3, sb5.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion8 = Result.INSTANCE;
                Object invoke3 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke3, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke3).getPackageName());
            } catch (Throwable th3) {
                Result.Companion companion9 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th3));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String packageName3 = (String) m1202constructorimpl2;
            if (!CoreExtsKt.isDebug()) {
                return null;
            }
            Intrinsics.checkNotNullExpressionValue(packageName3, "packageName");
            if (!StringsKt.contains$default((CharSequence) packageName3, charSequence, false, 2, (Object) null)) {
                return null;
            }
            StackTraceElement[] stackTrace6 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace6, str2);
            StackTraceElement stackTraceElement6 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace6);
            if (stackTraceElement6 == null || (className2 = stackTraceElement6.getClassName()) == null || (substringAfterLast$default = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                String canonicalName6 = (file == null || (cls3 = file.getClass()) == null) ? null : cls3.getCanonicalName();
                str5 = canonicalName6 == null ? str : canonicalName6;
            } else {
                str5 = substringAfterLast$default;
            }
            Matcher matcher6 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str5);
            if (matcher6.find()) {
                str5 = matcher6.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str5, "replaceAll(\"\")");
            }
            Unit unit6 = Unit.INSTANCE;
            if (str5.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str5 = str5.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str5, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append("getBitmapFromPDF: failed with OOM");
            sb6.append(' ');
            String localizedMessage4 = outOfMemoryError.getLocalizedMessage();
            if (localizedMessage4 != null) {
                str11 = '\n' + localizedMessage4;
            }
            sb6.append(str11);
            Log.println(6, str5, sb6.toString());
            return null;
        }
        charSequence = "co.hyperverge";
        str = "N/A";
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0209 A[Catch: all -> 0x0224, TryCatch #0 {all -> 0x0224, blocks: (B:23:0x01f8, B:25:0x0209, B:26:0x020c), top: B:22:0x01f8 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x03f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ String saveToCache(Context context, String dirName, String filename, String data) {
        String canonicalName;
        String str;
        Object m1202constructorimpl;
        String str2;
        CharSequence charSequence;
        String str3;
        String canonicalName2;
        String className;
        String str4;
        String str5;
        Object m1202constructorimpl2;
        String canonicalName3;
        String str6;
        Matcher matcher;
        String str7;
        String localizedMessage;
        String className2;
        String className3;
        File file;
        String className4;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(dirName, "dirName");
        Intrinsics.checkNotNullParameter(filename, "filename");
        Intrinsics.checkNotNullParameter(data, "data");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = context.getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str8 = "";
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str9 = context + ".saveToCache() called with: dirName = " + dirName + ", filename = " + filename + ", data = " + data;
        if (str9 == null) {
            str9 = "null ";
        }
        sb.append(str9);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        try {
            if (CoreExtsKt.isRelease()) {
                str2 = data;
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str = ", data = ";
                    try {
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String packageName = (String) m1202constructorimpl;
                        if (!CoreExtsKt.isDebug()) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str = ", data = ";
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (!CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    charSequence = "co.hyperverge";
                    str3 = "packageName";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = context.getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = "N/A";
                            }
                        }
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher3.find()) {
                            canonicalName2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(context);
                        sb3.append(".saveToCache() called with: dirName = ");
                        sb3.append(dirName);
                        sb3.append(", filename = ");
                        sb3.append(filename);
                        sb3.append(str);
                        str2 = data;
                        sb3.append(str2);
                        String sb4 = sb3.toString();
                        if (sb4 == null) {
                            sb4 = "null ";
                        }
                        sb2.append(sb4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(3, canonicalName2, sb2.toString());
                    } else {
                        str2 = data;
                    }
                    Result.Companion companion4 = Result.INSTANCE;
                    file = new File(context.getCacheDir(), dirName);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(file, filename);
                    byte[] bytes = str2.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    FilesKt.writeBytes(file2, bytes);
                    return file2.getPath();
                }
                str2 = data;
            }
            Result.Companion companion42 = Result.INSTANCE;
            file = new File(context.getCacheDir(), dirName);
            if (!file.exists()) {
            }
            File file22 = new File(file, filename);
            byte[] bytes2 = str2.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
            FilesKt.writeBytes(file22, bytes2);
            return file22.getPath();
        } catch (Throwable th3) {
            Result.Companion companion5 = Result.INSTANCE;
            Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(Result.m1202constructorimpl(ResultKt.createFailure(th3)));
            if (m1205exceptionOrNullimpl == null) {
                return null;
            }
            HyperLogger.Level level2 = HyperLogger.Level.ERROR;
            HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb5 = new StringBuilder();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str4 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls3 = context.getClass();
                String canonicalName4 = cls3 != null ? cls3.getCanonicalName() : null;
                str4 = canonicalName4 == null ? "N/A" : canonicalName4;
            }
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
            if (matcher4.find()) {
                str4 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
            }
            if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str4 = str4.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb5.append(str4);
            sb5.append(" - ");
            String str10 = "saveToCache failed for " + dirName + '/' + filename;
            if (str10 == null) {
                str10 = "null ";
            }
            sb5.append(str10);
            sb5.append(' ');
            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
            if (localizedMessage2 != null) {
                str5 = '\n' + localizedMessage2;
            } else {
                str5 = "";
            }
            sb5.append(str5);
            companion6.log(level2, sb5.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion7 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
            } catch (Throwable th4) {
                Result.Companion companion8 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String str11 = (String) m1202constructorimpl2;
            if (!CoreExtsKt.isDebug()) {
                return null;
            }
            Intrinsics.checkNotNullExpressionValue(str11, str3);
            if (!StringsKt.contains$default((CharSequence) str11, charSequence, false, 2, (Object) null)) {
                return null;
            }
            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls4 = context.getClass();
                canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                if (canonicalName3 == null) {
                    str6 = "N/A";
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                    if (matcher.find()) {
                        str6 = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                    }
                    if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str6 = str6.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb6 = new StringBuilder();
                    str7 = "saveToCache failed for " + dirName + '/' + filename;
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb6.append(str7);
                    sb6.append(' ');
                    localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage != null) {
                        str8 = '\n' + localizedMessage;
                    }
                    sb6.append(str8);
                    Log.println(6, str6, sb6.toString());
                    return null;
                }
            }
            str6 = canonicalName3;
            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
            if (matcher.find()) {
            }
            if (str6.length() > 23) {
                str6 = str6.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            StringBuilder sb62 = new StringBuilder();
            str7 = "saveToCache failed for " + dirName + '/' + filename;
            if (str7 == null) {
            }
            sb62.append(str7);
            sb62.append(' ');
            if (m1205exceptionOrNullimpl == null) {
            }
            if (localizedMessage != null) {
            }
            sb62.append(str8);
            Log.println(6, str6, sb62.toString());
            return null;
        }
        charSequence = "co.hyperverge";
        str3 = "packageName";
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0209 A[Catch: all -> 0x0224, TryCatch #0 {all -> 0x0224, blocks: (B:23:0x01f8, B:25:0x0209, B:26:0x020c), top: B:22:0x01f8 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x03f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final /* synthetic */ String saveToInternalStorage(Context context, String dirName, String filename, String data) {
        String canonicalName;
        String str;
        Object m1202constructorimpl;
        String str2;
        CharSequence charSequence;
        String str3;
        String canonicalName2;
        String className;
        String str4;
        String str5;
        Object m1202constructorimpl2;
        String canonicalName3;
        String str6;
        Matcher matcher;
        String str7;
        String localizedMessage;
        String className2;
        String className3;
        File file;
        String className4;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(dirName, "dirName");
        Intrinsics.checkNotNullParameter(filename, "filename");
        Intrinsics.checkNotNullParameter(data, "data");
        HyperLogger.Level level = HyperLogger.Level.DEBUG;
        HyperLogger companion = HyperLogger.INSTANCE.getInstance();
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "Throwable().stackTrace");
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.firstOrNull(stackTrace);
        if (stackTraceElement == null || (className4 = stackTraceElement.getClassName()) == null || (canonicalName = StringsKt.substringAfterLast$default(className4, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
            Class<?> cls = context.getClass();
            canonicalName = cls != null ? cls.getCanonicalName() : null;
            if (canonicalName == null) {
                canonicalName = "N/A";
            }
        }
        Matcher matcher2 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName);
        String str8 = "";
        if (matcher2.find()) {
            canonicalName = matcher2.replaceAll("");
            Intrinsics.checkNotNullExpressionValue(canonicalName, "replaceAll(\"\")");
        }
        if (canonicalName.length() > 23 && Build.VERSION.SDK_INT < 26) {
            canonicalName = canonicalName.substring(0, 23);
            Intrinsics.checkNotNullExpressionValue(canonicalName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        sb.append(canonicalName);
        sb.append(" - ");
        String str9 = context + ".saveToInternalStorage() called with: dirName = " + dirName + ", filename = " + filename + ", data = " + data;
        if (str9 == null) {
            str9 = "null ";
        }
        sb.append(str9);
        sb.append(' ');
        sb.append("");
        companion.log(level, sb.toString());
        try {
            if (CoreExtsKt.isRelease()) {
                str2 = data;
            } else {
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    str = ", data = ";
                    try {
                        Object invoke = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type android.app.Application");
                        m1202constructorimpl = Result.m1202constructorimpl(((Application) invoke).getPackageName());
                    } catch (Throwable th) {
                        th = th;
                        Result.Companion companion3 = Result.INSTANCE;
                        m1202constructorimpl = Result.m1202constructorimpl(ResultKt.createFailure(th));
                        if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                        }
                        String packageName = (String) m1202constructorimpl;
                        if (!CoreExtsKt.isDebug()) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    str = ", data = ";
                }
                if (Result.m1208isFailureimpl(m1202constructorimpl)) {
                    m1202constructorimpl = "";
                }
                String packageName2 = (String) m1202constructorimpl;
                if (!CoreExtsKt.isDebug()) {
                    Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
                    charSequence = "co.hyperverge";
                    str3 = "packageName";
                    if (StringsKt.contains$default((CharSequence) packageName2, (CharSequence) "co.hyperverge", false, 2, (Object) null)) {
                        StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                        Intrinsics.checkNotNullExpressionValue(stackTrace2, "Throwable().stackTrace");
                        StackTraceElement stackTraceElement2 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace2);
                        if (stackTraceElement2 == null || (className = stackTraceElement2.getClassName()) == null || (canonicalName2 = StringsKt.substringAfterLast$default(className, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                            Class<?> cls2 = context.getClass();
                            canonicalName2 = cls2 != null ? cls2.getCanonicalName() : null;
                            if (canonicalName2 == null) {
                                canonicalName2 = "N/A";
                            }
                        }
                        Matcher matcher3 = LogExtsKt.ANON_CLASS_PATTERN.matcher(canonicalName2);
                        if (matcher3.find()) {
                            canonicalName2 = matcher3.replaceAll("");
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "replaceAll(\"\")");
                        }
                        if (canonicalName2.length() > 23 && Build.VERSION.SDK_INT < 26) {
                            canonicalName2 = canonicalName2.substring(0, 23);
                            Intrinsics.checkNotNullExpressionValue(canonicalName2, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        StringBuilder sb2 = new StringBuilder();
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(context);
                        sb3.append(".saveToInternalStorage() called with: dirName = ");
                        sb3.append(dirName);
                        sb3.append(", filename = ");
                        sb3.append(filename);
                        sb3.append(str);
                        str2 = data;
                        sb3.append(str2);
                        String sb4 = sb3.toString();
                        if (sb4 == null) {
                            sb4 = "null ";
                        }
                        sb2.append(sb4);
                        sb2.append(' ');
                        sb2.append("");
                        Log.println(2, canonicalName2, sb2.toString());
                    } else {
                        str2 = data;
                    }
                    Result.Companion companion4 = Result.INSTANCE;
                    file = new File(context.getFilesDir(), dirName);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(file, filename);
                    byte[] bytes = str2.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    FilesKt.writeBytes(file2, bytes);
                    return file2.getPath();
                }
                str2 = data;
            }
            Result.Companion companion42 = Result.INSTANCE;
            file = new File(context.getFilesDir(), dirName);
            if (!file.exists()) {
            }
            File file22 = new File(file, filename);
            byte[] bytes2 = str2.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
            FilesKt.writeBytes(file22, bytes2);
            return file22.getPath();
        } catch (Throwable th3) {
            Result.Companion companion5 = Result.INSTANCE;
            Throwable m1205exceptionOrNullimpl = Result.m1205exceptionOrNullimpl(Result.m1202constructorimpl(ResultKt.createFailure(th3)));
            if (m1205exceptionOrNullimpl == null) {
                return null;
            }
            HyperLogger.Level level2 = HyperLogger.Level.ERROR;
            HyperLogger companion6 = HyperLogger.INSTANCE.getInstance();
            StringBuilder sb5 = new StringBuilder();
            StackTraceElement[] stackTrace3 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace3, "Throwable().stackTrace");
            StackTraceElement stackTraceElement3 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace3);
            if (stackTraceElement3 == null || (className3 = stackTraceElement3.getClassName()) == null || (str4 = StringsKt.substringAfterLast$default(className3, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls3 = context.getClass();
                String canonicalName4 = cls3 != null ? cls3.getCanonicalName() : null;
                str4 = canonicalName4 == null ? "N/A" : canonicalName4;
            }
            Matcher matcher4 = LogExtsKt.ANON_CLASS_PATTERN.matcher(str4);
            if (matcher4.find()) {
                str4 = matcher4.replaceAll("");
                Intrinsics.checkNotNullExpressionValue(str4, "replaceAll(\"\")");
            }
            if (str4.length() > 23 && Build.VERSION.SDK_INT < 26) {
                str4 = str4.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str4, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            sb5.append(str4);
            sb5.append(" - ");
            String str10 = "saveToInternalStorage failed for " + dirName + '/' + filename;
            if (str10 == null) {
                str10 = "null ";
            }
            sb5.append(str10);
            sb5.append(' ');
            String localizedMessage2 = m1205exceptionOrNullimpl != null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
            if (localizedMessage2 != null) {
                str5 = '\n' + localizedMessage2;
            } else {
                str5 = "";
            }
            sb5.append(str5);
            companion6.log(level2, sb5.toString());
            CoreExtsKt.isRelease();
            try {
                Result.Companion companion7 = Result.INSTANCE;
                Object invoke2 = Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, new Object[0]);
                Intrinsics.checkNotNull(invoke2, "null cannot be cast to non-null type android.app.Application");
                m1202constructorimpl2 = Result.m1202constructorimpl(((Application) invoke2).getPackageName());
            } catch (Throwable th4) {
                Result.Companion companion8 = Result.INSTANCE;
                m1202constructorimpl2 = Result.m1202constructorimpl(ResultKt.createFailure(th4));
            }
            if (Result.m1208isFailureimpl(m1202constructorimpl2)) {
                m1202constructorimpl2 = "";
            }
            String str11 = (String) m1202constructorimpl2;
            if (!CoreExtsKt.isDebug()) {
                return null;
            }
            Intrinsics.checkNotNullExpressionValue(str11, str3);
            if (!StringsKt.contains$default((CharSequence) str11, charSequence, false, 2, (Object) null)) {
                return null;
            }
            StackTraceElement[] stackTrace4 = new Throwable().getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace4, "Throwable().stackTrace");
            StackTraceElement stackTraceElement4 = (StackTraceElement) ArraysKt.firstOrNull(stackTrace4);
            if (stackTraceElement4 == null || (className2 = stackTraceElement4.getClassName()) == null || (canonicalName3 = StringsKt.substringAfterLast$default(className2, FilenameUtils.EXTENSION_SEPARATOR, (String) null, 2, (Object) null)) == null) {
                Class<?> cls4 = context.getClass();
                canonicalName3 = cls4 != null ? cls4.getCanonicalName() : null;
                if (canonicalName3 == null) {
                    str6 = "N/A";
                    matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
                    if (matcher.find()) {
                        str6 = matcher.replaceAll("");
                        Intrinsics.checkNotNullExpressionValue(str6, "replaceAll(\"\")");
                    }
                    if (str6.length() > 23 && Build.VERSION.SDK_INT < 26) {
                        str6 = str6.substring(0, 23);
                        Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
                    }
                    StringBuilder sb6 = new StringBuilder();
                    str7 = "saveToInternalStorage failed for " + dirName + '/' + filename;
                    if (str7 == null) {
                        str7 = "null ";
                    }
                    sb6.append(str7);
                    sb6.append(' ');
                    localizedMessage = m1205exceptionOrNullimpl == null ? m1205exceptionOrNullimpl.getLocalizedMessage() : null;
                    if (localizedMessage != null) {
                        str8 = '\n' + localizedMessage;
                    }
                    sb6.append(str8);
                    Log.println(6, str6, sb6.toString());
                    return null;
                }
            }
            str6 = canonicalName3;
            matcher = LogExtsKt.ANON_CLASS_PATTERN.matcher(str6);
            if (matcher.find()) {
            }
            if (str6.length() > 23) {
                str6 = str6.substring(0, 23);
                Intrinsics.checkNotNullExpressionValue(str6, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            StringBuilder sb62 = new StringBuilder();
            str7 = "saveToInternalStorage failed for " + dirName + '/' + filename;
            if (str7 == null) {
            }
            sb62.append(str7);
            sb62.append(' ');
            if (m1205exceptionOrNullimpl == null) {
            }
            if (localizedMessage != null) {
            }
            sb62.append(str8);
            Log.println(6, str6, sb62.toString());
            return null;
        }
        charSequence = "co.hyperverge";
        str3 = "packageName";
    }
}
