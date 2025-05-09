package dev.fluttercommunity.plus.share;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import androidx.core.content.FileProvider;
import androidx.webkit.ProxyConfig;
import androidx.webkit.internal.AssetHelper;
import com.facebook.gamingservices.cloudgaming.internal.SDKConstants;
import com.google.firebase.sessions.settings.RemoteSettings;
import com.pichillilorenzo.flutter_inappwebview_android.chrome_custom_tabs.ActionBroadcastReceiver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.pdfbox.pdmodel.interactive.form.PDButton;

/* compiled from: Share.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0015H\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001b\u001a\u00020\u0015H\u0002J\b\u0010\u001e\u001a\u00020\u0003H\u0002J\u0012\u0010\u001f\u001a\u00020\u00102\b\u0010 \u001a\u0004\u0018\u00010\u0010H\u0002J&\u0010!\u001a\u0012\u0012\u0004\u0012\u00020#0\"j\b\u0012\u0004\u0012\u00020#`$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00100&H\u0002J\u0018\u0010'\u001a\u00020\u00102\u000e\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010&H\u0002J\u0010\u0010)\u001a\u00020\u00192\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J \u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\u00102\b\u0010,\u001a\u0004\u0018\u00010\u00102\u0006\u0010-\u001a\u00020\u001dJ@\u0010.\u001a\u00020\u00192\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00100&2\u000e\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010&2\b\u0010+\u001a\u0004\u0018\u00010\u00102\b\u0010,\u001a\u0004\u0018\u00010\u00102\u0006\u0010-\u001a\u00020\u001dJ\u0018\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u0002012\u0006\u0010-\u001a\u00020\u001dH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u00062"}, d2 = {"Ldev/fluttercommunity/plus/share/Share;", "", "context", "Landroid/content/Context;", "activity", "Landroid/app/Activity;", "manager", "Ldev/fluttercommunity/plus/share/ShareSuccessManager;", "(Landroid/content/Context;Landroid/app/Activity;Ldev/fluttercommunity/plus/share/ShareSuccessManager;)V", "immutabilityIntentFlags", "", "getImmutabilityIntentFlags", "()I", "immutabilityIntentFlags$delegate", "Lkotlin/Lazy;", "providerAuthority", "", "getProviderAuthority", "()Ljava/lang/String;", "providerAuthority$delegate", "shareCacheFolder", "Ljava/io/File;", "getShareCacheFolder", "()Ljava/io/File;", "clearShareCacheFolder", "", "copyToShareCacheFolder", "file", "fileIsInShareCache", "", "getContext", "getMimeTypeBase", "mimeType", "getUrisForPaths", "Ljava/util/ArrayList;", "Landroid/net/Uri;", "Lkotlin/collections/ArrayList;", "paths", "", "reduceMimeTypes", "mimeTypes", "setActivity", "share", "text", "subject", "withResult", "shareFiles", "startActivity", SDKConstants.PARAM_INTENT, "Landroid/content/Intent;", "share_plus_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Share {
    private Activity activity;
    private final Context context;

    /* renamed from: immutabilityIntentFlags$delegate, reason: from kotlin metadata */
    private final Lazy immutabilityIntentFlags;
    private final ShareSuccessManager manager;

    /* renamed from: providerAuthority$delegate, reason: from kotlin metadata */
    private final Lazy providerAuthority;

    public Share(Context context, Activity activity, ShareSuccessManager manager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(manager, "manager");
        this.context = context;
        this.activity = activity;
        this.manager = manager;
        this.providerAuthority = LazyKt.lazy(new Function0<String>() { // from class: dev.fluttercommunity.plus.share.Share$providerAuthority$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Context context2;
                StringBuilder sb = new StringBuilder();
                context2 = Share.this.getContext();
                sb.append(context2.getPackageName());
                sb.append(".flutter.share_provider");
                return sb.toString();
            }
        });
        this.immutabilityIntentFlags = LazyKt.lazy(new Function0<Integer>() { // from class: dev.fluttercommunity.plus.share.Share$immutabilityIntentFlags$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(Build.VERSION.SDK_INT >= 23 ? PDButton.FLAG_RADIOS_IN_UNISON : 0);
            }
        });
    }

    private final String getProviderAuthority() {
        return (String) this.providerAuthority.getValue();
    }

    private final File getShareCacheFolder() {
        return new File(getContext().getCacheDir(), "share_plus");
    }

    private final int getImmutabilityIntentFlags() {
        return ((Number) this.immutabilityIntentFlags.getValue()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        Activity activity = this.activity;
        if (activity != null) {
            Intrinsics.checkNotNull(activity);
            return activity;
        }
        return this.context;
    }

    public final void setActivity(Activity activity) {
        this.activity = activity;
    }

    public final void share(String text, String subject, boolean withResult) {
        Intent chooserIntent;
        Intrinsics.checkNotNullParameter(text, "text");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType(AssetHelper.DEFAULT_MIME_TYPE);
        intent.putExtra("android.intent.extra.TEXT", text);
        intent.putExtra(ActionBroadcastReceiver.KEY_URL_TITLE, subject);
        if (withResult) {
            chooserIntent = Intent.createChooser(intent, null, PendingIntent.getBroadcast(this.context, 0, new Intent(this.context, (Class<?>) SharePlusPendingIntent.class), 134217728 | getImmutabilityIntentFlags()).getIntentSender());
        } else {
            chooserIntent = Intent.createChooser(intent, null);
        }
        Intrinsics.checkNotNullExpressionValue(chooserIntent, "chooserIntent");
        startActivity(chooserIntent, withResult);
    }

    public final void shareFiles(List<String> paths, List<String> mimeTypes, String text, String subject, boolean withResult) throws IOException {
        Intent chooserIntent;
        Intrinsics.checkNotNullParameter(paths, "paths");
        clearShareCacheFolder();
        ArrayList<Uri> urisForPaths = getUrisForPaths(paths);
        Intent intent = new Intent();
        if (urisForPaths.isEmpty()) {
            String str = text;
            if (!(str == null || StringsKt.isBlank(str))) {
                share(text, subject, withResult);
                return;
            }
        }
        if (urisForPaths.size() == 1) {
            List<String> list = mimeTypes;
            String str2 = !(list == null || list.isEmpty()) ? (String) CollectionsKt.first((List) mimeTypes) : "*/*";
            intent.setAction("android.intent.action.SEND");
            intent.setType(str2);
            intent.putExtra("android.intent.extra.STREAM", (Parcelable) CollectionsKt.first((List) urisForPaths));
        } else {
            intent.setAction("android.intent.action.SEND_MULTIPLE");
            intent.setType(reduceMimeTypes(mimeTypes));
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", urisForPaths);
        }
        if (text != null) {
            intent.putExtra("android.intent.extra.TEXT", text);
        }
        if (subject != null) {
            intent.putExtra(ActionBroadcastReceiver.KEY_URL_TITLE, subject);
        }
        intent.addFlags(1);
        if (withResult) {
            chooserIntent = Intent.createChooser(intent, null, PendingIntent.getBroadcast(this.context, 0, new Intent(this.context, (Class<?>) SharePlusPendingIntent.class), 134217728 | getImmutabilityIntentFlags()).getIntentSender());
        } else {
            chooserIntent = Intent.createChooser(intent, null);
        }
        List<ResolveInfo> queryIntentActivities = getContext().getPackageManager().queryIntentActivities(chooserIntent, 65536);
        Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "getContext().packageMana…CH_DEFAULT_ONLY\n        )");
        Iterator<T> it = queryIntentActivities.iterator();
        while (it.hasNext()) {
            String str3 = ((ResolveInfo) it.next()).activityInfo.packageName;
            Iterator<T> it2 = urisForPaths.iterator();
            while (it2.hasNext()) {
                getContext().grantUriPermission(str3, (Uri) it2.next(), 3);
            }
        }
        Intrinsics.checkNotNullExpressionValue(chooserIntent, "chooserIntent");
        startActivity(chooserIntent, withResult);
    }

    private final void startActivity(Intent intent, boolean withResult) {
        Activity activity = this.activity;
        if (activity == null) {
            intent.addFlags(268435456);
            if (withResult) {
                this.manager.unavailable();
            }
            this.context.startActivity(intent);
            return;
        }
        if (withResult) {
            Intrinsics.checkNotNull(activity);
            activity.startActivityForResult(intent, ShareSuccessManager.ACTIVITY_CODE);
        } else {
            Intrinsics.checkNotNull(activity);
            activity.startActivity(intent);
        }
    }

    private final ArrayList<Uri> getUrisForPaths(List<String> paths) throws IOException {
        ArrayList<Uri> arrayList = new ArrayList<>(paths.size());
        Iterator<T> it = paths.iterator();
        while (it.hasNext()) {
            File file = new File((String) it.next());
            if (fileIsInShareCache(file)) {
                throw new IOException("Shared file can not be located in '" + getShareCacheFolder().getCanonicalPath() + '\'');
            }
            arrayList.add(FileProvider.getUriForFile(getContext(), getProviderAuthority(), copyToShareCacheFolder(file)));
        }
        return arrayList;
    }

    private final String reduceMimeTypes(List<String> mimeTypes) {
        int i = 1;
        if (mimeTypes != null ? mimeTypes.isEmpty() : true) {
            return "*/*";
        }
        Intrinsics.checkNotNull(mimeTypes);
        if (mimeTypes.size() == 1) {
            return (String) CollectionsKt.first((List) mimeTypes);
        }
        String str = (String) CollectionsKt.first((List) mimeTypes);
        int lastIndex = CollectionsKt.getLastIndex(mimeTypes);
        if (1 <= lastIndex) {
            while (true) {
                if (!Intrinsics.areEqual(str, mimeTypes.get(i))) {
                    if (!Intrinsics.areEqual(getMimeTypeBase(str), getMimeTypeBase(mimeTypes.get(i)))) {
                        return "*/*";
                    }
                    str = getMimeTypeBase(mimeTypes.get(i)) + "/*";
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return str;
    }

    private final String getMimeTypeBase(String mimeType) {
        if (mimeType != null) {
            String str = mimeType;
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) RemoteSettings.FORWARD_SLASH_STRING, false, 2, (Object) null)) {
                String substring = mimeType.substring(0, StringsKt.indexOf$default((CharSequence) str, RemoteSettings.FORWARD_SLASH_STRING, 0, false, 6, (Object) null));
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                return substring;
            }
        }
        return ProxyConfig.MATCH_ALL_SCHEMES;
    }

    private final boolean fileIsInShareCache(File file) {
        try {
            String filePath = file.getCanonicalPath();
            Intrinsics.checkNotNullExpressionValue(filePath, "filePath");
            String canonicalPath = getShareCacheFolder().getCanonicalPath();
            Intrinsics.checkNotNullExpressionValue(canonicalPath, "shareCacheFolder.canonicalPath");
            return StringsKt.startsWith$default(filePath, canonicalPath, false, 2, (Object) null);
        } catch (IOException unused) {
            return false;
        }
    }

    private final void clearShareCacheFolder() {
        File shareCacheFolder = getShareCacheFolder();
        File[] files = shareCacheFolder.listFiles();
        if (shareCacheFolder.exists()) {
            boolean z = true;
            if (files != null) {
                if (!(files.length == 0)) {
                    z = false;
                }
            }
            if (z) {
                return;
            }
            Intrinsics.checkNotNullExpressionValue(files, "files");
            for (File file : files) {
                file.delete();
            }
            shareCacheFolder.delete();
        }
    }

    private final File copyToShareCacheFolder(File file) throws IOException {
        File shareCacheFolder = getShareCacheFolder();
        if (!shareCacheFolder.exists()) {
            shareCacheFolder.mkdirs();
        }
        File file2 = new File(shareCacheFolder, file.getName());
        FilesKt.copyTo$default(file, file2, true, 0, 4, null);
        return file2;
    }
}
