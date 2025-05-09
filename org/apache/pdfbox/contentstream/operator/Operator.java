package org.apache.pdfbox.contentstream.operator;

import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.pdfbox.cos.COSDictionary;

/* loaded from: classes5.dex */
public final class Operator {
    private static final ConcurrentMap<String, Operator> operators = new ConcurrentHashMap();
    private byte[] imageData;
    private COSDictionary imageParameters;
    private final String theOperator;

    private Operator(String str) {
        this.theOperator = str;
        if (str.startsWith(RemoteSettings.FORWARD_SLASH_STRING)) {
            throw new RuntimeException("Operators are not allowed to start with / '" + str + "'");
        }
    }

    public static Operator getOperator(String str) {
        if (str.equals("ID") || str.equals("BI")) {
            return new Operator(str);
        }
        ConcurrentMap<String, Operator> concurrentMap = operators;
        Operator operator = concurrentMap.get(str);
        if (operator != null) {
            return operator;
        }
        Operator putIfAbsent = concurrentMap.putIfAbsent(str, new Operator(str));
        return putIfAbsent == null ? concurrentMap.get(str) : putIfAbsent;
    }

    public String getName() {
        return this.theOperator;
    }

    public String toString() {
        return "PDFOperator{" + this.theOperator + "}";
    }

    public byte[] getImageData() {
        return this.imageData;
    }

    public void setImageData(byte[] bArr) {
        this.imageData = bArr;
    }

    public COSDictionary getImageParameters() {
        return this.imageParameters;
    }

    public void setImageParameters(COSDictionary cOSDictionary) {
        this.imageParameters = cOSDictionary;
    }
}
