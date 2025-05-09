package co.hyperverge.hyperkyc.utils.extensions;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: URLExts.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0016\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"removePathSlashPrefix", "", "Ljava/net/URL;", "urlDecode", "charset", "Ljava/nio/charset/Charset;", "hyperkyc_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class URLExtsKt {
    public static final /* synthetic */ String removePathSlashPrefix(URL url) {
        Intrinsics.checkNotNullParameter(url, "<this>");
        String path = url.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "this.path");
        return StringsKt.removePrefix(path, (CharSequence) RemoteSettings.FORWARD_SLASH_STRING);
    }

    public static /* synthetic */ String urlDecode$default(String str, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return urlDecode(str, charset);
    }

    public static final /* synthetic */ String urlDecode(String str, Charset charset) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String decode = URLDecoder.decode(str, charset.toString());
        Intrinsics.checkNotNullExpressionValue(decode, "decode(this, charset.toString())");
        return decode;
    }
}
