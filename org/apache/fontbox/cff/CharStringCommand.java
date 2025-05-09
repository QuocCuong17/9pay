package org.apache.fontbox.cff;

import androidx.media3.extractor.text.ttml.TtmlNode;
import co.hyperverge.hyperkyc.data.models.WorkflowRequestType;
import com.facebook.AuthenticationTokenClaims;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class CharStringCommand {
    public static final Map<Key, String> TYPE1_VOCABULARY;
    public static final Map<Key, String> TYPE2_VOCABULARY;
    private Key commandKey = null;

    public CharStringCommand(int i) {
        setKey(new Key(i));
    }

    public CharStringCommand(int i, int i2) {
        setKey(new Key(i, i2));
    }

    public CharStringCommand(int[] iArr) {
        setKey(new Key(iArr));
    }

    public Key getKey() {
        return this.commandKey;
    }

    private void setKey(Key key) {
        this.commandKey = key;
    }

    public String toString() {
        String str = TYPE2_VOCABULARY.get(getKey());
        if (str == null) {
            str = TYPE1_VOCABULARY.get(getKey());
        }
        if (str == null) {
            return getKey().toString() + '|';
        }
        return str + '|';
    }

    public int hashCode() {
        return getKey().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof CharStringCommand) {
            return getKey().equals(((CharStringCommand) obj).getKey());
        }
        return false;
    }

    /* loaded from: classes5.dex */
    public static class Key {
        private int[] keyValues = null;

        public Key(int i) {
            setValue(new int[]{i});
        }

        public Key(int i, int i2) {
            setValue(new int[]{i, i2});
        }

        public Key(int[] iArr) {
            setValue(iArr);
        }

        public int[] getValue() {
            return this.keyValues;
        }

        private void setValue(int[] iArr) {
            this.keyValues = iArr;
        }

        public String toString() {
            return Arrays.toString(getValue());
        }

        public int hashCode() {
            int[] iArr = this.keyValues;
            if (iArr[0] == 12 && iArr.length > 1) {
                return iArr[1] ^ iArr[0];
            }
            return iArr[0];
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            Key key = (Key) obj;
            int[] iArr = this.keyValues;
            if (iArr[0] == 12) {
                int[] iArr2 = key.keyValues;
                if (iArr2[0] == 12) {
                    return (iArr.length <= 1 || iArr2.length <= 1) ? iArr.length == iArr2.length : iArr[1] == iArr2[1];
                }
            }
            return iArr[0] == key.keyValues[0];
        }
    }

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(new Key(1), "hstem");
        linkedHashMap.put(new Key(3), "vstem");
        linkedHashMap.put(new Key(4), "vmoveto");
        linkedHashMap.put(new Key(5), "rlineto");
        linkedHashMap.put(new Key(6), "hlineto");
        linkedHashMap.put(new Key(7), "vlineto");
        linkedHashMap.put(new Key(8), "rrcurveto");
        linkedHashMap.put(new Key(9), "closepath");
        linkedHashMap.put(new Key(10), "callsubr");
        linkedHashMap.put(new Key(11), "return");
        linkedHashMap.put(new Key(12), "escape");
        linkedHashMap.put(new Key(12, 0), "dotsection");
        linkedHashMap.put(new Key(12, 1), "vstem3");
        linkedHashMap.put(new Key(12, 2), "hstem3");
        linkedHashMap.put(new Key(12, 6), "seac");
        linkedHashMap.put(new Key(12, 7), "sbw");
        linkedHashMap.put(new Key(12, 12), TtmlNode.TAG_DIV);
        linkedHashMap.put(new Key(12, 16), "callothersubr");
        linkedHashMap.put(new Key(12, 17), "pop");
        linkedHashMap.put(new Key(12, 33), "setcurrentpoint");
        linkedHashMap.put(new Key(13), "hsbw");
        linkedHashMap.put(new Key(14), "endchar");
        linkedHashMap.put(new Key(21), "rmoveto");
        linkedHashMap.put(new Key(22), "hmoveto");
        linkedHashMap.put(new Key(30), "vhcurveto");
        linkedHashMap.put(new Key(31), "hvcurveto");
        TYPE1_VOCABULARY = Collections.unmodifiableMap(linkedHashMap);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put(new Key(1), "hstem");
        linkedHashMap2.put(new Key(3), "vstem");
        linkedHashMap2.put(new Key(4), "vmoveto");
        linkedHashMap2.put(new Key(5), "rlineto");
        linkedHashMap2.put(new Key(6), "hlineto");
        linkedHashMap2.put(new Key(7), "vlineto");
        linkedHashMap2.put(new Key(8), "rrcurveto");
        linkedHashMap2.put(new Key(10), "callsubr");
        linkedHashMap2.put(new Key(11), "return");
        linkedHashMap2.put(new Key(12), "escape");
        linkedHashMap2.put(new Key(12, 3), "and");
        linkedHashMap2.put(new Key(12, 4), "or");
        linkedHashMap2.put(new Key(12, 5), "not");
        linkedHashMap2.put(new Key(12, 9), "abs");
        linkedHashMap2.put(new Key(12, 10), "add");
        linkedHashMap2.put(new Key(12, 11), AuthenticationTokenClaims.JSON_KEY_SUB);
        linkedHashMap2.put(new Key(12, 12), TtmlNode.TAG_DIV);
        linkedHashMap2.put(new Key(12, 14), "neg");
        linkedHashMap2.put(new Key(12, 15), "eq");
        linkedHashMap2.put(new Key(12, 18), "drop");
        linkedHashMap2.put(new Key(12, 20), "put");
        linkedHashMap2.put(new Key(12, 21), WorkflowRequestType.Method.GET);
        linkedHashMap2.put(new Key(12, 22), "ifelse");
        linkedHashMap2.put(new Key(12, 23), "random");
        linkedHashMap2.put(new Key(12, 24), "mul");
        linkedHashMap2.put(new Key(12, 26), "sqrt");
        linkedHashMap2.put(new Key(12, 27), "dup");
        linkedHashMap2.put(new Key(12, 28), "exch");
        linkedHashMap2.put(new Key(12, 29), FirebaseAnalytics.Param.INDEX);
        linkedHashMap2.put(new Key(12, 30), "roll");
        linkedHashMap2.put(new Key(12, 34), "hflex");
        linkedHashMap2.put(new Key(12, 35), "flex");
        linkedHashMap2.put(new Key(12, 36), "hflex1");
        linkedHashMap2.put(new Key(12, 37), "flex1");
        linkedHashMap2.put(new Key(14), "endchar");
        linkedHashMap2.put(new Key(18), "hstemhm");
        linkedHashMap2.put(new Key(19), "hintmask");
        linkedHashMap2.put(new Key(20), "cntrmask");
        linkedHashMap2.put(new Key(21), "rmoveto");
        linkedHashMap2.put(new Key(22), "hmoveto");
        linkedHashMap2.put(new Key(23), "vstemhm");
        linkedHashMap2.put(new Key(24), "rcurveline");
        linkedHashMap2.put(new Key(25), "rlinecurve");
        linkedHashMap2.put(new Key(26), "vvcurveto");
        linkedHashMap2.put(new Key(27), "hhcurveto");
        linkedHashMap2.put(new Key(28), "shortint");
        linkedHashMap2.put(new Key(29), "callgsubr");
        linkedHashMap2.put(new Key(30), "vhcurveto");
        linkedHashMap2.put(new Key(31), "hvcurveto");
        TYPE2_VOCABULARY = Collections.unmodifiableMap(linkedHashMap2);
    }
}
