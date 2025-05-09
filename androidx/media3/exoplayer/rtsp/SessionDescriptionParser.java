package androidx.media3.exoplayer.rtsp;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.exoplayer.rtsp.MediaDescription;
import androidx.media3.exoplayer.rtsp.SessionDescription;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
final class SessionDescriptionParser {
    private static final String ATTRIBUTE_TYPE = "a";
    private static final String BANDWIDTH_TYPE = "b";
    private static final String CONNECTION_TYPE = "c";
    private static final String EMAIL_TYPE = "e";
    private static final String INFORMATION_TYPE = "i";
    private static final String KEY_TYPE = "k";
    private static final String MEDIA_TYPE = "m";
    private static final String ORIGIN_TYPE = "o";
    private static final String PHONE_NUMBER_TYPE = "p";
    private static final String REPEAT_TYPE = "r";
    private static final String SESSION_TYPE = "s";
    private static final String TAG = "SDPParser";
    private static final String TIMING_TYPE = "t";
    private static final String URI_TYPE = "u";
    private static final String VERSION_TYPE = "v";
    private static final String ZONE_TYPE = "z";
    private static final Pattern SDP_LINE_PATTERN = Pattern.compile("([a-z])=\\s?(.+)");
    private static final Pattern SDP_LINE_WITH_EMPTY_VALUE_PATTERN = Pattern.compile("^([a-z])=$");
    private static final Pattern ATTRIBUTE_PATTERN = Pattern.compile("([\\x21\\x23-\\x27\\x2a\\x2b\\x2d\\x2e\\x30-\\x39\\x41-\\x5a\\x5e-\\x7e]+)(?::(.*))?");
    private static final Pattern MEDIA_DESCRIPTION_PATTERN = Pattern.compile("(\\S+)\\s(\\S+)\\s(\\S+)\\s(\\S+)");

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x019a, code lost:
    
        androidx.media3.common.util.Assertions.checkArgument(r8);
        r7 = java.lang.Integer.parseInt(r7[1]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01a3, code lost:
    
        if (r5 != null) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01a5, code lost:
    
        r0.setBitrate(r7 * 1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01ab, code lost:
    
        r5.setBitrate(r7 * 1000);
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0199, code lost:
    
        r8 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01b1, code lost:
    
        if (r6 == false) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01b4, code lost:
    
        if (r5 != null) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01b6, code lost:
    
        r0.setConnection(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x01ba, code lost:
    
        r5.setConnection(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01be, code lost:
    
        r0.setPhoneNumber(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x01c2, code lost:
    
        r0.setEmailAddress(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x01c6, code lost:
    
        r0.setUri(android.net.Uri.parse(r8));
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01ce, code lost:
    
        if (r6 == false) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x01d1, code lost:
    
        if (r5 != null) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x01d3, code lost:
    
        r0.setSessionInfo(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x01d7, code lost:
    
        r5.setMediaTitle(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x01db, code lost:
    
        r0.setSessionName(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x01df, code lost:
    
        r0.setOrigin(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x01e9, code lost:
    
        if ("0".equals(r8) == false) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01fa, code lost:
    
        throw androidx.media3.common.ParserException.createForMalformedManifest(java.lang.String.format("SDP version %s is not supported.", r8), null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x01fb, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x011b, code lost:
    
        switch(r13) {
            case 0: goto L113;
            case 1: goto L112;
            case 2: goto L111;
            case 3: goto L106;
            case 4: goto L105;
            case 5: goto L104;
            case 6: goto L103;
            case 7: goto L98;
            case 8: goto L88;
            case 9: goto L87;
            case 10: goto L82;
            case 11: goto L72;
            case 12: goto L66;
            default: goto L155;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0120, code lost:
    
        if (r5 == null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0122, code lost:
    
        addMediaDescriptionToSession(r0, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0125, code lost:
    
        r5 = parseMediaDescriptionLine(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0129, code lost:
    
        if (r5 != null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x012b, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x012e, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0131, code lost:
    
        if (r6 == false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0135, code lost:
    
        r8 = androidx.media3.exoplayer.rtsp.SessionDescriptionParser.ATTRIBUTE_PATTERN.matcher(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x013f, code lost:
    
        if (r8.matches() == false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0141, code lost:
    
        r7 = (java.lang.String) androidx.media3.common.util.Assertions.checkNotNull(r8.group(1));
        r8 = com.google.common.base.Strings.nullToEmpty(r8.group(2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0153, code lost:
    
        if (r5 != null) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0155, code lost:
    
        r0.addAttribute(r7, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x015a, code lost:
    
        r5.addAttribute(r7, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0174, code lost:
    
        throw androidx.media3.common.ParserException.createForMalformedManifest("Malformed Attribute line: " + r7, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01fb, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0175, code lost:
    
        if (r6 == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0179, code lost:
    
        if (r5 != null) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x017b, code lost:
    
        r0.setKey(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0180, code lost:
    
        r5.setKey(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0185, code lost:
    
        r0.setTiming(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x018a, code lost:
    
        if (r6 == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x018e, code lost:
    
        r7 = androidx.media3.common.util.Util.split(r8, ":\\s?");
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0195, code lost:
    
        if (r7.length != 2) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0197, code lost:
    
        r8 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static SessionDescription parse(String str) throws ParserException {
        SessionDescription.Builder builder = new SessionDescription.Builder();
        String[] splitRtspMessageBody = RtspMessageUtil.splitRtspMessageBody(str);
        int length = splitRtspMessageBody.length;
        MediaDescription.Builder builder2 = null;
        boolean z = false;
        for (int i = 0; i < length; i++) {
            String str2 = splitRtspMessageBody[i];
            if (!"".equals(str2)) {
                Matcher matcher = SDP_LINE_PATTERN.matcher(str2);
                if (!matcher.matches()) {
                    Matcher matcher2 = SDP_LINE_WITH_EMPTY_VALUE_PATTERN.matcher(str2);
                    if (!matcher2.matches() || !Objects.equals(matcher2.group(1), "i")) {
                        throw ParserException.createForMalformedManifest("Malformed SDP line: " + str2, null);
                    }
                } else {
                    String str3 = (String) Assertions.checkNotNull(matcher.group(1));
                    String str4 = (String) Assertions.checkNotNull(matcher.group(2));
                    char c = 65535;
                    switch (str3.hashCode()) {
                        case 97:
                            if (str3.equals("a")) {
                                c = 11;
                                break;
                            }
                            break;
                        case 98:
                            if (str3.equals(BANDWIDTH_TYPE)) {
                                c = '\b';
                                break;
                            }
                            break;
                        case 99:
                            if (str3.equals(CONNECTION_TYPE)) {
                                c = 7;
                                break;
                            }
                            break;
                        case 101:
                            if (str3.equals(EMAIL_TYPE)) {
                                c = 5;
                                break;
                            }
                            break;
                        case 105:
                            if (str3.equals("i")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 107:
                            if (str3.equals(KEY_TYPE)) {
                                c = '\n';
                                break;
                            }
                            break;
                        case 109:
                            if (str3.equals(MEDIA_TYPE)) {
                                c = '\f';
                                break;
                            }
                            break;
                        case 111:
                            if (str3.equals(ORIGIN_TYPE)) {
                                c = 1;
                                break;
                            }
                            break;
                        case 112:
                            if (str3.equals("p")) {
                                c = 6;
                                break;
                            }
                            break;
                        case 114:
                            if (str3.equals("r")) {
                                c = '\r';
                                break;
                            }
                            break;
                        case 115:
                            if (str3.equals("s")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 116:
                            if (str3.equals(TIMING_TYPE)) {
                                c = '\t';
                                break;
                            }
                            break;
                        case 117:
                            if (str3.equals(URI_TYPE)) {
                                c = 4;
                                break;
                            }
                            break;
                        case 118:
                            if (str3.equals("v")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 122:
                            if (str3.equals(ZONE_TYPE)) {
                                c = 14;
                                break;
                            }
                            break;
                    }
                }
            }
        }
        if (builder2 != null) {
            addMediaDescriptionToSession(builder, builder2);
        }
        try {
            return builder.build();
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw ParserException.createForMalformedManifest(null, e);
        }
    }

    private static void addMediaDescriptionToSession(SessionDescription.Builder builder, MediaDescription.Builder builder2) throws ParserException {
        try {
            builder.addMediaDescription(builder2.build());
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw ParserException.createForMalformedManifest(null, e);
        }
    }

    private static MediaDescription.Builder parseMediaDescriptionLine(String str) throws ParserException {
        Matcher matcher = MEDIA_DESCRIPTION_PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw ParserException.createForMalformedManifest("Malformed SDP media description line: " + str, null);
        }
        try {
            return new MediaDescription.Builder((String) Assertions.checkNotNull(matcher.group(1)), Integer.parseInt((String) Assertions.checkNotNull(matcher.group(2))), (String) Assertions.checkNotNull(matcher.group(3)), Integer.parseInt((String) Assertions.checkNotNull(matcher.group(4))));
        } catch (NumberFormatException e) {
            Log.w(TAG, "Malformed SDP media description line: " + str, e);
            return null;
        }
    }

    private SessionDescriptionParser() {
    }
}
