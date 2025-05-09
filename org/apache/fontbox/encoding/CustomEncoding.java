package org.apache.fontbox.encoding;

import java.util.Map;

/* loaded from: classes5.dex */
public class CustomEncoding extends Encoding {
    public CustomEncoding(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            addCharacterEncoding(entry.getKey().intValue(), entry.getValue());
        }
    }
}
