package org.apache.fontbox.ttf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class NamingTable extends TTFTable {
    public static final String TAG = "name";
    private List<NameRecord> nameRecords = new ArrayList();
    private Map<Integer, Map<Integer, Map<Integer, Map<Integer, String>>>> lookupTable = new HashMap();
    private String fontFamily = null;
    private String fontSubFamily = null;
    private String psName = null;

    @Override // org.apache.fontbox.ttf.TTFTable
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        tTFDataStream.readUnsignedShort();
        int readUnsignedShort = tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        for (int i = 0; i < readUnsignedShort; i++) {
            NameRecord nameRecord = new NameRecord();
            nameRecord.initData(trueTypeFont, tTFDataStream);
            this.nameRecords.add(nameRecord);
        }
        for (int i2 = 0; i2 < readUnsignedShort; i2++) {
            NameRecord nameRecord2 = this.nameRecords.get(i2);
            if (nameRecord2.getStringOffset() > getLength()) {
                nameRecord2.setString(null);
            } else {
                tTFDataStream.seek(getOffset() + 6 + (readUnsignedShort * 2 * 6) + nameRecord2.getStringOffset());
                int platformId = nameRecord2.getPlatformId();
                int platformEncodingId = nameRecord2.getPlatformEncodingId();
                String str = "ISO-8859-1";
                if (platformId == 3 && (platformEncodingId == 1 || platformEncodingId == 0)) {
                    str = "UTF-16";
                } else if (platformId == 2) {
                    if (platformEncodingId == 0) {
                        str = "US-ASCII";
                    } else if (platformEncodingId == 1) {
                        str = "ISO-10646-1";
                    }
                }
                nameRecord2.setString(tTFDataStream.readString(nameRecord2.getStringLength(), str));
            }
        }
        for (NameRecord nameRecord3 : this.nameRecords) {
            if (!this.lookupTable.containsKey(Integer.valueOf(nameRecord3.getNameId()))) {
                this.lookupTable.put(Integer.valueOf(nameRecord3.getNameId()), new HashMap());
            }
            Map<Integer, Map<Integer, Map<Integer, String>>> map = this.lookupTable.get(Integer.valueOf(nameRecord3.getNameId()));
            if (!map.containsKey(Integer.valueOf(nameRecord3.getPlatformId()))) {
                map.put(Integer.valueOf(nameRecord3.getPlatformId()), new HashMap());
            }
            Map<Integer, Map<Integer, String>> map2 = map.get(Integer.valueOf(nameRecord3.getPlatformId()));
            if (!map2.containsKey(Integer.valueOf(nameRecord3.getPlatformEncodingId()))) {
                map2.put(Integer.valueOf(nameRecord3.getPlatformEncodingId()), new HashMap());
            }
            map2.get(Integer.valueOf(nameRecord3.getPlatformEncodingId())).put(Integer.valueOf(nameRecord3.getLanguageId()), nameRecord3.getString());
        }
        this.fontFamily = getEnglishName(1);
        this.fontSubFamily = getEnglishName(2);
        String name = getName(6, 1, 0, 0);
        this.psName = name;
        if (name == null) {
            this.psName = getName(6, 3, 1, NameRecord.LANGUGAE_WINDOWS_EN_US);
        }
        this.initialized = true;
    }

    private String getEnglishName(int i) {
        String name = getName(i, 3, 1, NameRecord.LANGUGAE_WINDOWS_EN_US);
        if (name != null) {
            return name;
        }
        String name2 = getName(i, 1, 0, 0);
        if (name2 != null) {
            return name2;
        }
        return null;
    }

    public String getName(int i, int i2, int i3, int i4) {
        Map<Integer, Map<Integer, String>> map;
        Map<Integer, String> map2;
        Map<Integer, Map<Integer, Map<Integer, String>>> map3 = this.lookupTable.get(Integer.valueOf(i));
        if (map3 == null || (map = map3.get(Integer.valueOf(i2))) == null || (map2 = map.get(Integer.valueOf(i3))) == null) {
            return null;
        }
        return map2.get(Integer.valueOf(i4));
    }

    public List<NameRecord> getNameRecords() {
        return this.nameRecords;
    }

    public String getFontFamily() {
        return this.fontFamily;
    }

    public String getFontSubFamily() {
        return this.fontSubFamily;
    }

    public String getPostScriptName() {
        return this.psName;
    }
}
