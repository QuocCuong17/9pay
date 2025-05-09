package androidx.media3.exoplayer.rtsp;

import android.net.Uri;
import androidx.media3.common.C;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.firebase.sessions.settings.RemoteSettings;
import io.sentry.Session;

/* loaded from: classes.dex */
final class RtspTrackTiming {
    public final long rtpTimestamp;
    public final int sequenceNumber;
    public final Uri uri;

    /* JADX WARN: Removed duplicated region for block: B:16:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0084 A[Catch: Exception -> 0x0092, TRY_LEAVE, TryCatch #0 {Exception -> 0x0092, blocks: (B:7:0x0027, B:19:0x0072, B:24:0x0077, B:25:0x007c, B:28:0x007d, B:29:0x0084, B:31:0x004b, B:34:0x0055, B:37:0x0060), top: B:6:0x0027 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ImmutableList<RtspTrackTiming> parseTrackTiming(String str, Uri uri) throws ParserException {
        char c;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        String[] split = Util.split(str, ",");
        int length = split.length;
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            String str2 = split[i2];
            String[] split2 = Util.split(str2, ";");
            int length2 = split2.length;
            int i3 = i;
            long j = C.TIME_UNSET;
            Uri uri2 = null;
            int i4 = -1;
            while (i3 < length2) {
                String str3 = split2[i3];
                try {
                    String[] splitAtFirst = Util.splitAtFirst(str3, "=");
                    String str4 = splitAtFirst[i];
                    String str5 = splitAtFirst[1];
                    int hashCode = str4.hashCode();
                    String[] strArr = split;
                    int i5 = length;
                    if (hashCode == 113759) {
                        if (str4.equals(Session.JsonKeys.SEQ)) {
                            c = 1;
                            if (c != 0) {
                            }
                            i3++;
                            split = strArr;
                            length = i5;
                            i = 0;
                        }
                        c = 65535;
                        if (c != 0) {
                        }
                        i3++;
                        split = strArr;
                        length = i5;
                        i = 0;
                    } else if (hashCode != 116079) {
                        if (hashCode == 1524180539 && str4.equals("rtptime")) {
                            c = 2;
                            if (c != 0) {
                                uri2 = resolveUri(str5, uri);
                            } else if (c == 1) {
                                i4 = Integer.parseInt(str5);
                            } else if (c == 2) {
                                j = Long.parseLong(str5);
                            } else {
                                throw ParserException.createForMalformedManifest(str4, null);
                            }
                            i3++;
                            split = strArr;
                            length = i5;
                            i = 0;
                        }
                        c = 65535;
                        if (c != 0) {
                        }
                        i3++;
                        split = strArr;
                        length = i5;
                        i = 0;
                    } else {
                        if (str4.equals("url")) {
                            c = 0;
                            if (c != 0) {
                            }
                            i3++;
                            split = strArr;
                            length = i5;
                            i = 0;
                        }
                        c = 65535;
                        if (c != 0) {
                        }
                        i3++;
                        split = strArr;
                        length = i5;
                        i = 0;
                    }
                } catch (Exception e) {
                    throw ParserException.createForMalformedManifest(str3, e);
                }
                throw ParserException.createForMalformedManifest(str3, e);
            }
            String[] strArr2 = split;
            int i6 = length;
            if (uri2 == null || uri2.getScheme() == null || (i4 == -1 && j == C.TIME_UNSET)) {
                throw ParserException.createForMalformedManifest(str2, null);
            }
            builder.add((ImmutableList.Builder) new RtspTrackTiming(j, i4, uri2));
            i2++;
            split = strArr2;
            length = i6;
            i = 0;
        }
        return builder.build();
    }

    static Uri resolveUri(String str, Uri uri) {
        Assertions.checkArgument(((String) Assertions.checkNotNull(uri.getScheme())).equals("rtsp"));
        Uri parse = Uri.parse(str);
        if (parse.isAbsolute()) {
            return parse;
        }
        Uri parse2 = Uri.parse("rtsp://" + str);
        String uri2 = uri.toString();
        if (((String) Assertions.checkNotNull(parse2.getHost())).equals(uri.getHost())) {
            return parse2;
        }
        if (uri2.endsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
            return UriUtil.resolveToUri(uri2, str);
        }
        return UriUtil.resolveToUri(uri2 + RemoteSettings.FORWARD_SLASH_STRING, str);
    }

    private RtspTrackTiming(long j, int i, Uri uri) {
        this.rtpTimestamp = j;
        this.sequenceNumber = i;
        this.uri = uri;
    }
}
