package com.google.android.gms.common.server.response;

import android.util.Log;
import androidx.media3.common.C;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import okio.internal._BufferKt;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes3.dex */
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaa = {'u', 'l', 'l'};
    private static final char[] zab = {'r', 'u', 'e'};
    private static final char[] zac = {'r', 'u', 'e', '\"'};
    private static final char[] zad = {'a', 'l', 's', 'e'};
    private static final char[] zae = {'a', 'l', 's', 'e', '\"'};
    private static final char[] zaf = {'\n'};
    private static final zai zag = new zaa();
    private static final zai zah = new zab();
    private static final zai zai = new zac();
    private static final zai zaj = new zad();
    private static final zai zak = new zae();
    private static final zai zal = new zaf();
    private static final zai zam = new zag();
    private static final zai zan = new zah();
    private final char[] zao = new char[1];
    private final char[] zap = new char[32];
    private final char[] zaq = new char[1024];
    private final StringBuilder zar = new StringBuilder(32);
    private final StringBuilder zas = new StringBuilder(1024);
    private final Stack zat = new Stack();

    /* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
    /* loaded from: classes3.dex */
    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super("Error instantiating inner object", th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
    
        throw new com.google.android.gms.common.server.response.FastParser.ParseException("Unexpected control character while reading string");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final String zaA(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        sb.setLength(0);
        bufferedReader.mark(cArr.length);
        boolean z = false;
        boolean z2 = false;
        loop0: while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                for (int i = 0; i < read; i++) {
                    char c = cArr[i];
                    if (Character.isISOControl(c)) {
                        if (cArr2 == null) {
                            break loop0;
                        }
                        for (int i2 = 0; i2 <= 0; i2++) {
                            if (cArr2[i2] != c) {
                            }
                        }
                        break loop0;
                    }
                    if (c == '\"') {
                        if (!z2) {
                            sb.append(cArr, 0, i);
                            bufferedReader.reset();
                            bufferedReader.skip(i + 1);
                            return z ? JsonUtils.unescapeString(sb.toString()) : sb.toString();
                        }
                    } else if (c == '\\') {
                        z2 = !z2;
                        z = true;
                    }
                    z2 = false;
                }
                sb.append(cArr, 0, read);
                bufferedReader.mark(cArr.length);
            } else {
                throw new ParseException("Unexpected EOF while parsing string");
            }
        }
    }

    private final char zai(BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.zao) == -1) {
            return (char) 0;
        }
        while (Character.isWhitespace(this.zao[0])) {
            if (bufferedReader.read(this.zao) == -1) {
                return (char) 0;
            }
        }
        return this.zao[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zaj(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.zaq, 0, zam2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zak(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zaq, 0, zam2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zal(BufferedReader bufferedReader) throws ParseException, IOException {
        int i;
        int i2;
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return 0;
        }
        char[] cArr = this.zaq;
        if (zam2 <= 0) {
            throw new ParseException("No number to parse");
        }
        char c = cArr[0];
        int i3 = c == '-' ? Integer.MIN_VALUE : C.RATE_UNSET_INT;
        int i4 = c == '-' ? 1 : 0;
        if (i4 < zam2) {
            i2 = i4 + 1;
            int digit = Character.digit(cArr[i4], 10);
            if (digit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            i = -digit;
        } else {
            i = 0;
            i2 = i4;
        }
        while (i2 < zam2) {
            int i5 = i2 + 1;
            int digit2 = Character.digit(cArr[i2], 10);
            if (digit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (i < -214748364) {
                throw new ParseException("Number too large");
            }
            int i6 = i * 10;
            if (i6 < i3 + digit2) {
                throw new ParseException("Number too large");
            }
            i = i6 - digit2;
            i2 = i5;
        }
        if (i4 == 0) {
            return -i;
        }
        if (i2 > 1) {
            return i;
        }
        throw new ParseException("No digits to parse");
    }

    private final int zam(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i;
        char zai2 = zai(bufferedReader);
        if (zai2 == 0) {
            throw new ParseException("Unexpected EOF");
        }
        if (zai2 == ',') {
            throw new ParseException("Missing value");
        }
        if (zai2 == 'n') {
            zax(bufferedReader, zaa);
            return 0;
        }
        bufferedReader.mark(1024);
        if (zai2 == '\"') {
            i = 0;
            boolean z = false;
            while (i < 1024 && bufferedReader.read(cArr, i, 1) != -1) {
                char c = cArr[i];
                if (Character.isISOControl(c)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
                if (c == '\"') {
                    if (!z) {
                        bufferedReader.reset();
                        bufferedReader.skip(i + 1);
                        return i;
                    }
                } else if (c == '\\') {
                    z = !z;
                    i++;
                }
                z = false;
                i++;
            }
        } else {
            cArr[0] = zai2;
            i = 1;
            while (i < 1024 && bufferedReader.read(cArr, i, 1) != -1) {
                char c2 = cArr[i];
                if (c2 == '}' || c2 == ',' || Character.isWhitespace(c2) || cArr[i] == ']') {
                    bufferedReader.reset();
                    bufferedReader.skip(i - 1);
                    cArr[i] = 0;
                    return i;
                }
                i++;
            }
        }
        if (i == 1024) {
            throw new ParseException("Absurdly long value");
        }
        throw new ParseException("Unexpected EOF");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zan(BufferedReader bufferedReader) throws ParseException, IOException {
        long j;
        int i;
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return 0L;
        }
        char[] cArr = this.zaq;
        if (zam2 <= 0) {
            throw new ParseException("No number to parse");
        }
        char c = cArr[0];
        long j2 = c == '-' ? Long.MIN_VALUE : C.TIME_UNSET;
        int i2 = c == '-' ? 1 : 0;
        if (i2 < zam2) {
            i = i2 + 1;
            int digit = Character.digit(cArr[i2], 10);
            if (digit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            j = -digit;
        } else {
            j = 0;
            i = i2;
        }
        while (i < zam2) {
            int i3 = i + 1;
            int digit2 = Character.digit(cArr[i], 10);
            if (digit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (j < _BufferKt.OVERFLOW_ZONE) {
                throw new ParseException("Number too large");
            }
            long j3 = j * 10;
            int i4 = zam2;
            long j4 = digit2;
            if (j3 < j2 + j4) {
                throw new ParseException("Number too large");
            }
            j = j3 - j4;
            zam2 = i4;
            i = i3;
        }
        if (i2 == 0) {
            return -j;
        }
        if (i > 1) {
            return j;
        }
        throw new ParseException("No digits to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zao(BufferedReader bufferedReader) throws ParseException, IOException {
        return zap(bufferedReader, this.zap, this.zar, null);
    }

    private final String zap(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        char zai2 = zai(bufferedReader);
        if (zai2 == '\"') {
            return zaA(bufferedReader, cArr, sb, cArr2);
        }
        if (zai2 == 'n') {
            zax(bufferedReader, zaa);
            return null;
        }
        throw new ParseException("Expected string");
    }

    private final String zaq(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zat.push(2);
        char zai2 = zai(bufferedReader);
        if (zai2 == '\"') {
            this.zat.push(3);
            String zaA = zaA(bufferedReader, this.zap, this.zar, null);
            zaw(3);
            if (zai(bufferedReader) == ':') {
                return zaA;
            }
            throw new ParseException("Expected key/value separator");
        }
        if (zai2 == ']') {
            zaw(2);
            zaw(1);
            zaw(5);
            return null;
        }
        if (zai2 == '}') {
            zaw(2);
            return null;
        }
        throw new ParseException("Unexpected token: " + zai2);
    }

    private final String zar(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        char zai2 = zai(bufferedReader);
        int i = 1;
        if (zai2 == '\"') {
            if (bufferedReader.read(this.zao) != -1) {
                char c = this.zao[0];
                boolean z = false;
                do {
                    if (c == '\"') {
                        if (z) {
                            c = '\"';
                            z = true;
                        }
                    }
                    z = c == '\\' ? !z : false;
                    if (bufferedReader.read(this.zao) != -1) {
                        c = this.zao[0];
                    } else {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                } while (!Character.isISOControl(c));
                throw new ParseException("Unexpected control character while reading string");
            }
            throw new ParseException("Unexpected EOF while parsing string");
        }
        if (zai2 == ',') {
            throw new ParseException("Missing value");
        }
        if (zai2 == '[') {
            this.zat.push(5);
            bufferedReader.mark(32);
            if (zai(bufferedReader) == ']') {
                zaw(5);
            } else {
                bufferedReader.reset();
                boolean z2 = false;
                boolean z3 = false;
                while (i > 0) {
                    char zai3 = zai(bufferedReader);
                    if (zai3 != 0) {
                        if (Character.isISOControl(zai3)) {
                            throw new ParseException("Unexpected control character while reading array");
                        }
                        if (zai3 == '\"') {
                            if (!z3) {
                                z2 = !z2;
                            }
                            zai3 = '\"';
                        }
                        if (zai3 == '[') {
                            if (!z2) {
                                i++;
                            }
                            zai3 = '[';
                        }
                        if (zai3 == ']' && !z2) {
                            i--;
                        }
                        z3 = (zai3 == '\\' && z2) ? !z3 : false;
                    } else {
                        throw new ParseException("Unexpected EOF while parsing array");
                    }
                }
                zaw(5);
            }
        } else if (zai2 == '{') {
            this.zat.push(1);
            bufferedReader.mark(32);
            char zai4 = zai(bufferedReader);
            if (zai4 == '}') {
                zaw(1);
            } else if (zai4 == '\"') {
                bufferedReader.reset();
                zaq(bufferedReader);
                do {
                } while (zar(bufferedReader) != null);
                zaw(1);
            } else {
                throw new ParseException("Unexpected token " + zai4);
            }
        } else {
            bufferedReader.reset();
            zam(bufferedReader, this.zaq);
        }
        char zai5 = zai(bufferedReader);
        if (zai5 == ',') {
            zaw(2);
            return zaq(bufferedReader);
        }
        if (zai5 == '}') {
            zaw(2);
            return null;
        }
        throw new ParseException("Unexpected token " + zai5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zas(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaq, 0, zam2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zat(BufferedReader bufferedReader) throws ParseException, IOException {
        int zam2 = zam(bufferedReader, this.zaq);
        if (zam2 == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaq, 0, zam2));
    }

    private final ArrayList zau(BufferedReader bufferedReader, zai zaiVar) throws ParseException, IOException {
        char zai2 = zai(bufferedReader);
        if (zai2 == 'n') {
            zax(bufferedReader, zaa);
            return null;
        }
        if (zai2 != '[') {
            throw new ParseException("Expected start of array");
        }
        this.zat.push(5);
        ArrayList arrayList = new ArrayList();
        while (true) {
            bufferedReader.mark(1024);
            char zai3 = zai(bufferedReader);
            if (zai3 == 0) {
                throw new ParseException("Unexpected EOF");
            }
            if (zai3 != ',') {
                if (zai3 != ']') {
                    bufferedReader.reset();
                    arrayList.add(zaiVar.zaa(this, bufferedReader));
                } else {
                    zaw(5);
                    return arrayList;
                }
            }
        }
    }

    private final ArrayList zav(BufferedReader bufferedReader, FastJsonResponse.Field field) throws ParseException, IOException {
        ArrayList arrayList = new ArrayList();
        char zai2 = zai(bufferedReader);
        if (zai2 == ']') {
            zaw(5);
            return arrayList;
        }
        if (zai2 == 'n') {
            zax(bufferedReader, zaa);
            zaw(5);
            return null;
        }
        if (zai2 == '{') {
            this.zat.push(1);
            while (true) {
                try {
                    FastJsonResponse zad2 = field.zad();
                    if (!zaz(bufferedReader, zad2)) {
                        return arrayList;
                    }
                    arrayList.add(zad2);
                    char zai3 = zai(bufferedReader);
                    if (zai3 != ',') {
                        if (zai3 == ']') {
                            zaw(5);
                            return arrayList;
                        }
                        throw new ParseException("Unexpected token: " + zai3);
                    }
                    if (zai(bufferedReader) == '{') {
                        this.zat.push(1);
                    } else {
                        throw new ParseException("Expected start of next object in array");
                    }
                } catch (IllegalAccessException e) {
                    throw new ParseException("Error instantiating inner object", e);
                } catch (InstantiationException e2) {
                    throw new ParseException("Error instantiating inner object", e2);
                }
            }
        } else {
            throw new ParseException("Unexpected token: " + zai2);
        }
    }

    private final void zaw(int i) throws ParseException {
        if (this.zat.isEmpty()) {
            throw new ParseException("Expected state " + i + " but had empty stack");
        }
        int intValue = ((Integer) this.zat.pop()).intValue();
        if (intValue == i) {
            return;
        }
        throw new ParseException("Expected state " + i + " but had " + intValue);
    }

    private final void zax(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (true) {
            int length = cArr.length;
            if (i >= length) {
                return;
            }
            int read = bufferedReader.read(this.zap, 0, length - i);
            if (read == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i2 = 0; i2 < read; i2++) {
                if (cArr[i2 + i] != this.zap[i2]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i += read;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zay(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        char[] cArr;
        char zai2 = zai(bufferedReader);
        if (zai2 == '\"') {
            if (z) {
                throw new ParseException("No boolean value found in string");
            }
            return zay(bufferedReader, true);
        }
        if (zai2 == 'f') {
            if (z) {
                cArr = zae;
            } else {
                cArr = zad;
            }
            zax(bufferedReader, cArr);
            return false;
        }
        if (zai2 == 'n') {
            zax(bufferedReader, zaa);
            return false;
        }
        if (zai2 == 't') {
            zax(bufferedReader, z ? zac : zab);
            return true;
        }
        throw new ParseException("Unexpected token: " + zai2);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x003b. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x026f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0253 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean zaz(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        int i;
        HashMap hashMap;
        char zai2;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String zaq = zaq(bufferedReader);
        if (zaq != null) {
            while (zaq != null) {
                FastJsonResponse.Field<?, ?> field = fieldMappings.get(zaq);
                if (field == null) {
                    zaq = zar(bufferedReader);
                } else {
                    this.zat.push(4);
                    int i2 = field.zaa;
                    switch (i2) {
                        case 0:
                            if (field.zab) {
                                fastJsonResponse.zav(field, zau(bufferedReader, zag));
                            } else {
                                fastJsonResponse.zau(field, zal(bufferedReader));
                            }
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                                zaq = zaq(bufferedReader);
                                break;
                            } else {
                                if (zai2 != '}') {
                                    throw new ParseException("Expected end of object or field separator, but found: " + zai2);
                                }
                                zaq = null;
                                break;
                            }
                        case 1:
                            if (field.zab) {
                                fastJsonResponse.zag(field, zau(bufferedReader, zam));
                            } else {
                                fastJsonResponse.zae(field, zat(bufferedReader));
                            }
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                            }
                            break;
                        case 2:
                            if (field.zab) {
                                fastJsonResponse.zay(field, zau(bufferedReader, zah));
                            } else {
                                fastJsonResponse.zax(field, zan(bufferedReader));
                            }
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                            }
                            break;
                        case 3:
                            if (field.zab) {
                                fastJsonResponse.zas(field, zau(bufferedReader, zai));
                            } else {
                                fastJsonResponse.zaq(field, zak(bufferedReader));
                            }
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                            }
                            break;
                        case 4:
                            if (field.zab) {
                                fastJsonResponse.zao(field, zau(bufferedReader, zaj));
                            } else {
                                fastJsonResponse.zam(field, zaj(bufferedReader));
                            }
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                            }
                            break;
                        case 5:
                            if (field.zab) {
                                fastJsonResponse.zac(field, zau(bufferedReader, zan));
                            } else {
                                fastJsonResponse.zaa(field, zas(bufferedReader));
                            }
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                            }
                            break;
                        case 6:
                            if (field.zab) {
                                fastJsonResponse.zaj(field, zau(bufferedReader, zak));
                            } else {
                                fastJsonResponse.zai(field, zay(bufferedReader, false));
                            }
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                            }
                            break;
                        case 7:
                            if (field.zab) {
                                fastJsonResponse.zaC(field, zau(bufferedReader, zal));
                            } else {
                                fastJsonResponse.zaA(field, zao(bufferedReader));
                            }
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                            }
                            break;
                        case 8:
                            fastJsonResponse.zal(field, Base64Utils.decode(zap(bufferedReader, this.zaq, this.zas, zaf)));
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                            }
                            break;
                        case 9:
                            fastJsonResponse.zal(field, Base64Utils.decodeUrlSafe(zap(bufferedReader, this.zaq, this.zas, zaf)));
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                            }
                            break;
                        case 10:
                            char zai3 = zai(bufferedReader);
                            if (zai3 == 'n') {
                                zax(bufferedReader, zaa);
                                hashMap = null;
                            } else if (zai3 == '{') {
                                this.zat.push(1);
                                hashMap = new HashMap();
                                while (true) {
                                    char zai4 = zai(bufferedReader);
                                    if (zai4 != 0) {
                                        if (zai4 == '\"') {
                                            String zaA = zaA(bufferedReader, this.zap, this.zar, null);
                                            if (zai(bufferedReader) == ':') {
                                                if (zai(bufferedReader) == '\"') {
                                                    hashMap.put(zaA, zaA(bufferedReader, this.zap, this.zar, null));
                                                    char zai5 = zai(bufferedReader);
                                                    if (zai5 != ',') {
                                                        if (zai5 == '}') {
                                                            zaw(1);
                                                        } else {
                                                            throw new ParseException("Unexpected character while parsing string map: " + zai5);
                                                        }
                                                    }
                                                } else {
                                                    throw new ParseException("Expected String value for key ".concat(String.valueOf(zaA)));
                                                }
                                            } else {
                                                throw new ParseException("No map value found for key ".concat(String.valueOf(zaA)));
                                            }
                                        } else if (zai4 == '}') {
                                            zaw(1);
                                        }
                                        i = 4;
                                        zaw(i);
                                        zaw(2);
                                        zai2 = zai(bufferedReader);
                                        if (zai2 == ',') {
                                        }
                                    } else {
                                        throw new ParseException("Unexpected EOF");
                                    }
                                }
                            } else {
                                throw new ParseException("Expected start of a map object");
                            }
                            fastJsonResponse.zaB(field, hashMap);
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                            }
                            break;
                        case 11:
                            if (field.zab) {
                                char zai6 = zai(bufferedReader);
                                if (zai6 == 'n') {
                                    zax(bufferedReader, zaa);
                                    fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, null);
                                } else {
                                    this.zat.push(5);
                                    if (zai6 == '[') {
                                        fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, zav(bufferedReader, field));
                                    } else {
                                        throw new ParseException("Expected array start");
                                    }
                                }
                            } else {
                                char zai7 = zai(bufferedReader);
                                if (zai7 == 'n') {
                                    zax(bufferedReader, zaa);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.zae, null);
                                } else {
                                    this.zat.push(1);
                                    if (zai7 == '{') {
                                        try {
                                            FastJsonResponse zad2 = field.zad();
                                            zaz(bufferedReader, zad2);
                                            fastJsonResponse.addConcreteTypeInternal(field, field.zae, zad2);
                                        } catch (IllegalAccessException e) {
                                            throw new ParseException("Error instantiating inner object", e);
                                        } catch (InstantiationException e2) {
                                            throw new ParseException("Error instantiating inner object", e2);
                                        }
                                    } else {
                                        throw new ParseException("Expected start of object");
                                    }
                                }
                            }
                            i = 4;
                            zaw(i);
                            zaw(2);
                            zai2 = zai(bufferedReader);
                            if (zai2 == ',') {
                            }
                            break;
                        default:
                            throw new ParseException("Invalid field type " + i2);
                    }
                }
            }
            zaw(1);
            return true;
        }
        zaw(1);
        return false;
    }

    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            try {
                this.zat.push(0);
                char zai2 = zai(bufferedReader);
                if (zai2 != 0) {
                    if (zai2 == '[') {
                        this.zat.push(5);
                        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                        if (fieldMappings.size() != 1) {
                            throw new ParseException("Object array response class must have a single Field");
                        }
                        FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                        t.addConcreteTypeArrayInternal(value, value.zae, zav(bufferedReader, value));
                    } else if (zai2 == '{') {
                        this.zat.push(1);
                        zaz(bufferedReader, t);
                    } else {
                        throw new ParseException("Unexpected token: " + zai2);
                    }
                    zaw(0);
                    try {
                        bufferedReader.close();
                        return;
                    } catch (IOException unused) {
                        Log.w("FastParser", "Failed to close reader while parsing.");
                        return;
                    }
                }
                throw new ParseException("No data to parse");
            } catch (IOException e) {
                throw new ParseException(e);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th;
        }
    }
}
