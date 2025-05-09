package org.apache.pdfbox.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.pdfbox.cos.COSName;

/* loaded from: classes5.dex */
public final class FilterFactory {
    public static final FilterFactory INSTANCE = new FilterFactory();
    private final Map<COSName, Filter> filters;

    private FilterFactory() {
        HashMap hashMap = new HashMap();
        this.filters = hashMap;
        FlateFilter flateFilter = new FlateFilter();
        DCTFilter dCTFilter = new DCTFilter();
        CCITTFaxFilter cCITTFaxFilter = new CCITTFaxFilter();
        LZWFilter lZWFilter = new LZWFilter();
        ASCIIHexFilter aSCIIHexFilter = new ASCIIHexFilter();
        ASCII85Filter aSCII85Filter = new ASCII85Filter();
        RunLengthDecodeFilter runLengthDecodeFilter = new RunLengthDecodeFilter();
        CryptFilter cryptFilter = new CryptFilter();
        hashMap.put(COSName.FLATE_DECODE, flateFilter);
        hashMap.put(COSName.FLATE_DECODE_ABBREVIATION, flateFilter);
        hashMap.put(COSName.DCT_DECODE, dCTFilter);
        hashMap.put(COSName.DCT_DECODE_ABBREVIATION, dCTFilter);
        hashMap.put(COSName.CCITTFAX_DECODE, cCITTFaxFilter);
        hashMap.put(COSName.CCITTFAX_DECODE_ABBREVIATION, cCITTFaxFilter);
        hashMap.put(COSName.LZW_DECODE, lZWFilter);
        hashMap.put(COSName.LZW_DECODE_ABBREVIATION, lZWFilter);
        hashMap.put(COSName.ASCII_HEX_DECODE, aSCIIHexFilter);
        hashMap.put(COSName.ASCII_HEX_DECODE_ABBREVIATION, aSCIIHexFilter);
        hashMap.put(COSName.ASCII85_DECODE, aSCII85Filter);
        hashMap.put(COSName.ASCII85_DECODE_ABBREVIATION, aSCII85Filter);
        hashMap.put(COSName.RUN_LENGTH_DECODE, runLengthDecodeFilter);
        hashMap.put(COSName.RUN_LENGTH_DECODE_ABBREVIATION, runLengthDecodeFilter);
        hashMap.put(COSName.CRYPT, cryptFilter);
    }

    public Filter getFilter(String str) throws IOException {
        return getFilter(COSName.getPDFName(str));
    }

    public Filter getFilter(COSName cOSName) throws IOException {
        Filter filter = this.filters.get(cOSName);
        if (filter != null) {
            return filter;
        }
        throw new IOException("Invalid filter: " + cOSName);
    }

    Collection<Filter> getAllFilters() {
        return this.filters.values();
    }
}
